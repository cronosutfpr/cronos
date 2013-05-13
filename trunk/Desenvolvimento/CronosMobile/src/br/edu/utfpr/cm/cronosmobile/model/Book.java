package br.edu.utfpr.cm.cronosmobile.model;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
@SuppressLint("SimpleDateFormat")
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private ClassRoom classroom;
	private Period period;
	private String requestor;
	private String String;
	private String startdate;
	private String endDate;
	private String status;
	private String note;

	public Book() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// gerar esse objeto no webservice
	public ClassRoom getClassroom() {
		return classroom;
	}

	public void setClassroom(ClassRoom classroom) {
		this.classroom = classroom;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getString() {
		return String;
	}

	public void setString(String String) {
		this.String = String;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	    
	    String startdate = this.startdate;
	    String enddate = this.endDate;
	    
	    try {
	    	Date d1 = sdf1.parse(startdate);
	    	sdf1.applyPattern("dd/MM/yyyy");
			startdate = sdf1.format(d1);

			Date d2 = sdf2.parse(enddate);
			sdf2.applyPattern("dd/MM/yyyy");
			enddate = sdf2.format(d2);
			
		} catch (ParseException e) {
		}
	    
	    String sala = this.classroom.get_short();
	    String periodo = this.period.getName();
	    
		return sala.trim() + "-" + periodo.trim() + " - " + startdate + "-"+ enddate;
		
	}

}
