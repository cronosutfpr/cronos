package br.edu.utfpr.cm.cronosmobile.model;

import java.io.Serializable;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class Administrador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String ID 	= "_id";
	public static String LOGIN 	= "login";
	public static String EMAIL 	= "email";
	public static String SENHA 	= "senha";

	public static String[] colunas = new String[] { 
		Administrador.ID, 
		Administrador.LOGIN, 
		Administrador.EMAIL, 
		Administrador.SENHA 
	};

	private int id;
	private String login;
	private String email;
	private String senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
