<?php
//require_once('services_pages/book_calendar.php');

foreach ($_GET as $nome => $valor) {
    $$nome = $valor;
}

echo $id;


require_once('classes/funcoes.php');
require_once('classes/pdo.class.php');
require_once('classes/mysql.class.php');
require_once('classes/book.class.php');

// Inicia a sessão caso não esteja inicada.
if (!isset($_SESSION)) {
    session_start();
}

// Instancia um objeto do tipo book.
$book = new book();
// Gera o comando SQL
$sql = $book->listBookCalendar();
// Instancia uma base de dados via PDO (padrão do PHP)
$banco = new cPDO();
// Executa o SQL
$banco->query($sql);

foreach ($banco->query($sql) as $value) {
    $teste[] = "{title: '" . $value['title'] . "', start: '" . $value['startdate'] . "', end: '" . $value['enddate'] . "'},";
}
?>

<!DOCTYPE html>
<html>
    <head>
        <link href='js/fullcalendar/fullcalendar.css' rel='stylesheet' />
        <link href='js/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
        <script src='js/jquery/jquery-1.9.1.min.js'></script>
        <script src='js/jquery/jquery-ui-1.10.2.custom.min.js'></script>
        <script src='js/fullcalendar/fullcalendar.min.js'></script>
        <script>
            $(document).ready(function() {
                
                var date = new Date();
                var d = date.getDate();
                var m = date.getMonth();
                var y = date.getFullYear();
		
                $('#calendar').fullCalendar({
                    dayClick: function(a) {
                        alert(a);
                    },
                    
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,basicWeek,basicDay'
                    },
                    editable: true,
                    events:
                            <?php
                            echo '[';
                            for ($x = 0; $x < count($teste); $x++) {
                                if ($x < count($teste) - 1) {
                                    echo $teste[$x];
                                } else {
                                    echo substr($teste[$x], 0, -1) . ']';
                                }
                            }
                            ?>
                                });
                                //Evento de click	
                                //		$('#calendar').fullCalendar({
                                //    dayClick: function() {
                                //        alert('a day has been clicked!');
                                //    }
                                //});
		
                            });

        </script>
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
    </body>
</html>
