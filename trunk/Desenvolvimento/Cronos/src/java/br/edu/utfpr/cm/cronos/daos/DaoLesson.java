/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.Lesson;

/**
 *
 * @author Erick
 */
public class DaoLesson extends DaoGenerics<Lesson> {

    public DaoLesson() {
        super.alvo = Lesson.class;
    }

    
}