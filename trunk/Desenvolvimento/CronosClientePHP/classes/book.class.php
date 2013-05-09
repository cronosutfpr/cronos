<?php

/**
 * Classe que possui os métodos necessários para trabalhar com as salas.
 *
 * @author Willyan
 */
class book extends mysql {

    public $id;
    public $endDate;
    public $note;
    public $startdate;
    public $status;
    public $classroom_id;
    public $requestor_id;
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

    public function ListBook() {

        if ($this->id != '') {
            $id = "AND b.id = '$this->id'";
        }

        if ($this->endDate != '') {
            $endDate = "AND b.endDate = '$this->endDate'";
        }
        
        if ($this->classroom_id != '') {
            $classroom_id = "AND b.classroom_id = '$this->classroom_id'";
        }

        if ($this->note != '') {
            $note = "AND b.note = '$this->note'";
        }

        if ($this->startdate != '') {
            $startdate = "AND b.startdate = '$this->startdate'";
        }

        if ($this->status != '') {
            $status = "AND b.status = '$this->status'";
        }

        if ($this->classroom_id != '') {
            $classroom_id = "AND b.classroom_id = '$this->classroom_id'";
        }

        if ($this->requestor_id != '') {
            $requestor_id = "AND b.requestor_id = '$this->requestor_id'";
        }

        $sql = "SELECT b.* FROM book b
                WHERE b.id > 0 $id $classroom_id $endDate $note $startdate $status $classroom_id $requestor_id
                ORDER BY b.startdate ASC";

        if ($this->start != '' && $this->limit != '') {
            $start = $this->start;
            $end = $this->limit;
            $sql = $sql . " LIMIT " . $start . "," . $end . ";";
        }

        return $sql;
    }

    public function listBookCalendar() {

        $sql = "SELECT CONCAT(p.name, ' - ', b.startdate, ' - ', b.enddate) AS title, b.startdate, b.enddate FROM book b, period p WHERE b.id = p.id ORDER BY b.startdate ASC";

        return $sql;
    }

    function insertOrUpdate() {

        if ($this->id > 0) {
            if ($this->id != '') {
                $id = "id='$this->id' ";
            }

            if ($this->endDate != '') {
                $endDate = ", endDate='" . $this->endDate . "' ";
            }
            if ($this->note != '') {
                $note = ", note='" . $this->note . "' ";
            }

            if ($this->startdate != '') {
                $startdate = ", startdate = '$this->startdate'";
            }

            if ($this->status != '') {
                $status = ", status = '$this->status'";
            }

            if ($this->classroom_id != '') {
                $classroom_id = ", classroom_id = '$this->classroom_id'";
            }

            if ($this->requestor_id != '') {
                $requestor_id = ", requestor_id = '$this->requestor_id'";
            }

            $sql = "UPDATE classroom SET $id $endDate $note $startdate $status $classroom_id $requestor_id  WHERE id='$this->id' LIMIT 1";
        } else {
            $sql = "INSERT INTO classroom (id, endDate, note, startdate, status, classroom_id, requestor_id) 
                    VALUES (''," . $this->verificaNull(utf8_decode($this->endDate)) . ", " . $this->verificaNull(utf8_decode($this->note)) . ", " . $this->verificaNull(utf8_decode($this->startdate)) . ", " . $this->verificaNull(utf8_decode($this->status)) . ", " . $this->verificaNull(utf8_decode($this->classroom_id)) . ", " . $this->verificaNull(utf8_decode($this->requestor_id)) . ");";
        }
        return $sql;
    }

    function delete() {
        $sql = "DELETE FROM book WHERE id='$this->id' LIMIT 1";
        return $sql;
    }

}

?>
