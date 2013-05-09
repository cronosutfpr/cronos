<?php

error_reporting(false);

require_once('../classes/funcoes.php');
require_once('../classes/pdo.class.php');
require_once('../classes/mysql.class.php');
require_once('../classes/book.class.php');

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

echo '[';
for ($x = 0; $x < count($teste); $x++) {
    if ($x < count($teste) - 1) {
        echo $teste[$x];
    } else {
        echo substr($teste[$x], 0, -1) . ']';
    }
}
?>