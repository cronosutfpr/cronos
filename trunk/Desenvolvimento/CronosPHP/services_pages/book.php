<?php

error_reporting(false);

header('content-type: application/json; charset=utf-8');

require_once('../model/funcoes.php');
require_once('../model/pdo.class.php');
require_once('../model/mysql.class.php');
require_once('../model/classroom.class.php');
require_once('../model/book.class.php');
require_once('../model/period.class.php');

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
    $id             = htmlspecialchars(strip_tags($_POST['id']));
    $endDate        = htmlspecialchars(strip_tags($_POST['endDate']));
    $note           = htmlspecialchars(strip_tags($_POST['note']));
    $startdate      = htmlspecialchars(strip_tags($_POST['startdate']));
    $status         = htmlspecialchars(strip_tags($_POST['status']));
    $classroom_id   = htmlspecialchars(strip_tags($_POST['classroom_id']));
    $requestor_id   = htmlspecialchars(strip_tags($_POST['requestor_id']));
    $periods_id     = htmlspecialchars(strip_tags($_POST['periods_id']));
}

// Caso o parametro venha via GET ele é setado na variavel 'task'.
if ($read != "") {
    $task = $read[0];
}

// De acordo com a tarefa definida no 'task' será executada uma ação dentro do switch.
switch ($task) {

    // Insere uma sala de acordo com os parametros passados pelo usuário via POST ou PUT
    case 'insert';
        insertOrUpdate($id, $classroom_id, $endDate, $note, $startdate, $status, $classroom_id, $requestor_id, $periods_id);
        break;

    // Caso o usuário envie uma requisição via GET com o parametro 'list' serão listadas as reservas de acordo com os paramtros que ele passar na URL
    case "list":
        listBook($id, $pg);
        break;

    // Atualiza uma sala de acordo com os parametros passados pelo usuário via POST ou UPDATE
    case 'update';
        insertOrUpdate($id, $classroom_id, $endDate, $note, $startdate, $status, $classroom_id, $requestor_id, $periods_id);
        break;

    // Deleta uma sala de acordo com os parametros passados pelo usuário via POST ou DELETE.
    case 'delete';
        delete($id);
        break;

    // Caso o usuário não passe nada por parametro serão exibidas todas as reservas.
    default:
        listBook($id, $pg);
        break;
}

function listBook($id, $pg) {

    // Variáveis para controle de paginação.
    $start = 0;
    $limit = 10;

    // Instancia um objeto do tipo book.
    $book = new book();

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
            $book->set(start, $start);
            // Define quantos registro o banco irá retornar na consulta.
            $book->set(limit, $limit);
            break;

        // Caso o usário queira uma pesquisa para saber as reservas que finalizam em uma data.
        case 'endDate':
            $book->set(endDate, $pg);
            break;

        // Caso o usário queira uma pesquisa para saber as reservas que iniciam em uma data.
        case 'startdate':
            $book->set(startdate, $pg);
            break;

        // Caso o usário queira uma pesquisa para saber as reservas que possuem um status especifico (passar satus).
        case 'status':
            $book->set(status, $pg);
            break;

        // Caso o usário queira uma pesquisa para saber as reservas de uma sala em especifico (passarid da sala).
        case 'classroom':
            $classroom = new classroom();
            if (is_numeric($id)) {
                $classroom->set(id, $pg);
                $book->set(classroom_id, $pg);
            } else {
                $classroom->set(_short, $pg);
                // Gera o comando SQL
                $sql = $classroom->ListClassRoom();

                // Instancia uma base de dados via PDO (padrão do PHP)
                $banco = new cPDO();
                // Executa o SQL
                $banco->query($sql);
                // Trasforma o retorno do banco em um array
                foreach ($banco->query($sql) as $rowClass) {
                    $w = $rowClass['id'];
                }
                $book->set(classroom_id, $w);
            }
            break;

        // Caso o usário queira uma pesquisa para saber as reservas de uma pessoa em especifico (passarid da pessoa que requisitou).
        case 'requestor':
            $book->set(requestor_id, $pg);
            break;

        // Caso a ID seja um número siginifica que o usuário está pesquisando pela ID da sala. Seta esse valor no objeto.
        default:
            if (is_numeric($id)) {
                $book->set(id, $id);
            }
            break;
    }

    // Gera o comando SQL
    $sql = $book->ListBook();

    // Instancia uma base de dados via PDO (padrão do PHP)
    $banco = new cPDO();
    // Executa o SQL
    $banco->query($sql);

    $bookArray = array();
    $periodArray = array();

    // $row = $banco->query($sql)->fetchAll();
    foreach ($banco->query($sql) as $row) {

        // Encontra a sala
        $classroom = new classroom();
        // Seta ID da sala
        if ($id == "classroom") {
            $classroom->set(id, $w);
        } else {
            $classroom->set(id, $row['classroom_id']);
        }
        $sqlClass = $classroom->ListClassRoom();
        // Instancia uma base de dados via PDO (padrão do PHP)
        $banco = new cPDO();
        // Executa o SQL
        $banco->query($sqlClass);
        // Trasforma o retorno do banco em um array
        foreach ($banco->query($sqlClass) as $rowClassroom) {
            $classroomArray = '{"id":"' . $rowClassroom['id'] . '","idxml":"' . $rowClassroom['idxml'] . '","name":"' . utf8_encode_all($rowClassroom['name']) . '","_short":"' . $rowClassroom['_short'] . '","capacity":"' . $rowClassroom['capacity'] . '","type":"' . $rowClassroom['type'] . '","building":"' . $rowClassroom['building'] . '","owner":"' . $rowClassroom['owner'] . '","bookable":"' . $rowClassroom['bookable'] . '"';
        }

        // Encontra o periodo
        // Instancia um objeto do tipo period.
        $period = new period();
        $period->set(id, $row['periods_id']);
        // Gera o comando SQL
        $sql = $period->ListPeriod();

        // Executa o SQL
        $banco->query($sql);

        foreach ($banco->query($sql) as $rowPeriod) {
            $periodArray = '{"id":"' . $rowPeriod['id'] . '","name":"' . $rowPeriod['name'] . '","_short":"' . $rowPeriod['_short'] . '","period":"' . $rowPeriod['period'] . '","starttime":"' . $rowPeriod['starttime'] . '","endtime":"' . $rowPeriod['endtime'] . '"';
        }

        $bookArray[] = '{"id":"' . $row['id'] . '","endDate":"' . $row['endDate'] . '","note":"' . $row['note'] . '","startdate":"' . $row['startdate'] . '","status":"' . $row['status'] . '","classroom_id":"' . $row['classroom_id'] . '","requestor_id":"' . $row['requestor_id'] . '","classroom":' . $classroomArray . '},"period":' . $periodArray . '}},';
    }

