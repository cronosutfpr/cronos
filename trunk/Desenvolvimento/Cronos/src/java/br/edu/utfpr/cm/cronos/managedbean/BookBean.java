/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoBook;
import br.edu.utfpr.cm.cronos.daos.DaoPeriod;
import br.edu.utfpr.cm.cronos.model.Book;
import br.edu.utfpr.cm.cronos.model.Period;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author junior
 */
@ManagedBean(name = "bookBean")
@SessionScoped
public class BookBean {
    Book book;

    public BookBean() {
        book = new Book();
        book.setClassroom(TableClassRoom.selectedClassRoom);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String addBook() {
        DaoBook daoBook = new DaoBook();
        daoBook.persistir(this.book);
        this.book = new Book();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Gravado"));
        return "lista_reserva_salas";

    }

    public List<Period> getPeriods() {
        DaoPeriod daoP = new DaoPeriod();
        return daoP.listar();
    }

}
