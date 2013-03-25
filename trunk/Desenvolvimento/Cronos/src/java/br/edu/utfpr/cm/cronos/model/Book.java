/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cm.cronos.model;

import java.util.Calendar;

/**
 *
 * @author junior
 */
public class Book {

    private int id;
    private ClassRooms classroom;
    private User requestor;
    private Periods period;
    private Calendar startdate;
    private Calendar endDate;
    private BookStatus status;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassRooms getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassRooms classroom) {
        this.classroom = classroom;
    }

    public User getRequestor() {
        return requestor;
    }

    public void setRequestor(User requestor) {
        this.requestor = requestor;
    }

    public Periods getPeriod() {
        return period;
    }

    public void setPeriod(Periods period) {
        this.period = period;
    }

    public Calendar getStartdate() {
        return startdate;
    }

    public void setStartdate(Calendar startdate) {
        this.startdate = startdate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
