/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.TypeUser;

/**
 *
 * @author Erick
 */
public class DaoTypeUser extends DaoGenerics<TypeUser> {

    public DaoTypeUser() {
        super.alvo = TypeUser.class;
    }

    
}