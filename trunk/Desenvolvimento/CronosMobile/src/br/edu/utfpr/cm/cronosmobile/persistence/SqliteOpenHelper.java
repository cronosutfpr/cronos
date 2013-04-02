package br.edu.utfpr.cm.cronosmobile.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class SqliteOpenHelper extends SQLiteOpenHelper  {

	private String[] scriptSQLCreate = null;
	private String scriptSQLDelete = null;
	
	public SqliteOpenHelper(Context context, String nomeBanco , int version, String[] scriptSQLCreate, String scriptSQLDelete) {
		super(context, nomeBanco, null, version);
	    this.scriptSQLCreate = scriptSQLCreate;
	    this.scriptSQLDelete = scriptSQLDelete;
		
	}

	@Override  
	public void onCreate(SQLiteDatabase db) {
	    Log.i("Banco de Dados", "Criando Banco...");
	    int quantidadeScripts = scriptSQLCreate.length;
	    
	    // Usado for porque vem um array de comandos sql 
	    for(int i = 0; i < quantidadeScripts; i++){
	        String sql = scriptSQLCreate[i];
	        db.execSQL(sql);
	    }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.i("Banco de Dados", "Atualizando a versão " + oldVersion + " para " + newVersion + ". Todos os registros serão deletados.");
	    db.execSQL(scriptSQLDelete);
	    onCreate(db);  
	}

}
