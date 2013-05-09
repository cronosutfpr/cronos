<?php
//require_once('services_pages/book_calendar.php');

foreach ($_GET as $nome => $valor) {
    $$nome = $valor;
}

?>

<!DOCTYPE html>
<html>
    <head>
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
        <div id='calendar'>A ID da sala selecionada &eacute; <?php echo $id;?> </div>
    </body>
</html>
