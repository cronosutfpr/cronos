package br.edu.utfpr.cm.cronosmobile.model;

import java.io.Serializable;


/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class ClassRoom implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Usado para pegar o nome dos atributos da classe
    public static String ID 		= "id";
    public static String IDXML 		= "idxml";
    public static String NAME 		= "name";
    public static String _SHORT		= "_short";
    public static String BOOKABLE	= "bookable";
    public static String BUILDING	= "building";
    public static String TYPE		= "type";
    public static String STATUS 	= "status";
    public static String CAPACITY	= "capacity";
    public static String OWNER_ID	= "owner_id";

    // Isso é usado para a criação de banco de dados (Facilita nos comandos)
    public static String[] colunas = new String[] { 
		ClassRoom.ID, 
		ClassRoom.IDXML, 
		ClassRoom.NAME, 
		ClassRoom._SHORT, 
		ClassRoom.BOOKABLE, 
		ClassRoom.BUILDING, 
		ClassRoom.TYPE, 
		ClassRoom.STATUS, 
		ClassRoom.CAPACITY, 
		ClassRoom.OWNER_ID 
    };
	
    private String id;
    private String idxml;
    private String name;
    private String _short;
    private String bookable;
    private String building;
    private String type;
    private String status;
    private String capacity;
    private String owner_id;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdxml() {
		return idxml;
	}

	public void setIdxml(String idxml) {
		this.idxml = idxml;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String get_short() {
		return _short;
	}

	public void set_short(String _short) {
		this._short = _short;
	}

	public String getBookable() {
		return bookable;
	}

	public void setBookable(String bookable) {
		this.bookable = bookable;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	@Override
	public String toString() {
		return this.name;
	}

	
}
