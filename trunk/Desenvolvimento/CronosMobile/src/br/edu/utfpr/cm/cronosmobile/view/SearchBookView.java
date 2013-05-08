package br.edu.utfpr.cm.cronosmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.edu.utfpr.cm.cronosmobile.R;

public class SearchBookView extends Activity {

	private Button btBuscar;
	private Button btBack;
	private EditText edSearchBook;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_search_list);
		
		this.btBuscar = (Button) findViewById(R.id.btBuscar);
		this.btBack = (Button) findViewById(R.id.btBack);
		this.edSearchBook = (EditText) findViewById(R.id.edSearchBook);

		this.btBuscar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intentSearch = new Intent(SearchBookView.this, ClassRoomView.class);
				
				Bundle b = new Bundle();  
				b.putString("service", "list/book/list/classroom/" + edSearchBook.getText().toString());  
				intentSearch.putExtras(b);
				startActivity(intentSearch);
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
		getMenuInflater().inflate(R.menu.book_search_view, menu);
		return true;
	}

}
