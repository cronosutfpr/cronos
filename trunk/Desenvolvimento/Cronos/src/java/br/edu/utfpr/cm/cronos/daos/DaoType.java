/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.Type;

/**
 *
 * @author Erick
 */
public class DaoType extends DaoGenerics<Type> {

    public DaoType() {
        super.alvo = Type.class;
    }

    
}