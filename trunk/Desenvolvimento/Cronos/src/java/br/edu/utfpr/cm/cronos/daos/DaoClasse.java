/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.Classe;

/**
 *
 * @author Erick
 */
public class DaoClasse extends DaoGenerics<Classe> {

    public DaoClasse() {
        super.alvo = Classe.class;
    }

    
}