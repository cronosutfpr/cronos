/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.Subject;

/**
 *
 * @author Erick
 */
public class DaoSubject extends DaoGenerics<Subject> {

    public DaoSubject() {
        super.alvo = Subject.class;
    }

    
}