<?php

date_default_timezone_set('America/Sao_Paulo');

function textoTamanho($texto, $casas) {
    $texto = substr($texto, 0, $casas);
    return $texto;
}

function convAsp($vp) {
    $vp = str_replace("'", " ", $vp);
    return $vp;
}

function paginacao($quantidade, $pagina, $tipo) {
    if (( $pagina == '' || $pagina == '1' ) && $tipo == 'S') {
        return '0';
    } else if (( $pagina == '' || $pagina == '1' ) && $tipo == 'L') {
        return $quantidade;
    }
    if (( $pagina > '1' ) && $tipo == 'S') {
        return ( $pagina - 1 ) * $quantidade;
    } else if (( $pagina > '1' ) && $tipo == 'L') {
        return $quantidade * $pagina;
    }
}

function tiraVP($vp) {
    $vp = str_replace(".", "", $vp);
    $vp = str_replace(",", ".", $vp);
    return $vp;
}

function convPV($vp) {
    $vp = str_replace(",", ".", $vp);
    return $vp;
}

function convVP($vp) {
    $vp = str_replace(".", ",", $vp);
    return $vp;
}

function tiraTudo($vpt) {
    $vpt = str_replace(".", "", $vpt);
    $vpt = str_replace(",", "", $vpt);
    $vpt = str_replace("/", "", $vpt);
    $vpt = str_replace("-", "", $vpt);
    $vpt = str_replace("(", "", $vpt);
    $vpt = str_replace(")", "", $vpt);
    $vpt = str_replace("*", "", $vpt);
    $vpt = str_replace(",", "", $vpt);
    $vpt = str_replace("+", "", $vpt);
    return $vpt;
}

function convTelefone($vp) {
    if (strlen($vp) == 10) {
        $vp = "(" . substr($vp, 0, 2) . ")" . substr($vp, 2, 4) . "-" . substr($vp, 6, 4);
    } else if (strlen($vp) == 11) {
        $vp = "(" . substr($vp, 0, 2) . ")" . substr($vp, 2, 5) . "-" . substr($vp, 7, 4);
    }
    return $vp;
}

function mascaraCpfCnpj($vp) {
    if (strlen($vp) == 11) {
        $vp = substr($vp, 0, 3) . "." . substr($vp, 3, 3) . "." . substr($vp, 6, 3) . "-" . substr($vp, 9, 2);
    } else if (strlen($vp) == 14) {
        $vp = substr($vp, 0, 2) . "." . substr($vp, 2, 3) . "." . substr($vp, 5, 3) . "/" . substr($vp, 8, 4) . "-" . substr($vp, 12, 2);
    }
    return $vp;
}

function convCep($vp) {
    $vp = substr($vp, 0, 2) . "." . substr($vp, 2, 3) . "-" . substr($vp, 5, 3);
    return $vp;
}

function verificaCpfCnpj($vp) {
    $vp = tiraTudo($vp);
    if (strlen($vp) == 11) {
        echo validaCPF($vp);
    } else if (strlen($vp) == 14) {
        echo validaCNPJ($vp);
    } else {
        echo 0;
    }
}

function validaCPF($cpf) {
    // Verifiva se o n??mero digitado cont??m todos os digitos
    $cpf = str_pad(preg_replace('[^0-9]', '', $cpf), 11, '0', STR_PAD_LEFT);

    // Verifica se nenhuma das sequ??ncias abaixo foi digitada, caso seja, retorna falso
    if (strlen($cpf) != 11 || $cpf == '00000000000' || $cpf == '11111111111' || $cpf == '22222222222' || $cpf == '33333333333' || $cpf == '44444444444' || $cpf == '55555555555' || $cpf == '66666666666' || $cpf == '77777777777' || $cpf == '88888888888' || $cpf == '99999999999') {
        return 0;
    } else {   // Calcula os n??meros para verificar se o CPF ?? verdadeiro
        for ($t = 9; $t < 11; $t++) {
            for ($d = 0, $c = 0; $c < $t; $c++) {
                $d += $cpf{$c} * (($t + 1) - $c);
            }
            $d = ((10 * $d) % 11) % 10;
            if ($cpf{$c} != $d) {
                return 0;
            }
        }
        return 1;
    }
}

