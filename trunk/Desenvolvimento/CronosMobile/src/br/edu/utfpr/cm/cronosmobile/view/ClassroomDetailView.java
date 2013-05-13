package br.edu.utfpr.cm.cronosmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.model.ClassRoom;

public class ClassroomDetailView extends Activity {

	private ClassRoom classroom;
	private TextView classroomBookShort;
	private TextView classroomBookName;
	private TextView classroomBookBookable;
	private TextView classroomBookBuilding;
	private TextView classroomBookCapacity;
	private TextView classroomBookStatus;
	private TextView classroomBookType;
	private TextView classroomBookOwner;
	
	private Button btBookBack;
	private Button btBook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classroom_detail);

		this.btBookBack = (Button) findViewById(R.id.btBookBack);
		this.btBook 	= (Button) findViewById(R.id.btBook);
		
		this.classroomBookShort = (TextView) findViewById(R.id.classroomBookShort);
		this.classroomBookName = (TextView) findViewById(R.id.classroomBookName);
		this.classroomBookBookable = (TextView) findViewById(R.id.classroomBookBookable);
		this.classroomBookBuilding = (TextView) findViewById(R.id.classroomBookBuilding);
		this.classroomBookCapacity = (TextView) findViewById(R.id.classroomBookCapacity);
		this.classroomBookStatus = (TextView) findViewById(R.id.classroomBookStatus);
		this.classroomBookType = (TextView) findViewById(R.id.classroomBookType);
		this.classroomBookOwner = (TextView) findViewById(R.id.classroomBookOwner);
		
		Intent iService = getIntent();

		// verifica se veio alguma coisa pela intent
		if (iService != null) {
			// Recebe os parametros passados pela intent
			Bundle parametrosRecebidos = iService.getExtras();
			if (parametrosRecebidos != null) {
				
				// pega um objeto serializado
				this.classroom = (ClassRoom) parametrosRecebidos.getSerializable("classroom");
				
				this.classroomBookName.setText(classroom.getName());
				this.classroomBookShort.setText(classroom.get_short());
				this.classroomBookBookable.setText(classroom.getBookable());
				this.classroomBookBuilding.setText(classroom.getBuilding());
				this.classroomBookCapacity.setText(classroom.getCapacity());
				this.classroomBookStatus.setText(classroom.getStatus());
				this.classroomBookType.setText(classroom.getType());
				this.classroomBookOwner.setText(classroom.getOwner_id());
				
			}
		}

		this.btBook.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intentBook = new Intent(ClassroomDetailView.this, BookView.class);
				intentBook.putExtra("classroom", classroom);
			    startActivity(intentBook);
			}
		});
		
		this.btBookBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intentHome = new Intent(ClassroomDetailView.this, PrincipalView.class);
			    startActivity(intentHome);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.classroom_detail, menu);
		return true;
	}

}
