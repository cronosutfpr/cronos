<?php

error_reporting(false);

header('content-type: application/json; charset=utf-8');

require_once('../model/funcoes.php');
require_once('../model/pdo.class.php');
require_once('../model/mysql.class.php');
require_once('../model/classroom.class.php');

// Inicia a sessão caso não esteja inicada.
if (!isset($_SESSION)) {
    session_start();
}

// Variável que recebe os parametros para definir qual ação deverá ser executada..
$task = htmlspecialchars(strip_tags($_POST['task']));

// Recebe a variável 'task' via GET, remove tags HTML e PHP - objetivo segurança, evitar código malicioso via URL.
$read = htmlspecialchars(strip_tags($_GET['task']));

// Quebra a variável 'task' em novas variáveis a cada '/'.
if (substr_count($read, '/') > 0) {
    $read = explode('/', $read);
    $id = $read[1];
    $pg = $read[2];
} else {
    $id = 0;
    $pg = 0;
}

// Caso venha alguma tarefa via POST setar os valores aqui.
if ($task != "") {
    $id = htmlspecialchars(strip_tags($_POST['id']));
    $idxml = htmlspecialchars(strip_tags($_POST['idxml']));
    $name = htmlspecialchars(strip_tags($_POST['name']));
    $_short = htmlspecialchars(strip_tags($_POST['_short']));
    $capacity = htmlspecialchars(strip_tags($_POST['capacity']));
    $type = htmlspecialchars(strip_tags($_POST['type']));
    $building = htmlspecialchars(strip_tags($_POST['building']));
    $owner = htmlspecialchars(strip_tags($_POST['owner']));
    $bookable = htmlspecialchars(strip_tags($_POST['bookable']));
}

// Caso o parametro venha via GET ele é setado na variavel 'task'.
if ($read != "") {
    $task = $read[0];
}


// De acordo com a tarefa definida no 'task' será executada uma ação dentro do switch.
switch ($task) {

    // Insere uma sala de acordo com os parametros passados pelo usuário via POST ou PUT
    case 'insert';
        insertOrUpdateClassroom($id, $idxml, $name, $_short, $capacity, $type, $building, $owner, $bookable);
        break;

    // Caso o usuário envie uma requisição via GET com o parametro 'list' serão listadas as salas de acordo com os paramtros que ele passar na URL
    case "list":
        listClassroom($id, $pg);
        break;

    // Atualiza uma sala de acordo com os parametros passados pelo usuário via POST ou UPDATE
    case 'update';
        insertOrUpdateClassroom($id, $idxml, $name, $_short, $capacity, $type, $building, $owner, $bookable);
        break;

    // Deleta uma sala de acordo com os parametros passados pelo usuário via POST ou DELETE.
    case 'delete';
        deleteClassroom($id);
        break;

    // Caso o usuário não passe nada por parametro serão exibidas todas as salas.
    default:
        listClassroom($id, $pg);
        break;
}

function listClassroom($id, $pg) {

    // As duas variáveis recebem o valor que vem depois do nome da tarefa, que pode ser uma ID de sala, uma página, uma abreviação da sala...
    $classroom_chosen = $pg;

    // Variáveis para controle de paginação.
    $start = 0;
    $limit = 10;

    // Instancia um objeto do tipo classroom.
    $classroom = new classroom();

    switch ($id) {
        // Caso o usário passe uma página ao invés de uma ID gera a paginação.
        case 'pg':
            if ($pg > 1) {
                // Esse é o cálculo para definir em qual regitro deve iniciar a consulta.
                $start = ($pg * $limit) - ($limit - 1);
            } else if ($pg > 0) {
                $start = 1;
            }
            // Define em qual registro o banco irá iniciar a consulta.
            $classroom->set(start, $start);
            // Define quantos registro o banco irá retornar na consulta.
            $classroom->set(limit, $limit);
            break;

        // Caso o usuário decida procuara por uma sala pela abreviação
        case 'classroom':
            $classroom->set(_short, $classroom_chosen);
            break;

        // Caso a ID seja um número siginifica que o usuário está pesquisando pela ID da sala. Seta esse valor no objeto.
        default:
            if (is_numeric($id)) {
                $classroom->set(id, $id);
            }
            break;
    }

    // Gera o comando SQL
    $sql = $classroom->ListClassRoom();

    // Instancia uma base de dados via PDO (padrão do PHP)
    $banco = new cPDO();
    // Executa o SQL
    $banco->query($sql);
    // Trasforma o retorno do banco em um array
    $row = $banco->query($sql)->fetchAll();
    // Conta quantos registros tem no banco
    $total = $banco->query($sql)->rowCount();

    // Gera o JSON do objeto e imprime na página
    echo json_encode(utf8_encode_all($row));
}

/**
 * Função que insere ou atualiza uma classroom
 * @param type $id
 * @return ID inserida no banco ou 0 em caso de erro.
 */
function insertOrUpdateClassroom($id, $idxml, $name, $_short, $capacity, $type, $building, $owner, $bookable) {

    // Instancia um objeto do tipo classroom.
    $classroom = new classroom();
    $classroom->set(id, $id);
    $classroom->set(idxml, $idxml);
    $classroom->set(_short, $_short);
    $classroom->set(name, $name);
    $classroom->set(owner, $owner);
    $classroom->set(type, $type);
    $classroom->set(building, $building);
    $classroom->set(capacity, $capacity);
    $classroom->set(bookable, $bookable);

    // Gera o comando SQL
    $sql = $classroom->insertOrUpdate();

    // Instancia uma base de dados via PDO (padrão do PHP)
    $banco = new cPDO();

    // Tenta executar o SQL, se conseguir retorna id que Inseriu ou atualizou senão retorna 0
    if (!$banco->query($sql)) {
        echo '0';
    } else {
        echo '1';
    }
}

function deleteClassroom($id) {

    // Instancia um objeto do tipo classroom.
    $classroom = new classroom();
    $classroom->set(id, $id);
    
    // Gera o comando SQL
    $sql = $classroom->delete();

    // Instancia uma base de dados via PDO (padrão do PHP)
    $banco = new cPDO();
    
    echo $banco->query($sql);
    
    // Tenta executar o SQL, se conseguir retorna id que excluiu senão retorna 0
    if (!$banco->query($sql)) {
        echo '0';
    } else {
        echo $id;
    }
}
?>