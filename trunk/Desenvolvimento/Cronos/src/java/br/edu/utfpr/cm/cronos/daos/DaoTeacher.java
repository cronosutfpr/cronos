/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.Teacher;
import org.hibernate.Query;

/**
 *
 * @author Erick
 */
public class DaoTeacher extends DaoGenerics<Teacher> {

    public DaoTeacher() {
        super.alvo = Teacher.class;
    }

    public Teacher obterPorNome(String nome) {
         Teacher teacher = null;
        if (nome != null) {
            Query select = session.createQuery("From "+alvo.getSimpleName()+" where name = '" + nome+"'");
            teacher = (Teacher) select.uniqueResult();
        }
        return teacher;
    }

    
}