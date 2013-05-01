/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.controller;

import br.edu.utfpr.cm.cronos.model.Book;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.Schedule;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author junior
 */
public class BookScheduleEvent implements ScheduleEvent, Serializable{
    private Book book;

    public BookScheduleEvent(Book book) {
        this.book = book;
    }
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setId(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTitle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getStartDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getEndDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAllDay() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getStyleClass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEditable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
