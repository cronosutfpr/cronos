<?php
//require_once('services_pages/book_calendar.php');
require_once 'host.php';
require_once '/classes/period.class.php';
require_once '/classes/mysql.class.php';

foreach ($_GET as $nome => $valor) {
    $$nome = $valor;
}
?>

<!DOCTYPE html>
<html>
    <head>
        <script language="JavaScript" src="js/jquery-1.8.2.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/jquery.maskedinput-1.2.2.js"></script>  
        <style>

            body {
                margin-top: 40px;
                text-align: center;
                font-size: 14px;
                font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
            }

            #calendar {
                width: 900px;
                margin: 0 auto;
            }

        </style>

    </head>
    <body>
        <div id='calendar'></div>
        <form name="frm_reserva" class="internas" method="post" action="#" id="frm_reserva">
            <input type="hidden" name="classroom_id" id="classroom_id" value="<?php echo $id; ?>" />
            <div>
                <label>Data Início:</label>
                <input type="text" name="startdate" id="startdate" maxlength="200" />
            </div>
            <div>
                <label>Data Fim:</label>
                <input type="text" name="endDate" id="endDate" maxlength="200" />
            </div>
            <div>
                <label>Obs:</label>
                <textarea name="note" id="note"></textarea>
            </div>
            <div>
                <label>Periodo:</label>
                <select name="period_id" id="period_id" style="width: 60px; margin-left: 3px">
                    <?php
                    $periodo = new period();
                    $sql = $periodo->ListPeriod();

                    $banco = new mysql();
                    $banco->connect();
                    $banco->sqlQuery($sql);

                    while ($row_periodo = $banco->fetch_object()) {
                        ?>
                        <option value="<?php echo $row_periodo->id; ?>"><?php echo $row_periodo->name; ?></option>
                    <?php }
                    ?>
                </select>
            </div>
            <div class="botoesForm">
                <input type="button" value="Inserir" id="teste" class="send" title="Inserir" >
            </div>
            <div class="clear"></div><!--clear-->
            <div style="width: 100%;"></div><!--clear-->
            <div id="resposta" class="respostaCadastro"></div>
        </form>

        <script type="text/javascript">
            $('#teste').click(function() {
                var endDate = $('#endDate').val();
                var startdate = $('#startdate').val();
                var note = $('#note').val();
                var status = '1';
                var period_id = $('#period_id').val();
                var requestor_id = $('#requestor_id').val();
                var classroom_id = $('#classroom_id').val();

                var task = 'insert';

                $('#frm_reserva').fadeTo("slow", 0.3);
                $.post("<?php echo $host; ?>services_pages/book.php/", {endDate: endDate, startdate: startdate, note: note, status: status, period_id: period_id, requestor_id: requestor_id, classroom_id: classroom_id, task: task},
                function(retorno) {
                    $('#resposta').html(retorno).fadeIn();
                    $('#frm_reserva').fadeTo("slow", 2);
                });
            });

            $(document).ready(function() {
                $(function() {
                    $("#startdate").mask("99/99/9999");
                    $("#endDate").mask("99/99/9999");
                });
            });

        </script>
    </body>
</html>
