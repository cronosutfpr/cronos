package br.edu.utfpr.cm.cronosmobile.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.persistence.Sqlite;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class PrincipalActivity extends Activity {

	private Button btSair;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		this.btSair	= (Button) findViewById(R.id.btSair);
		
		btSair.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onDestroy();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Sqlite.fechar();
		finish();
	}
}
