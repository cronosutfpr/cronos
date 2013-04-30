package br.edu.utfpr.cm.cronosmobile.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.persistence.Sqlite;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class PrincipalActivity extends ListActivity {

    private Button btSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// setContentView(R.layout.menu);

	String[] opcoes = new String[] { 
			"Listar Salas", 
			"Listar Professores",
			"Listar Disciplinas" 
			};

	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes));

	ListView listView = getListView();
	listView.setTextFilterEnabled(true);

	// this.btSair = (Button) findViewById(R.id.btSair);
	//
	// btSair.setOnClickListener(new View.OnClickListener() {
	// public void onClick(View v) {
	// onDestroy();
	// }
	// });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

	// //Mostrar item selecionado
	// String selectedValue = (String) getListAdapter().getItem(position);
	// Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

	switch (position) {
	case 0:
	    Intent intent = new Intent(PrincipalActivity.this, ClassRoomActivity.class);
	    startActivity(intent);
	    break;

	default:
	    Toast.makeText(this, "Não implementado", Toast.LENGTH_SHORT).show();
	    break;
	}
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
