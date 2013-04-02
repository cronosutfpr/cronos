/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.Period;

/**
 *
 * @author Erick
 */
public class DaoPeriod extends DaoGenerics<Period> {

    public DaoPeriod() {
        super.alvo = Period.class;
    }

    
}