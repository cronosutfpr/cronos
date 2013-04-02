package br.edu.utfpr.cm.cronosmobile.controller;

import android.content.Context;
import br.edu.utfpr.cm.cronosmobile.model.Administrador;
import br.edu.utfpr.cm.cronosmobile.persistence.AdministradorDAO;

public class AdministradorController {

	private Context context;
	private Administrador adm;

	public AdministradorController(Context context) {
		super();
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Administrador getAdm() {
		return adm;
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}

	public static boolean validarUsuario(Context context, Administrador adm) {

		boolean autenticou = false;
		AdministradorDAO ad = new AdministradorDAO(context);
		autenticou = ad.validarUsuario(adm);

		return autenticou;

	}

}
