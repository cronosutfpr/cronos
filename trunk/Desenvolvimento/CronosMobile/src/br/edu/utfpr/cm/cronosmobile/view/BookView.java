package br.edu.utfpr.cm.cronosmobile.view;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import br.edu.utfpr.cm.cronosmobile.R;

public class BookView extends Activity {

	private TextView txtStartDate;
	private TextView txtEndDate;
	
	private Button btStartDate;
	private Button btEndDate;

	private int year;
	private int month;
	private int day;

	static final int DATE_DIALOG_ID_START_DATE = 998;
	static final int DATE_DIALOG_ID_END_DATE = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_view);

		setCurrentDateOnView();
		addListenerOnButton();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.book_view, menu);
		return true;
	}

	// Mostra a data atual
	public void setCurrentDateOnView() {

		txtStartDate = (TextView) findViewById(R.id.txtStartDate);
		txtEndDate   = (TextView) findViewById(R.id.txtEndDate);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// Seta data no texview
		txtStartDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" "));
		txtEndDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" "));
	}

	public void addListenerOnButton() {

		btStartDate = (Button) findViewById(R.id.btStartDate);
		btEndDate   = (Button) findViewById(R.id.btEndDate);

		btStartDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID_START_DATE);

			}

		});
		
		btEndDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				showDialog(DATE_DIALOG_ID_END_DATE);
				
			}
			
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		
		if (id == DATE_DIALOG_ID_START_DATE) {
			// seta o date picker com a data atual
			return new DatePickerDialog(this, datePickerStartDate, year, month,day);
		} else if (id == DATE_DIALOG_ID_END_DATE) {
			// seta o date picker com a data atual
			return new DatePickerDialog(this, datePickerEndDate, year, month,day);	
		}
		
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerStartDate= new DatePickerDialog.OnDateSetListener() {

		// quando o dialog é fechado, este método é chamado.
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// Seta data selecionada no texview
			txtStartDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" "));
		}
	};
	
	private DatePickerDialog.OnDateSetListener datePickerEndDate= new DatePickerDialog.OnDateSetListener() {
		
		// quando o dialog é fechado, este método é chamado.
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			
			// Seta data selecionada no texview
			txtEndDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" "));
		}
	};

}
