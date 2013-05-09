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




        <script language="JavaScript" src="js/jquery-1.8.2.js" type="text/javascript"></script>

        <link rel="stylesheet" href="js/prettyPhoto/css/prettyPhoto.css" type="text/css" media="screen" title="prettyPhoto main stylesheet" charset="utf-8" />
        <script src="js/prettyPhoto/js/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>

        <script type="text/javascript" src='js/fullcalendar/fullcalendar.min.js'></script>
        <script type="text/javascript" src='js/jquery/jquery-ui-1.10.2.custom.min.js'></script>
        <script type="text/javascript" language="javascript" src="js/jquery.dataTables.js"></script>

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

        <style type="text/css">
            .pp_nav{
                display: none !important;
            }
        </style>

        <script type="text/javascript" charset="utf-8">
	
            $(document).ready(function(){
                $("a[rel^='prettyPhoto']").prettyPhoto({
                    animation_speed: 'fast', /* fast/slow/normal */
                    slideshow: 5000, /* false OR interval time in ms */
                    autoplay_slideshow: false, /* true/false */
                    opacity: 0.80, /* Value between 0 and 1 */
                    show_title: false, /* true/false */
                    allow_resize: true, /* Resize the photos bigger than viewport. true/false */
                    default_width: 960,
                    default_height: 600,
                    counter_separator_label: '/', /* The separator for the gallery counter 1 "of" 2 */
                    theme: 'pp_default', /* light_rounded / dark_rounded / light_square / dark_square / facebook */
                    horizontal_padding: 20, /* The padding on each side of the picture */
                    hideflash: false, /* Hides all the flash object on a page, set to TRUE if flash appears over prettyPhoto */
                    wmode: 'opaque', /* Set the flash wmode attribute */
                    autoplay: false, /* Automatically start videos: True/False */
                    modal: false, /* If set to true, only the close button will close the window */
                    deeplinking: true, /* Allow prettyPhoto to update the url to enable deeplinking. */
                    overlay_gallery: false, /* If set to true, a gallery will overlay the fullscreen image on mouse over */
                    keyboard_shortcuts: true, /* Set to false if you open forms inside prettyPhoto */
                    changepicturecallback: function(){}, /* Called everytime an item is shown/changed */
                    callback: function(){}, /* Called when prettyPhoto is closed */
                    ie6_fallback: true,
                    social_tools: false
                });
            });
            function __url(a){location.href=a};
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
                                            <td>" . utf8_encode($linha->name2_) . "</td>
                                            <td>" . utf8_encode($linha->building2_) . "</td>
                                            <td>" . utf8_encode($linha->capacity2_) . "</td>
                                            <td>" . utf8_encode($linha->type2_) . "</td>
                                            <td>" . utf8_encode($linha->status2_) . "</td>"
                                    ?>
                                    <td class="center">

                                        <a href="reserva_box.php?id=<?php if (isset($linha->id2_)) echo $linha->id2_; ?>&name=<?php echo utf8_encode($linha->column2_2_); ?>&iframe=true&amp;" rel="prettyPhoto[mixed]"><img src="images/icn_new_article.png" title="Reservar"/></a>


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

                    </form>


                </div>
            </div>
    </body>
</html>
