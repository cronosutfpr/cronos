/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.listagens;

import br.edu.utfpr.cm.cronos.daos.DaoBook;
import br.edu.utfpr.cm.cronos.model.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author junior
 */
@ManagedBean
@SessionScoped
public class ListarReservas implements Serializable {

    private List<Book> books;
    public static Book selectedBook;
    private BookDataModel classRoomDataModel;
    private DaoBook daoBook;
    private List<Book> filteredBooks;
    private SelectItem[] itens;

    public ListarReservas() {
        books = new ArrayList<Book>();
        buscarBook();
        classRoomDataModel = new BookDataModel(books);
    }

    private void buscarBook() {
        daoBook = new DaoBook();
        books = daoBook.listar();
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public BookDataModel getBookDataModel() {
        return classRoomDataModel;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Sala selecionada", String.valueOf(((Book) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Sala não selecionada", String.valueOf(((Book) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Book> getFilteredBooks() {
        return filteredBooks;
    }

    public void setFilteredBooks(List<Book> filteredBooks) {
        this.filteredBooks = filteredBooks;
    }

    public SelectItem[] getItens() {
            buscarBook();
        return itens;
    }
}
