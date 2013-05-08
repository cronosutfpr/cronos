package br.edu.utfpr.cm.cronosmobile.view;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.controller.PeriodAdapter;
import br.edu.utfpr.cm.cronosmobile.model.Host;
import br.edu.utfpr.cm.cronosmobile.model.Period;
import br.edu.utfpr.cm.cronosmobile.persistence.ConexaoHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BookView extends Activity {

	private Period period;
	private Spinner sp_period;
	
    private TextView txtStartDate;
    private TextView txtEndDate;

    private Button btStartDate;
    private Button btEndDate;
    private Button btBookBack;

    private int year;
    private int month;
    private int day;
    
    static final int DATE_DIALOG_ID_START_DATE = 998;
    static final int DATE_DIALOG_ID_END_DATE = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);
		
		setCurrentDateOnView();
		addListenerOnButton();
		spinnerPeriod();
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.book_view, menu);
		return true;
    }

    // Mostra a data atual
    public void setCurrentDateOnView() {

    	this.txtStartDate = (TextView) findViewById(R.id.txtStartDate);
    	this.txtEndDate = (TextView) findViewById(R.id.txtEndDate);

    	final Calendar c = Calendar.getInstance();
    	this.year = c.get(Calendar.YEAR);
    	this.month = c.get(Calendar.MONTH);
    	this.day = c.get(Calendar.DAY_OF_MONTH);

		// Seta data no texview
		this.txtStartDate.setText(new StringBuilder().append(this.day).append("/").append(this.month + 1).append("/").append(this.year).append(" "));
		this.txtEndDate.setText(new StringBuilder().append(this.day).append("/").append(this.month + 1).append("/").append(this.year).append(" "));
    }

    public void addListenerOnButton() {

		this.btStartDate = (Button) findViewById(R.id.btStartDate);
		this.btEndDate   = (Button) findViewById(R.id.btEndDate);
		this.btBookBack  = (Button) findViewById(R.id.btBookBack);
	
		this.btStartDate.setOnClickListener(new OnClickListener() {
	
		    @Override
		    public void onClick(View v) {
			showDialog(DATE_DIALOG_ID_START_DATE);
		    }
		});
	
		this.btEndDate.setOnClickListener(new OnClickListener() {
	
		    @Override
		    public void onClick(View v) {
			showDialog(DATE_DIALOG_ID_END_DATE);
		    }
		});
		
		this.btBookBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentHome = new Intent(BookView.this, PrincipalView.class);
				startActivity(intentHome);
			}
		});

    }

    @Override
    protected Dialog onCreateDialog(int id) {
	
		if (id == DATE_DIALOG_ID_START_DATE) {
		    // seta o date picker com a data atual
		    return new DatePickerDialog(this, datePickerStartDate, this.year, this.month, day);
		} else if (id == DATE_DIALOG_ID_END_DATE) {
		    // seta o date picker com a data atual
		    return new DatePickerDialog(this, datePickerEndDate, this.year, this.month, day);
		}
	
		return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerStartDate = new DatePickerDialog.OnDateSetListener() {

	// quando o dialog é fechado, este método é chamado.
	public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
	    year = selectedYear;
	    month = selectedMonth;
	    day = selectedDay;

	    // Seta data selecionada no texview
	    txtStartDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" "));
	}
    };

    private DatePickerDialog.OnDateSetListener datePickerEndDate = new DatePickerDialog.OnDateSetListener() {

		// quando o dialog é fechado, este método é chamado.
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
		    year = selectedYear;
		    month = selectedMonth;
		    day = selectedDay;
	
		    // Seta data selecionada no texview
		    txtEndDate.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year).append(" "));
		}
    };

    /**
     * Método para a exibição de Mensagens
     * 
     * @param title String - Titulo da Mensagem
     * @param msg String - Conteúdo da Mensagem
     * 
     */
    public void mensagens(String title, String msg) {
    	AlertDialog.Builder alerta = new AlertDialog.Builder(BookView.this);
    	alerta.setTitle(title);
    	alerta.setMessage(msg);
    	alerta.setNeutralButton("OK", null);
    	alerta.show();
    }
    
    /**
     * Método que gera e preenche o spinner (combo)
     */
    private void spinnerPeriod() {
    	
    	String retorno = "";
    	String resposta = "";
    	String service = "";
    	
    	// Strig com a url do webservice
    	String urlGet = Host.DOMAIN + "services/period/"+service;
    	
    	try {
    	    
    	    // Tenta a conexão com o servidor
    	    resposta = ConexaoHttpClient.httpGet(urlGet);
    	    Log.e("Logs", "Postou");
    	    resposta = resposta.toString();
    	    Log.e("Logs", "Recebeu" + resposta);

    	    // Retira todos os espaços em Branco
    	    //resposta = resposta.replaceAll("\\s+", "");

    	} catch (Exception erro) {
    	    Log.e("Logs", "Erro = " + erro);
    	    mensagens("Logs", "Erro = " + erro);
    	}
    	
    	retorno = resposta;

    	// TRANSFORMAR UM OBJETO JSON EM UM OBJETO JAVA
    	// Define o tipo como ClassRoom
    	Type type = new TypeToken<List<Period>>() {}.getType();

    	// Gera a lista com base no tipo da classe e com a string json
    	List<Period> jsonObjectList = new Gson().fromJson(retorno, type);

    	// Lista que será usada para exibir os periodos
    	ArrayAdapter<PeriodAdapter> spinnerArrayAdapter = null;
    	ArrayList<PeriodAdapter> listCategory = new ArrayList<PeriodAdapter>();
    	
    	// Gera lista com objetos vindos do Json
    	for (int i = 0; i < jsonObjectList.size(); i++) {
    		Period x = jsonObjectList.get(i);
    		listCategory.add(new PeriodAdapter(i, x));
    	}
    	
    	spinnerArrayAdapter = new ArrayAdapter<PeriodAdapter>(this, android.R.layout.simple_spinner_item, listCategory);
    	this.sp_period = (Spinner) findViewById(R.id.sp_period);

    	spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	this.sp_period.setAdapter(spinnerArrayAdapter);
	
    	this.sp_period.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
	        public void onItemSelected(AdapterView parent,View v,int posicao,long id) {
            	PeriodAdapter category = (PeriodAdapter) sp_period.getSelectedItem();
	        	period = category.period;
	        	Log.i("Periodo", period.getName());
	        }
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
    	
    }    
}
