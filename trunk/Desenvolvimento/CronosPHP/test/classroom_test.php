<?php
require_once('../host.php');

if (!isset($_SESSION)) {
    session_start();
}
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Cronos - Classroom Test</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <!-- JS -->
        <script type="text/javascript" src="../../js/jquery-1.7.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#sendInsert').click(function() {
                    var idxml = 'ASDF123';
                    var name = 'Laboratorio de Teste Novo';
                    var _short = 'UP001';
                    var capacity = '40';
                    var type = '1';
                    var building = 'E';
                    var owner = '';
                    var bookable = '1';
                    var task = 'insert';

                    $('#frm_testes').fadeTo("slow", 0.3);
                    $.post("<?php echo $host; ?>services/classroom/", {idxml: idxml, name: name, _short: _short, capacity: capacity, type: type, building: building, owner: owner, bookable: bookable, task: task},
                    function(retorno) {
                        $('#resposta').html(retorno).fadeIn();
                        $('#frm_testes').fadeTo("slow", 2);
                    });
                });
                
                $('#sendUpdate').click(function() {
                    var id = '57';
                    var idxml = 'LKJ987';
                    var name = '<?php echo utf8_decode('Laboratorio de Teste Update'); ?>';
                    var _short = 'UP002';
                    var capacity = '40';
                    var type = '1';
                    var building = 'E';
                    var owner = '';
                    var bookable = '1';
                    var task = 'update';

                    $('#frm_testes').fadeTo("slow", 0.3);
                    $.post("<?php echo $host; ?>services/classroom/", {id: id, idxml: idxml, name: name, _short: _short, capacity: capacity, type: type, building: building, owner: owner, bookable: bookable, task: task},
                    function(retorno) {
                        $('#resposta').html(retorno).fadeIn();
                        $('#frm_testes').fadeTo("slow", 2);
                    });
                });
                
                $('#sendDelete').click(function() {
                    var id = '71';
                    var task = 'delete';

                    $('#frm_testes').fadeTo("slow", 0.3);
                    $.post("<?php echo $host; ?>services/classroom/", {id: id, task: task},
                    function(retorno) {
                        $('#resposta').html(retorno).fadeIn();
                        $('#frm_testes').fadeTo("slow", 2);
                    });
                });
                
                $('#sendListAll').click(function() {
                    $('#frm_testes').fadeTo("slow", 0.3);
                    $.get("<?php echo $host; ?>services/classroom/",
                    function(retorno) {
                        var conteudo = "<br><strong>Mostrando Todas as Salas </strong><br><hr><br>";
                        var i = 0;
                        for ($i = 0; $i < retorno.length; $i++) {
                            conteudo += "<strong>Nome:</strong> "+retorno[$i].name+"<br>";
                        }
                        $('#resposta').html(conteudo).fadeIn();
                        $('#frm_testes').fadeTo("slow", 2);
                    });
                });
                $('#sendListUnique').click(function() {
                    $('#frm_testes').fadeTo("slow", 0.3);
                    $.get("<?php echo $host; ?>services/classroom/list/55",
                    function(retorno) {
                        var conteudo = "<br><strong>Mostrando apenas a Sala de ID = 55 </strong><br><hr><br>";
                        var i = 0;
                        for ($i = 0; $i < retorno.length; $i++) {
                            conteudo += "<strong>Nome:</strong> "+retorno[$i].name+"<br>";
                        }
                        $('#resposta').html(conteudo).fadeIn();
                        $('#frm_testes').fadeTo("slow", 2);
                    });
                });
                $('#sendListPage').click(function() {
                    $('#frm_testes').fadeTo("slow", 0.3);
                    $.get("<?php echo $host; ?>services/classroom/list/pg/1",
                    function(retorno) {
                        var conteudo = "<br><strong>Mostrando apenas a P&aacutegina 1 </strong><br><hr><br>";
                        var i = 0;
                        for ($i = 0; $i < retorno.length; $i++) {
                            conteudo += "<strong>Nome:</strong> "+retorno[$i].name+"<br>";
                        }
                        $('#resposta').html(conteudo).fadeIn();
                        $('#frm_testes').fadeTo("slow", 2);
                    });
                });
                $('#sendListShort').click(function() {
                    $('#frm_testes').fadeTo("slow", 0.3);
                    $.get("<?php echo $host; ?>services/classroom/list/classroom/C005",
                    function(retorno) {
                        var conteudo = "<br><strong>Mostrando apenas a Sala C005 </strong><br><hr><br>";
                        var i = 0;
                        for ($i = 0; $i < retorno.length; $i++) {
                            conteudo += "<strong>Nome:</strong> "+retorno[$i].name+"<br>";
                        }
                        $('#resposta').html(conteudo).fadeIn();
                        $('#frm_testes').fadeTo("slow", 2);
                    });
                });
            });
        </script>
    </head>
    <body>
        <form name="frm_testes" id="frm_testes" method="post" action="#" >
            <fieldset>
                <legend>Teste do Webservice Classroom</legend>
                <input type="button" value="Listar Todas" id="sendListAll" class="send" title="Listar Todas" >
                <input type="button" value="Listar por ID" id="sendListUnique" class="send" title="Listar por ID" >
                <input type="button" value="Listar Paginado" id="sendListPage" class="send" title="Listar Paginado" >
                <input type="button" value="Listar por Abrevia&ccedil;&atilde;o" id="sendListShort" class="send" title="Listar por Abrevia&ccedil;&atilde;o" >
                <input type="button" value="Inserir" id="sendInsert" class="send" title="Inserir" >
                <input type="button" value="Update" id="sendUpdate" class="send" title="Update" >
                <input type="button" value="Delete" id="sendDelete" class="send" title="Delete" >
            </fieldset>
            <div id="resposta" class="resposta"></div>
        </form>
    </body>
</html>