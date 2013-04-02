package br.edu.utfpr.cm.cronosmobile.persistence;

import android.content.Context;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class SqliteController {

	Sqlite sqlite;

	public SqliteController(Context context) {
		this.sqlite = new Sqlite(context);
	}

	public void fechar() {
		Sqlite.fechar();
	}

}
