/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.ClassRoom;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Erick
 */
public class DaoClassRoom extends DaoGenerics<ClassRoom> {

    public DaoClassRoom() {
        super.alvo = ClassRoom.class;
    }

     public List<ClassRoom> ObterPorFiltro(String descricao, String tipo, String abreviatura, String capacidade, String reservavel ) {
        Query query = session.createQuery("FROM ClassRoom c where classroom.name like '%"+descricao+"%' or classroom.type like '%"+tipo+"%' or classroom._short like '%"+tipo+"%'  or classroom.capacity like '"+capacidade+"' or classroom.bookable = "+reservavel+" ");
        return query.list();
    }
}