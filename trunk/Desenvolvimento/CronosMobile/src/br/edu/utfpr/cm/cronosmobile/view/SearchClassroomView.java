package br.edu.utfpr.cm.cronosmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.edu.utfpr.cm.cronosmobile.R;
/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class SearchClassroomView extends Activity {

	private Button btBuscar;
	private Button btBack;
	private EditText edSearchClassroom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_classroom);

		this.btBuscar = (Button) findViewById(R.id.btBuscar);
		this.btBack = (Button) findViewById(R.id.btBack);
		this.edSearchClassroom = (EditText) findViewById(R.id.edSearchClassroom);

		this.btBuscar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intentSearch = new Intent(SearchClassroomView.this, ClassRoomListView.class);
				
				Bundle b = new Bundle();  
				b.putString("service", "list/classroom/" + edSearchClassroom.getText().toString());  
				intentSearch.putExtras(b);
				startActivity(intentSearch);
				finish();
			}
		});

		this.btBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_classroom_view, menu);
		return true;
	}

}
