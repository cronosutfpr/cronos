<?php

error_reporting(true);

header('content-type: application/json; charset=utf-8');

require_once('model/funcoes.php');
require_once('model/pdo.class.php');
require_once('model/mysql.class.php');
require_once('model/classroom.class.php');

if (!isset($_SESSION)) {
    session_start();
}

// Variavel que recebe os parametros para definir qual ação deverá ser executada.
$task = $_POST['task'];

// Inicializada para poder simular o resultado.
//$task = 'listar_salas';
$read = $_GET['task'];

if (substr_count($read, '/') > 0) {
    $read = explode('/', $read);
    $id = $read[1];
} else {
    $id = 0;
}

if ($read != "") {
    $task = $read;
}
switch ($task) {

    case "listar_salas":

        $classroom = new classroom();
        $classroom->set(id, $id);

        $sql = $classroom->ListaClassRoom();

        $banco = new cPDO();
        $banco->query($sql);
        $row = $banco->query($sql)->fetchAll();
        $total = $banco->query($sql)->rowCount();

        //echo $json = json_encode(utf8_encode_all($row));
//        foreach ($banco->query($sql) as $row) {
//            echo utf8_encode($row['name']);
//            echo json_encode(utf8_encode($row['name']));
//        }

        echo json_encode(utf8_encode_all($row));

        break;

    default:
        $sql = "";
        break;
}

function utf8_encode_all($dat) {
// -- It returns $dat encoded to UTF8 
    if (is_string($dat))
        return utf8_encode($dat);
    if (!is_array($dat))
        return $dat;
    $ret = array();
    foreach ($dat as $i => $d)
        $ret[$i] = utf8_encode_all($d);
    return $ret;
}

?>