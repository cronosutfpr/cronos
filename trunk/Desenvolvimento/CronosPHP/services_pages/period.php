<?php

error_reporting(false);

header('content-type: application/json; charset=utf-8');
require_once('../model/funcoes.php');
require_once('../model/pdo.class.php');
require_once('../model/mysql.class.php');
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

// Caso tenha algum parametro para tarefa pegar os valores aqui.
if ($task != "") {
    $id = htmlspecialchars(strip_tags($_POST['id']));
}

// Caso o parametro venha via GET ele é setado na variavel 'task'.
if ($read != "") {
    $task = $read[0];
}


// De acordo com a tarefa definida no 'task' será executada uma ação dentro do switch.
switch ($task) {

    // Insere um período de acordo com os parametros passados pelo usuário via POST ou PUT
    case 'insert';
        insertOrUpdatePeriod();
        break;

    // Caso o usuário envie uma requisição via GET com o parametro 'list' serão listadas as salas de acordo com os paramtros que ele passar na URL
    case "list":
        listPeriod($id, $pg);
        break;

    // Atualiza um período de acordo com os parametros passados pelo usuário via POST ou UPDATE
    case 'update';
        insertOrUpdatePeriod();
        break;

    // Deleta um período de acordo com os parametros passados pelo usuário via POST ou DELETE.
    case 'delete';
        deletePeriod($id);
        break;

    // Caso o usuário não passe nada por parametro serão exibidos todos os periodos.
    default:
        listPeriod($id, $pg);
        break;
}

function listPeriod($id, $pg) {

    // Variáveis para controle de paginação.
    $start = 0;
    $limit = 10;

    // Instancia um objeto do tipo period.
    $period = new period();

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
            $period->set(start, $start);
            // Define quantos registro o banco irá retornar na consulta.
            $period->set(limit, $limit);
            break;

        // Caso o usuário decida procuara um periodo pelo 'periodo'
        case 'period':
            $period->set(period, $pg);
            break;

        // Caso o usuário decida procuara um periodo pela abreviação
        case 'short':
            $period->set(_short, $pg);
            break;

        // Caso o usuário decida procuara um periodo pela hora de finalização
        case 'endtime':
            $period->set(endtime, $pg);
            break;

        // Caso o usuário decida procuara um periodo pela hora de início
        case 'starttime':
            $period->set(starttime, $pg);
            break;

        // Caso o usuário decida procuara um periodo pelo nome
        case 'name':
            $period->set(name, $pg);
            break;

        // Caso a ID seja um número siginifica que o usuário está pesquisando pela ID do periodo. Seta esse valor no objeto.
        default:
            if (is_numeric($id)) {
                $period->set(id, $id);
            }
            break;
    }

    // Gera o comando SQL
    $sql = $period->ListPeriod();

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

?>