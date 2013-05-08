/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoBook;
import br.edu.utfpr.cm.cronos.daos.DaoPeriod;
import br.edu.utfpr.cm.cronos.model.Book;
import br.edu.utfpr.cm.cronos.model.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author junior
 */
@ManagedBean(name = "bookBean")
@SessionScoped
public class BookBean {

    Book book;
    public static ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    public BookBean() {
        book = new Book();
        book.setClassroom(TableClassRoom.selectedClassRoom);
        eventModel = new DefaultScheduleModel();
        //eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));  
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public String addBook() {
        DaoBook daoBook = new DaoBook();
        this.book.setClassroom(TableClassRoom.selectedClassRoom);
        daoBook.persistir(this.book);
        this.book = new Book();
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Successful", "Gravado"));
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }

        //event = new DefaultScheduleEvent();
        eventModel.addEvent(new DefaultScheduleEvent(this.book.getNote(), this.book.getStartdate(), this.book.getEndDate()));
        book = new Book();
        return "lista_reserva_salas";

    }

    public void buildScheduleModel() {
        List<Book> books = new DaoBook().obterPorClassRoom(TableClassRoom.selectedClassRoom.getId());
        for (Book b : books) {
            eventModel.addEvent(new DefaultScheduleEvent(b.getNote(), b.getStartdate(), b.getEndDate()));
        }
    }

    public List<Period> getPeriods() {
        DaoPeriod daoP = new DaoPeriod();
        return daoP.listar();
    }
}
