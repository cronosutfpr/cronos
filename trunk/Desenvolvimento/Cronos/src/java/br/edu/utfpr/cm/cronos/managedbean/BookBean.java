/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoBook;
import br.edu.utfpr.cm.cronos.model.Book;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author junior
 */
@ManagedBean(name="bookBean")
@SessionScoped
public class BookBean {
    
    Book book;

    public BookBean() {
   book =  new Book();
    }

    
    
    
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String addClassRoom() {
        DaoBook daoBook = new DaoBook();
       daoBook.persistir(this.book);
        
       this.book = new Book();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Gravado"));
        return "lista_reserva_salas";

    }

}
