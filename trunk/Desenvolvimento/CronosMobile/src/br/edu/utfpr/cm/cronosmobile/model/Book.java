package br.edu.utfpr.cm.cronosmobile.model;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class Book {

	private String id;
	private ClassRoom classroom;
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
		return startdate + " - " + this.endDate;
	}

}
