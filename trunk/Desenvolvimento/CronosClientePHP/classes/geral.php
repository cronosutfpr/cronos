<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Description of geral
 *
 * @author ehric_000
 */

class geral {

    public $conexao;
	public $bd; 

    function __construct() {
       $this->conexao = mysql_connect("127.0.0.1", "root", "root");
	   $this->bd = mysql_select_db("cronos"); 
    }

    
    function sql_select($string){
        return mysql_query($string);
    }

}

?>
