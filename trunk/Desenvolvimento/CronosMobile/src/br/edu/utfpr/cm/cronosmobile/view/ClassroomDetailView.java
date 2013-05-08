package br.edu.utfpr.cm.cronosmobile.view;

import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.R.layout;
import br.edu.utfpr.cm.cronosmobile.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ClassroomDetailView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classroom_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.classroom_detail, menu);
		return true;
	}

}
