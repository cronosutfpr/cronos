package br.edu.utfpr.cm.cronosmobile.persistence;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.edu.utfpr.cm.cronosmobile.model.Administrador;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class AdministradorDAO implements Dao<Administrador> {

	private static final String NOME_BANCO  = Sqlite.NOME_BANCO;
	private static final String NOME_TABELA = "administradores";

	protected static SQLiteDatabase db;
	private static Context context;

	public AdministradorDAO(Context ctx) {
		context = ctx;
	}

	@Override
	public int salvar(Administrador objAdministrador) {

		int id = objAdministrador.getId();

		if (id != 0) {
			atualizar(objAdministrador); // Atualizar
			Log.i("Atualizou id: ", id + "");
		} else {
			id = inserir(objAdministrador); // Inserir
			Log.i("Gravou com id: ", id + "");
		}

		return id;

	}

	@Override
	public int inserir(Administrador objAdministrador) {

		ContentValues values = new ContentValues();

		values.put(Administrador.LOGIN, objAdministrador.getLogin());
		values.put(Administrador.EMAIL, objAdministrador.getEmail());
		values.put(Administrador.SENHA, objAdministrador.getSenha());

		int id = inserir(values);

		return id;

	}

	public static int inserir(ContentValues valores) {

		openDataBase();

		int id = (int) db.insert(NOME_TABELA, "", valores);
		db.close();

		if (id > 0)
			return id;
		else
			return 0;
	}

	@Override
	public int atualizar(Administrador objAdministrador) {

		ContentValues values = new ContentValues();

		values.put(Administrador.SENHA, objAdministrador.getSenha());
		values.put(Administrador.EMAIL, objAdministrador.getEmail());
		values.put(Administrador.LOGIN, objAdministrador.getLogin());

		String _id = String.valueOf(objAdministrador.getId());
		String where = Administrador.ID + "=?";
		String[] whereArgs = new String[] { _id };
		int count = atualizar(values, where, whereArgs);

		return count;

	}

	public int atualizar(ContentValues valores, String where, String[] whereArgs) {

		openDataBase();

		int count = db.update(NOME_TABELA, valores, where, whereArgs);
		db.close();

		return count;

	}

	@Override
	public int excluir(int id) {

		String where = Administrador.ID + "=?";
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };
		int count = excluir(where, whereArgs);

		return count;

	}

	public int excluir(String where, String[] whereArgs) {

		openDataBase();

		int count = db.delete(NOME_TABELA, where, whereArgs);
		db.close();

		return count;

	}

	@Override
	public Administrador editar(int id) {

		openDataBase();
		Cursor c = db.query(true, NOME_TABELA, Administrador.colunas,
				Administrador.ID + "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {

			c.moveToFirst();

			Administrador adm = new Administrador();

			adm.setId(c.getInt(0));
			adm.setEmail(c.getString(1));
			adm.setLogin(c.getString(2));
			adm.setSenha(c.getString(3));

			return adm;

		}

		c.close();
		db.close();

		return null;

	}

	@Override
	public ArrayList<Administrador> listarTodos() {

		openDataBase();
		Cursor c = db.query(true, NOME_TABELA, Administrador.colunas, null,
				null, null, null, null, null);

		ArrayList<Administrador> listAdministrador = new ArrayList<Administrador>();

		if (c.moveToFirst()) {

			int id = c.getColumnIndex(Administrador.ID);
			int email = c.getColumnIndex(Administrador.EMAIL);
			int login = c.getColumnIndex(Administrador.LOGIN);
			int senha = c.getColumnIndex(Administrador.SENHA);

			do {
				Administrador objAdministrador = new Administrador();
				objAdministrador.setId(c.getInt(id));
				objAdministrador.setEmail(c.getString(email));
				objAdministrador.setLogin(c.getString(login));
				objAdministrador.setSenha(c.getString(senha));
				listAdministrador.add(objAdministrador);
			} while (c.moveToNext());
		}

		c.close();
		db.close();

		return listAdministrador;

	}

	public boolean validarUsuario(Administrador adm) {

		openDataBase();
		
		Cursor c = db.query(false, NOME_TABELA, Administrador.colunas, Administrador.SENHA + "=\"" + adm.getSenha() + "\" AND " + Administrador.LOGIN + "=\"" + adm.getLogin() + "\"", null, null, null, null, null);

		if (c.getCount() > 0) {
			c.close();
			db.close();
			return true;
		} else {
			c.close();
			db.close();
			return false;
		}

	}

	public Cursor getAdministradoresCursor() {

		openDataBase();
		Cursor c = db.query(true, NOME_TABELA, Administrador.colunas, null, null, null, null, null, null);
		db.close();

		return c;

	}

	public static void openDataBase() {
		db = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}

}
