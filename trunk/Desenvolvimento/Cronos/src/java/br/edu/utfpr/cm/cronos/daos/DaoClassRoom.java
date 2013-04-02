/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.ClassRoom;

/**
 *
 * @author Erick
 */
public class DaoClassRoom extends DaoGenerics<ClassRoom> {

    public DaoClassRoom() {
        super.alvo = ClassRoom.class;
    }

    
}