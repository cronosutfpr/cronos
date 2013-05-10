package br.edu.utfpr.cm.cronosmobile.model;


/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class Book {

	private Long id;
//	private ClassRoom classroom;
//	private Long requestor;
//	private Period period;
	private String startdate;
	private String endDate;
//	private BookStatus status;
//	private String note;

	public Book() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
// gerar esse objeto no webservice
//	public ClassRoom getClassroom() {
//		return classroom;
//	}
//
//	public void setClassroom(ClassRoom classroom) {
//		this.classroom = classroom;
//	}

//	public User getRequestor() {
//		return requestor;
//	}
//
//	public void setRequestor(User requestor) {
//		this.requestor = requestor;
//	}

//	public Period getPeriod() {
//		return period;
//	}
//
//	public void setPeriod(Period period) {
//		this.period = period;
//	}

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

//	public BookStatus getStatus() {
//		return status;
//	}
//
//	public void setStatus(BookStatus status) {
//		this.status = status;
//	}
//
//	public String getNote() {
//		return note;
//	}
//
//	public void setNote(String note) {
//		this.note = note;
//	}
//
	@Override
	public String toString() {
		return  startdate + " - " + this.endDate;
	}
	
	
}
