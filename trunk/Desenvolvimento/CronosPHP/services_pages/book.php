<?php

error_reporting(false);

header('content-type: application/json; charset=utf-8');

require_once('../model/funcoes.php');
require_once('../model/pdo.class.php');
require_once('../model/mysql.class.php');
require_once('../model/book.class.php');

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
    $classroom_id   = htmlspecialchars(strip_tags($_POST['classroom_id']));
    $endDate        = htmlspecialchars(strip_tags($_POST['endDate']));
    $note           = htmlspecialchars(strip_tags($_POST['note']));
    $startdate      = htmlspecialchars(strip_tags($_POST['startdate']));
    $status         = htmlspecialchars(strip_tags($_POST['status']));
    $period_id      = htmlspecialchars(strip_tags($_POST['period_id']));
    $classroom_id   = htmlspecialchars(strip_tags($_POST['classroom_id']));
    $requestor_id   = htmlspecialchars(strip_tags($_POST['requestor_id']));
}

// Caso o parametro venha via GET ele é setado na variavel 'task'.
if ($read != "") {
    $task = $read[0];
}


// De acordo com a tarefa definida no 'task' será executada uma ação dentro do switch.
switch ($task) {

    // Insere uma sala de acordo com os parametros passados pelo usuário via POST ou PUT
    case 'insert';
        insertOrUpdate($id, $classroom_id, $endDate, $note, $startdate, $status, $classroom_id, $requestor_id, $period_id);
        break;

    // Caso o usuário envie uma requisição via GET com o parametro 'list' serão listadas as reservas de acordo com os paramtros que ele passar na URL
    case "list":
        listBook($id, $pg);
        break;

    // Atualiza uma sala de acordo com os parametros passados pelo usuário via POST ou UPDATE
    case 'update';
        insertOrUpdate($id, $classroom_id, $endDate, $note, $startdate, $status, $classroom_id, $requestor_id, $period_id);
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
            $book->set(classroom_id, $pg);
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
    echo $sql = $book->ListBook();

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
 * Função que insere ou atualiza uma resarva
 * @param type $id
 * @return ID inserida no banco ou 0 em caso de erro.
 */
function insertOrUpdate($id, $classroom_id, $endDate, $note, $startdate, $status, $classroom_id, $requestor_id, $period_id) {

    // Instancia um objeto do tipo book.
    $book = new book();
    $book->set(id, $id);
    $book->set(classroom_id, $classroom_id);
    $book->set(endDate, converte_data($endDate));
    $book->set(note, $note);
    $book->set(startdate, converte_data($startdate));
    $book->set(status, $status);
    $book->set(period_id, $period_id);
    $book->set(classroom_id, $classroom_id);
    $book->set(requestor_id, $requestor_id);

    // Gera o comando SQL
    $sql = $book->insertOrUpdate();

    // Instancia uma base de dados via PDO (padrão do PHP)
    $banco = new cPDO();
    
    //echo $sql;

    // Tenta executar o SQL, se conseguir retorna id que Inseriu ou atualizou senão retorna 0
    if(valida_data($startdate) == false){
        echo "Data de inicio inválida.";
    }else if(valida_data($endDate) == false){
        echo "Data de fim inválida.";
    }else if (!$banco->query($sql)) {
        echo 'Erro no cadastro.';
    } else {
        echo 'Cadastro realizado com sucesso.';
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