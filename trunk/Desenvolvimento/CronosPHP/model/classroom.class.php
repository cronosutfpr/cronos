<?php

/**
 * Description of classroom
 *
 * @author Willyan
 */
class classroom extends mysql {
    
    public $id;
    public $idxml;
    public $name;
    public $_short;
    public $capacity;
    public $type;
    public $building;
    public $owner;
    public $bookable;
    
    //Método para atribuir valores às propriedades da classe.
    function set($prop, $value) {
        $this->$prop = $value;
    }

    //Método para verificar se o valor que estão vindo vazio para campos NULL.
    function verificaNull($value) {
        if ($value != '') {
            return "'" . $value . "'";
        } else {
            return "NULL";
        }
    }

    public function ListaClassRoom() {
        
        if ($this->id != '') {
            $id = "AND c.id = '$this->id'";
        }
        
        if ($this->idxml != '') {
            $idxml = "AND c.idxml = '$this->idxml'";
        }
        
        $sql = "SELECT c.* FROM classroom c
                WHERE c.id > 0 
                ORDER BY c.name ASC;";
        
        return $sql;
        
    }
}

?>
