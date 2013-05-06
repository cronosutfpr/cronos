package br.edu.utfpr.cm.cronosmobile.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.model.Administrador;
import br.edu.utfpr.cm.cronosmobile.persistence.AdministradorDAO;
import br.edu.utfpr.cm.cronosmobile.persistence.SqliteController;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 * O Objetivo desta classe é inicialização do Banco de Dados,
 * criando as tabelas e fazendo as inserções necessárias.
 *
 */
public class SplashView extends Activity implements Runnable {

	private SqliteController sqliteController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		this.setSqliteController(new SqliteController(getApplicationContext()));

		
		AdministradorDAO administradorDAO = new AdministradorDAO(getApplicationContext());
		
		ArrayList<Administrador> arrayListAdm = new ArrayList<Administrador>();
		arrayListAdm = administradorDAO.listarTodos();
		
		// Caso não tenha um administrador, o sistema cria um para poder testar
		if (arrayListAdm.size() > 0) {
		    Log.i("Administradores: ", arrayListAdm.size() + "");
		} else {
			// Cria um administrador para testes quando inicia o sistema.
			criaAdministrador("wyworak", "wyworak@gmail.com", "123");
			Log.i("Banco de Dados", "Criou um Administrador");
		}
		
		// Neste caso, aguarda 3 segundo e executa o handler.
		Handler h = new Handler();
		h.postDelayed(this, 3000);

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.slpah, menu);
		return true;
	}

	/**
	 * O método run será executado após o Handler agurardar o tempo determinado pelo método postDelayed.
	 */
	@Override
	public void run() {
		startActivity(new Intent(this, LoginView.class));
		finish();
	}

	private void criaAdministrador(String login, String email, String senha) {

		Administrador a = new Administrador();
		AdministradorDAO ad = new AdministradorDAO(getApplicationContext());

		a.setLogin(login);
		a.setEmail(email);
		a.setSenha(senha);

		ad.salvar(a);

	}


	public SqliteController getSqliteController() {
		return sqliteController;
	}


	public void setSqliteController(SqliteController sqliteController) {
		this.sqliteController = sqliteController;
	}

}