// Fazer o mesmo com esse cara;
//    $bookArray['requestor'] = $row;
//    $bookArray['requestor'] = $classroomArray;
//    echo json_encode(utf8_encode_all($book['requestor']));
    // Gera o JSON do objeto e imprime na página
    echo '[';
    for ($x = 0; $x < count($bookArray); $x++) {
        if ($x < count($bookArray) - 1) {
            echo $bookArray[$x];
        } else {
            echo substr($bookArray[$x], 0, -2);
        }
    }
    echo '}]';
//    echo "[" . $json_classroom . "]";
//    echo json_encode(utf8_encode_all($row));
}

function insertBookPeriod($Book_id, $periods_id) {
    // Instancia um objeto do tipo book.
    $book = new book();
    // Gera o comando SQL
    $sql = $book->insertBookPeriod($Book_id, $periods_id);

    // Instancia uma base de dados via PDO (padrão do PHP)
    $banco = new cPDO();

    // Tenta executar o SQL, se conseguir retorna id que Inseriu ou atualizou senão retorna 0
    if (!$banco->query($sql)) {
        echo 'Erro ao cadastrar reserva';
    } else {
        echo 'Reserva cadastrada com sucesso';
    }
}

/**
 * Função que insere ou atualiza uma resarva
 * @param type $id
 * @return ID inserida no banco ou 0 em caso de erro.
 */
function insertOrUpdate($id, $classroom_id, $endDate, $note, $startdate, $status, $classroom_id, $requestor_id, $periods_id) {

    // Instancia um objeto do tipo book.
    $book = new book();
    $book->set(id, $id);
    $book->set(classroom_id, $classroom_id);
    $book->set(endDate, $endDate);
    $book->set(note, $note);
    $book->set(startdate, $startdate);
    $book->set(status, $status);
    $book->set(classroom_id, $classroom_id);
    $book->set(requestor_id, $requestor_id);

    // Gera o comando SQL
    $sql = $book->insertOrUpdate();

    // Instancia uma base de dados via PDO (padrão do PHP)
    $banco = new cPDO();

    // Tenta executar o SQL, se conseguir retorna id que Inseriu ou atualizou senão retorna 0
    if (!$banco->query($sql)) {
        echo '0';
    } else {
        if ($id > 0) {
            echo $id;
        } else {
            $sql = $book->lastId();
            foreach ($banco->query($sql) as $rowLastId) {
                $lastId = $rowLastId['id'];
            }
            insertBookPeriod($lastId, $periods_id);
        }
    }
}

function delete($id) {

    // Instancia um objeto do tipo book.
    $book = new book();
    $book->set(id, $id);

    // Gera o comando SQL
    $sql = $book->delete();

    // Instancia uma base de dados via PDO (padrão do PHP)
    $banco = new cPDO();

    $banco->query($sql);

    // Tenta executar o SQL, se conseguir retorna id que excluiu senão retorna 0
    if (!$banco->query($sql)) {
        echo '0';
    } else {
        echo $id;
    }
}

?>