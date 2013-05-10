/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.managedbean;

import br.edu.utfpr.cm.cronos.daos.DaoBook;
import br.edu.utfpr.cm.cronos.daos.DaoPeriod;
import br.edu.utfpr.cm.cronos.model.Book;
import br.edu.utfpr.cm.cronos.model.Period;
import br.edu.utfpr.cm.cronos.util.Utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
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
        if (this.book != null) {
            if (daoBook.validarReserva(this.book)) {
                daoBook.persistir(this.book);
                this.book = new Book();
                FacesContext context = FacesContext.getCurrentInstance();

                context.addMessage(null, new FacesMessage("A reserva foi efetuada com sucesso!", ""));
                buildScheduleModel();
                book = new Book();
                return "lista_reserva_salas";
            }
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Não foi possível efetuar a reserva, pois a sala já está reservada nesse período!", ""));
        }
        return "";

    }

    public void buildScheduleModel() {
        List<Book> books = new DaoBook().obterPorClassRoom(TableClassRoom.selectedClassRoom.getId());
        for (Book book : books) {
            eventModel.addEvent(new DefaultScheduleEvent(book.getPeriod().getStarttime() + " - " + book.getPeriod().getEndtime(), book.getStartdate(), book.getEndDate()));
        }

    }

    public List<Period> getPeriods() {
        DaoPeriod daoP = new DaoPeriod();
        return daoP.listar();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        book.setStartdate((Date) selectEvent.getObject());
    }

    public String today() {
        return Utils.formatDatePtBR(new Date());
    }

    public String thisYear() {
        Calendar cal = Calendar.getInstance();  
        return "31-12-"+cal.get(Calendar.YEAR);
    }
}