function validaCNPJ($CampoNumero) {
    $RecebeCNPJ = ${"CampoNumero"};

    $s = "";
    for ($x = 1; $x <= strlen($RecebeCNPJ); $x = $x + 1) {
        $ch = substr($RecebeCNPJ, $x - 1, 1);
        if (ord($ch) >= 48 && ord($ch) <= 57) {
            $s = $s . $ch;
        }
    }

    $RecebeCNPJ = $s;
    if (strlen($RecebeCNPJ) != 14) {
        return 0;
    } else
    if ($RecebeCNPJ == "00000000000000") {
        $then;
        return 0;
    } else {
        $Numero[1] = intval(substr($RecebeCNPJ, 1 - 1, 1));
        $Numero[2] = intval(substr($RecebeCNPJ, 2 - 1, 1));
        $Numero[3] = intval(substr($RecebeCNPJ, 3 - 1, 1));
        $Numero[4] = intval(substr($RecebeCNPJ, 4 - 1, 1));
        $Numero[5] = intval(substr($RecebeCNPJ, 5 - 1, 1));
        $Numero[6] = intval(substr($RecebeCNPJ, 6 - 1, 1));
        $Numero[7] = intval(substr($RecebeCNPJ, 7 - 1, 1));
        $Numero[8] = intval(substr($RecebeCNPJ, 8 - 1, 1));
        $Numero[9] = intval(substr($RecebeCNPJ, 9 - 1, 1));
        $Numero[10] = intval(substr($RecebeCNPJ, 10 - 1, 1));
        $Numero[11] = intval(substr($RecebeCNPJ, 11 - 1, 1));
        $Numero[12] = intval(substr($RecebeCNPJ, 12 - 1, 1));
        $Numero[13] = intval(substr($RecebeCNPJ, 13 - 1, 1));
        $Numero[14] = intval(substr($RecebeCNPJ, 14 - 1, 1));

        $soma = $Numero[1] * 5 + $Numero[2] * 4 + $Numero[3] * 3 + $Numero[4] * 2 + $Numero[5] * 9 + $Numero[6] * 8 + $Numero[7] * 7 +
                $Numero[8] * 6 + $Numero[9] * 5 + $Numero[10] * 4 + $Numero[11] * 3 + $Numero[12] * 2;

        $soma = $soma - (11 * (intval($soma / 11)));

        if ($soma == 0 || $soma == 1) {
            $resultado1 = 0;
        } else {
            $resultado1 = 11 - $soma;
        }
        if ($resultado1 == $Numero[13]) {
            $soma = $Numero[1] * 6 + $Numero[2] * 5 + $Numero[3] * 4 + $Numero[4] * 3 + $Numero[5] * 2 + $Numero[6] * 9 +
                    $Numero[7] * 8 + $Numero[8] * 7 + $Numero[9] * 6 + $Numero[10] * 5 + $Numero[11] * 4 + $Numero[12] * 3 + $Numero[13] * 2;
            $soma = $soma - (11 * (intval($soma / 11)));
            if ($soma == 0 || $soma == 1) {
                $resultado2 = 0;
            } else {
                $resultado2 = 11 - $soma;
            }
            if ($resultado2 == $Numero[14]) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}

function formata_valor($valor = '', $casas = 2) {
    $valor = number_format($valor, $casas, ',', '.');
    return $valor;
}

function anti_injection($str) {
    $substituir = array();
    $substituir[0] = '/from/';
    $substituir[1] = '/select/';
    $substituir[2] = '/insert/';
    $substituir[3] = '/delete/';
    $substituir[4] = '/where/';
    $substituir[5] = '/drop table/';
    $substituir[6] = '/show tables/';
    $str = trim($str);
    $str = strip_tags($str);
    $str = preg_replace($substituir, "", $str);
    $str = (get_magic_quotes_gpc()) ? $str : addslashes($str);
    return $str;
}

function valida_data($data) {
    $data = explode("/", $data);
    if (!checkdate($data[1], $data[0], $data[2]) and !checkdate($data[1], $data[2], $data[0])) {
        return false;
    }
    return true;
}

function converte_data($data) {
    if (valida_data($data)) {
        return implode(!strstr($data, '/') ? "/" : "-", array_reverse(explode(!strstr($data, '/') ? "-" : "/", $data)));
    }
}

function converte_dataPg($data) {
    return substr($date, 0, 4) . "-" . substr($date, 5, 2) . "-" . substr($date, 8, 2) . " " . substr($date, 11, 2) . ":" . substr($date, 14, 2) . ":" . substr($date, 17, 2);
}

function convdata($date) {
    $bra = substr($date, 8, 2) . "/" . substr($date, 5, 2) . "/" . substr($date, 0, 4);
    return $bra;
}

/**
 * @param $to email que receber� a mensagem;
 * @param $subject Assunto do email;
 * @param $from Nome de quem est� enviando o email;
 * @param $emailRementente Remetente precisa ser uma caixa postal do mesmo dominio da hospedagem;
 * @param $message Mensagem a ser enviada;
 * @property Return-Path: Precisa ser uma caixa postal do mesmo dominio da hospedagem;
 */
function enviaEmail($to, $subject, $from, $emailRementente, $message) {
    $headers = "MIME-Version: 1.1\n";
    $headers .= 'Content-type: text/html; charset=iso-8859-1' . "\n";
    $headers .= 'From: ' . $from . ' <' . $emailRementente . '>' . "\n";
    $headers .= "Return-Path: '.$from.' <'.$emailRementente.'>\n";

    if (mail($to, $subject, $message, $headers)) {
        return true;
    } else {
        return false;
    }
}

?>