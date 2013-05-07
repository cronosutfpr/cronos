<?php

/**
 * Classe que possui os métodos necessários para trabalhar com as salas.
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
    public $start;
    public $limit;

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

    public function ListClassRoom() {

        if ($this->id != '') {
            $id = "AND c.id = '$this->id'";
        }

        if ($this->idxml != '') {
            $idxml = "AND c.idxml = '$this->idxml'";
        }

        if ($this->name != '') {
            $name = "AND c.name = '$this->name'";
        }

        if ($this->_short != '') {
            $_short = "AND c._short = '$this->_short'";
        }

        if ($this->capacity != '') {
            $capacity = "AND c.capacity = '$this->capacity'";
        }

        if ($this->type != '') {
            $type = "AND c.type = '$this->type'";
        }

        if ($this->building != '') {
            $building = "AND c.building = '$this->building'";
        }

        if ($this->owner != '') {
            $owner = "AND c.owner_id = '$this->owner'";
        }

        if ($this->bookable != '') {
            $bookable = "AND c.bookable = '$this->bookable'";
        }

        $sql = "SELECT c.* FROM classroom c
                WHERE c.id > 0 $id $idxml $name $_short $capacity $type $building $owner $bookable
                ORDER BY c.name ASC";

        if ($this->start != '' && $this->limit != '') {
            $start = $this->start;
            $end = $this->limit;
            $sql = $sql . " LIMIT " . $start . "," . $end . ";";
        }

        return $sql;
    }

    function insertOrUpdate() {

        if ($this->id > 0) {
            if ($this->id != '') {
                $id = "id='$this->id' ";
            }
            if ($this->idxml != '') {
                $idxml = ", idxml='$this->idxml' ";
            }
            if ($this->name != '') {
                $name = ", name='" . $this->name . "' ";
            }
            if ($this->_short != '') {
                $_short = ", _short='" . $this->_short . "' ";
            }

            if ($this->capacity != '') {
                $capacity = ", capacity = '$this->capacity'";
            }

            if ($this->type != '') {
                $type = ", type = '$this->type'";
            }

            if ($this->building != '') {
                $building = ", building = '$this->building'";
            }

            if ($this->owner != '') {
                $owner = ", owner_id = '$this->owner'";
            }

            if ($this->bookable != '') {
                $bookable = ", bookable = '$this->bookable'";
            }

            $sql = "UPDATE classroom SET $id $idxml $name $_short $capacity $type $building $owner $bookable WHERE id='$this->id' LIMIT 1";
        } else {
            $sql = "INSERT INTO classroom (id, idxml, name, _short, capacity, type, building, owner_id, bookable) 
                    VALUES (''," . $this->verificaNull($this->idxml) . ", " . $this->verificaNull(utf8_decode($this->name)) . ", " . $this->verificaNull(utf8_decode($this->_short)) . ", " . $this->verificaNull(utf8_decode($this->capacity)) . ", " . $this->verificaNull(utf8_decode($this->type)) . ", " . $this->verificaNull(utf8_decode($this->building)) . ", " . $this->verificaNull(utf8_decode($this->owner)) . ", " . $this->verificaNull(utf8_decode($this->bookable)) . ");";
        }
        return $sql;
    }

    function delete() {
        $sql = "DELETE FROM classroom WHERE id='$this->id' LIMIT 1";
        return $sql;
    }

}

?>
