<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<?php
require_once "classes/geral.php";
$geral = new geral();

$sql = "select classroom0_.id as id2_, classroom0_._short as column2_2_, classroom0_.bookable as bookable2_,
	 classroom0_.building as building2_, classroom0_.capacity as capacity2_, classroom0_.idxml as idxml2_, classroom0_.name as name2_, 
	 classroom0_.owner_id as owner10_2_, classroom0_.status as status2_, classroom0_.type as type2_ from ClassRoom classroom0_";

$query_lista = $geral->sql_select($sql);
?>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Gerenciador de Salas</title>
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />

        <!--jdatatable-->
        <link href="css/demo_table_jui.css" rel="stylesheet" type="text/css" media="screen" />
        <link href="css/themes/smoothness/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" media="screen" />
        <link href="js/alert_query.css" rel="stylesheet" type="text/css" media="screen" />        
        <link href='css/fullcalendar/fullcalendar.css' rel='stylesheet' />
        <link href='css/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
        <link type='text/css' href='css/modal.css' rel='stylesheet' media='screen' />
        <link type='text/css' href='css/osx.css' rel='stylesheet' media='screen' />


        <script language="JavaScript" src="js/jquery-1.8.2.js" type="text/javascript"></script>
        <script type="text/javascript" src='js/fullcalendar/fullcalendar.min.js'></script>
        <script type="text/javascript" src='js/jquery/jquery-ui-1.10.2.custom.min.js'></script>
        <script type="text/javascript" language="javascript" src="js/jquery.dataTables.js"></script>
        <script type="text/javascript" language="javascript" src="js/jquery-impromptu.4.0.min.js"></script>
        <script type='text/javascript' src='js/modal/jquery.simplemodal.js'></script>
        <script type='text/javascript' src='js/modal/osx.js'></script>
        <script type="text/javascript" charset="utf-8">
            var oTable;

            $(document).ready(function() {
                $('#form_listagem').submit(function() {
                    var sData = oTable.$('input').serialize();
                    alert("\n\n" + sData);
                    return false;
                });

                oTable = $('#listagem').dataTable({
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers", "oLanguage": {
                        "sUrl": "css/themes/de_DE.txt"

                    }
                });
            });

        </script>
    </head>

    <body>
        <div id="cabecalho">
            <div>
                <h1 class="site_title"><a href="index.html">Navegação</a></h1>
                <h2 class="site_gestor">Gerenciador de Salas</h2>
            </div>
        </div>
        <div id="bar_navegacao">
            <div class="user">
                <p>usuario </p>
                <a class="logout_user" href="#" title="Logout">Sair</a> </div>
            <div class="breadcrumbs_container">
                <div class="breadcrumbs"><a href="index.html">Administração</a>
                    <div class="breadcrumb_divider"></div>
                    <a href="index.html">Inicial</a> </div>
            </div>
            <div id="sidebar" class="column">
                <h3>Reservas</h3>
                <ul>
                    <li class="icn_edit_article"><a href="#">Nova Reserva </a></li>
                    <li class="icn_edit_article"><a href="#">Lista de reservas</a></li>
                    <li class="icn_edit_article"><a href="#">Minhas Reservas</a></li>
                </ul>
            </div>
            <div id="principal">

                <div class="conteudo">
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
                                <?php
                                    include 'pages/calendarView.php';
                                    include 'pages/json-events.php';
                                ?>
                            </div>
                        </div>

                        <div id="mask"></div>
                    </form>
                </div>

            </div>
    </body>
</html>
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