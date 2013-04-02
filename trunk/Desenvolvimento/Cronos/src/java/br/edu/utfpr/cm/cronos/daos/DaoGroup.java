/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.Group;

/**
 *
 * @author Erick
 */
public class DaoGroup extends DaoGenerics<Group> {

    public DaoGroup() {
        super.alvo = Group.class;
    }

    
}