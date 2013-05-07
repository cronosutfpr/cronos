package br.edu.utfpr.cm.cronosmobile.model;

/**
 * 
 * @author Willyan Schultz Dworak
 * 
 */
public class Period {

	// Usado para pegar o nome dos atributos da classe
    public static String ID 		= "id";
    public static String NAME 		= "name";
    public static String _SHORT		= "_short";
    public static String PERIOD		= "period";
    public static String START_TIME	= "starttime";
    public static String END_TIME	= "endtime";
    
    // Isso é usado para a criação de banco de dados (Facilita nos comandos)
    public static String[] colunas = new String[] { 
    	Period.ID, 
    	Period.NAME, 
    	Period._SHORT, 
    	Period.PERIOD, 
    	Period.START_TIME, 
    	Period.END_TIME 
    };
    
	private Long id;
	private String name;
	private String _short;
	private int period;
	private String starttime;
	private String endtime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShort() {
		return _short;
	}

	public void setShort(String _short) {
		this._short = _short;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

}
