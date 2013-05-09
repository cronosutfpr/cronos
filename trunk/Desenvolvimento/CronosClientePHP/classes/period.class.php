<?php

/**
 * Classe que possui os métodos necessários para trabalhar com os períodos.
 *
 * @author Willyan
 */
class period extends mysql {

    public $id;
    public $name;
    public $_short;
    public $period;
    public $starttime;
    public $endtime;
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

    public function ListPeriod() {

        if ($this->id != '') {
            $id = "AND p.id = '$this->id'";
        }

        if ($this->name != '') {
            $name = "AND p.name = '$this->name'";
        }

        if ($this->_short != '') {
            $_short = "AND p._short = '$this->_short'";
        }

        if ($this->period != '') {
            $period = "AND p.period = '$this->period'";
        }

        if ($this->starttime != '') {
            $starttime = "AND p.starttime = '$this->starttime'";
        }

        if ($this->endtime != '') {
            $endtime = "AND p.endtime = '$this->endtime'";
        }

        $sql = "SELECT p.* FROM period p
                WHERE p.id > 0 $id $name $_short $period $starttime $endtime
                ORDER BY p.starttime ASC";

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

            if ($this->name != '') {
                $name = ", p.name = '$this->name'";
            }

            if ($this->_short != '') {
                $_short = ", _short = '$this->_short'";
            }

            if ($this->period != '') {
                $period = ", period = '$this->period'";
            }

            if ($this->starttime != '') {
                $starttime = ", starttime = '$this->starttime'";
            }

            if ($this->endtime != '') {
                $endtime = ", endtime = '$this->endtime'";
            }

            $sql = "UPDATE period SET $id $name $_short $period $starttime $endtime  WHERE id='$this->id' LIMIT 1";
        } else {
            $sql = "INSERT INTO period (id, name, _short, period, starttime, endtime) 
                    VALUES (''," . $this->verificaNull(utf8_decode($this->name)) . ", " . $this->verificaNull(utf8_decode($this->_short)) . ", " . $this->verificaNull(utf8_decode($this->period)) . ", " . $this->verificaNull(utf8_decode($this->starttime)) . ", " . $this->verificaNull(utf8_decode($this->endtime)) . ");";
        }
        return $sql;
    }

    function delete() {
        $sql = "DELETE FROM period WHERE id='$this->id' LIMIT 1";
        return $sql;
    }

}

?>
