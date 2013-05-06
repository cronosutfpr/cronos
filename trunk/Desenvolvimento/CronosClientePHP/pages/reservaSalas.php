<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {

        $('a[name=modal]').click(function(e) {
            e.preventDefault();

            var id = $(this).attr('href');

            var maskHeight = $(document).height();
            var maskWidth = $(window).width();

            $('#mask').css({'width': maskWidth, 'height': maskHeight});

            $('#mask').fadeIn(1000);
            $('#mask').fadeTo("slow", 0.8);

            //Get the window height and width
            var winH = $(window).height();
            var winW = $(window).width();

            $(id).css('top', 10);
            $(id).css('left', 200);

            $(id).fadeIn(2000);

        });

        $('.window .close').click(function(e) {
            e.preventDefault();

            $('#mask').hide();
            $('.window').hide();
        });

        $('#mask').click(function() {
            $(this).hide();
            $('.window').hide();
        });

    });
</script>

<?php
require_once "classes/geral.php";
$geral = new geral();

$sql = "select classroom0_.id as id2_, classroom0_._short as column2_2_, classroom0_.bookable as bookable2_,
	 classroom0_.building as building2_, classroom0_.capacity as capacity2_, classroom0_.idxml as idxml2_, classroom0_.name as name2_, 
	 classroom0_.owner_id as owner10_2_, classroom0_.status as status2_, classroom0_.type as type2_ from ClassRoom classroom0_";

$query_lista = $geral->sql_select($sql);
?>

<form id="form_listagem" action="" method="post">
    <table cellpadding="0" cellspacing="0" border="0" class="display" id="listagem">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Prédio</th>
                <th>Capacidade</th>
                <th>E-Tipo</th>
                <th>Status</th>
                <th>Reservar</th>
            </tr>
        </thead>
        <tbody>
            <?php
            while ($linha = mysql_fetch_object($query_lista)) {
                echo "<tr>
                                            <td>" . $linha->name2_ . "</td>
                                            <td>" . $linha->building2_ . "</td>
                                            <td>" . $linha->capacity2_ . "</td>
                                            <td>" . $linha->type2_ . "</td>
                                            <td>" . $linha->status2_ . "</td>"
                ?>
            <td class="center">
                <a href="#dialog" name="modal"><img src="images/icn_new_article.png" title="Reservar"/></a>
            </td>
            <?php
            echo "</tr>";
            ?>

            <?php
        }
        ?>      


        </tbody>
        <tfoot>
            <tr>
                <th>Nome</th>
                <th>Prédio</th>
                <th>Capacidade</th>
                <th>E-Tipo</th>
                <th>Status</th>
                <th>Reservar</th>

            </tr>
        </tfoot>
    </table>
    <!--    <div id="osx-modal-content">
            <div id="osx-modal-title">OSX Style Modal Dialog</div>
            <div class="close"><a href="#" class="simplemodal-close">x</a></div>
            <div id="osx-modal-data">
    <?php // include 'calendarView.php' ?>
            </div>
        </div>-->
    <div id="boxes">
        <div id="dialog" class="window">
            <a href="#" class="close">Fechar [X]</a><br />
                <?php include 'calendarView.php';
                        include 'json-events.php';
                ?>
        </div>
    </div>

    <div id="mask"></div>
</form>