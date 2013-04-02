package br.edu.utfpr.cm.cronosmobile.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class Sqlite {
	public static final String NOME_BANCO = "cronos_mobile_db";
    private static final int VERSAO_BANCO = 1;
    private static SqliteOpenHelper dbHelper;
    private static SQLiteDatabase db;
    
    // Todas as tabelas colocadas nessa variavel são excluidas.
    private static final String SCRIPT_DATABASE_DELETE = "DROP DATABASE IF EXISTS " + Sqlite.NOME_BANCO;
    // Colocar todos os comandos de criação de tabelas nessa variável.
    private static final String[] SCRIPT_DATABASE_CREATE = new String[] { 
    	"create table contatos (_id integer primary key autoincrement, nome text not null, telefone text not null);",
    	"create table administradores (_id integer primary key autoincrement, login text not null, email text not null, senha text not null);"
	};

    public Sqlite(Context ctx) {
		dbHelper = new SqliteOpenHelper(ctx, Sqlite.NOME_BANCO, Sqlite.VERSAO_BANCO, Sqlite.SCRIPT_DATABASE_CREATE, Sqlite.SCRIPT_DATABASE_DELETE);
		db = dbHelper.getWritableDatabase();
    }

    public static void fechar() {
	if (dbHelper != null)
	    db = dbHelper.getReadableDatabase();
	// dbHelper.close();

	if (db != null) {
	    db.close();

	}
    }
}
