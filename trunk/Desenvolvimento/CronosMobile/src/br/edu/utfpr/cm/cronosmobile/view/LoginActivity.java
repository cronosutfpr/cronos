package br.edu.utfpr.cm.cronosmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.controller.AdministradorController;
import br.edu.utfpr.cm.cronosmobile.model.Administrador;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class LoginActivity extends Activity {

	private Button btEntrar;
	private EditText edUser;
	private EditText edSenha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		this.edUser 	= (EditText) findViewById(R.id.edUser);
		this.edSenha 	= (EditText) findViewById(R.id.edSenha);
		this.btEntrar	= (Button) findViewById(R.id.btEntrar);

		btEntrar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Administrador a = new Administrador();
				a.setLogin(edUser.getText().toString());
				a.setSenha(edSenha.getText().toString());

				if (AdministradorController.validarUsuario(getApplicationContext(), a)) {
					Intent intent = new Intent(LoginActivity.this,
					PrincipalActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(), "Usuário ou senha invalidos", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
