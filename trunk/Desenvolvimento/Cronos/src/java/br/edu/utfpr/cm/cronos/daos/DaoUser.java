/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.User;

/**
 *
 * @author Erick
 */
public class DaoUser extends DaoGenerics<User> {

    public DaoUser() {
        super.alvo = User.class;
    }

    
}
