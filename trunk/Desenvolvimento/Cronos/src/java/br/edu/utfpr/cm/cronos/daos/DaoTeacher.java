/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.Teacher;

/**
 *
 * @author Erick
 */
public class DaoTeacher extends DaoGenerics<Teacher> {

    public DaoTeacher() {
        super.alvo = Teacher.class;
    }

    
}