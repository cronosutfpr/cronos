package br.edu.utfpr.cm.cronosmobile.model;


/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class ClassRoom {

    // Usado para pegar o nome dos atributos da classe
    public static String ID 	= "id";
    public static String IDXML 	= "idxml";
    public static String NAME 	= "name";
    public static String _SHORT	= "_short";

    // Isso é usado para a criação de banco de dados (Facilita nos comandos)
    public static String[] colunas = new String[] { 
		ClassRoom.ID, 
		ClassRoom.IDXML, 
		ClassRoom.NAME, 
		ClassRoom._SHORT 
    };
	
    private String id;
    private String idxml;
    private String name;
    private String _short;
    
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

	@Override
	public String toString() {
		return this.name;
	}

	
}
