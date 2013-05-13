package br.edu.utfpr.cm.cronosmobile.model;

import java.io.Serializable;
/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String login;
	private TypeUser type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public TypeUser getType() {
		return type;
	}

	public void setType(TypeUser type) {
		this.type = type;
	}
}
