<?php
include "classes/geral.php";
$geral = new geral();

$sql = "select classroom0_.id as id2_, classroom0_._short as column2_2_, classroom0_.bookable as bookable2_,
	 classroom0_.building as building2_, classroom0_.capacity as capacity2_, classroom0_.idxml as idxml2_, classroom0_.name as name2_, 
	 classroom0_.owner_id as owner10_2_, classroom0_.status as status2_, classroom0_.type as type2_ from ClassRoom classroom0_";

$query_lista = $geral->sql_select($sql);
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Gerenciador de Salas</title>
        <link rel="stylesheet" href="css/layout.css" type="text/css" media="screen" />

        <!--jdatatable-->
        <link href="css/demo_table_jui.css" rel="stylesheet" type="text/css" media="screen" />
        <link href="css/themes/smoothness/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" media="screen" />

        <script language="JavaScript" src="js/jquery-1.8.2.js" type="text/javascript"></script>
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
                <p>ehrickwilliam </p>
                <a class="logout_user" href="#" title="Logout">Sair</a> </div>
            <div class="breadcrumbs_container">
                <div class="breadcrumbs"><a href="index.html">Adminstração</a>
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
                                            <td>" . $linha->status2_ . "</td>
                                      </tr>";
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

                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>

            </div>
    </body>
</html>