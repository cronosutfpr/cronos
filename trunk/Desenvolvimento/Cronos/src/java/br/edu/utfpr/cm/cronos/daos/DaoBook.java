/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.daos;

import br.edu.utfpr.cm.cronos.model.Book;

/**
 *
 * @author Erick
 */
public class DaoBook extends DaoGenerics<Book> {

    public DaoBook() {
        super.alvo = Book.class;
    }

    
}
