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
public class PrincipalView extends ListActivity {

    private Button buttonSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// setContentView(R.layout.menu);

	String[] opcoes = new String[] { 
			"Pesquisar Salas", 
			"Reservar Salas",
			"Pesquisar Reservas",
			"Sair"
			};

	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoes));

	ListView listView = getListView();
	listView.setTextFilterEnabled(true);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

	// //Mostrar item selecionado
	// String selectedValue = (String) getListAdapter().getItem(position);
	// Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

	switch (position) {
		case 0:
			// Cria a Intent
		    Intent intentClassroomList = new Intent(PrincipalView.this, ClassRoomListView.class);
		    // Instancia um Bundle
		    Bundle b1 = new Bundle();
		    // Define que a tarefa que será enviada para a outra Activity será Listar as Salas
			b1.putInt("task", 1);  
			// Seta na Intent o Bundle
			intentClassroomList.putExtras(b1);
			// Inicia a nova Tela
			startActivity(intentClassroomList);
		    
		    break;
		case 1:
			Intent intentClassroomBook = new Intent(PrincipalView.this, ClassRoomListView.class);
		    Bundle b2 = new Bundle();  
			b2.putInt("task", 2);  
			intentClassroomBook.putExtras(b2);
			startActivity(intentClassroomBook);
			
			break;
		case 2:
			Intent intentBookSearch = new Intent(PrincipalView.this, BookListView.class);
			startActivity(intentBookSearch);
			break;
		case 3:
			onDestroy();
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

	public Button getBtSair() {
		return buttonSair;
	}

	public void setBtSair(Button btSair) {
		this.buttonSair = btSair;
	}
}
