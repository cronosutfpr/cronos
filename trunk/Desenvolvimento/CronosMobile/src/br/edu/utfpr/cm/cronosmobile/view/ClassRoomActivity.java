package br.edu.utfpr.cm.cronosmobile.view;

import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.R.layout;
import br.edu.utfpr.cm.cronosmobile.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

public class ClassRoomActivity extends Activity {

    private Button btBack;
    private SimpleCursorAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.class_room_list);
	
	btBack = (Button) findViewById(R.id.btBack);
	
	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.lista_salas, menu);
	return true;
    }

}
