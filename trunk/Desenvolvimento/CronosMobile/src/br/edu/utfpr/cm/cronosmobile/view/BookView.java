package br.edu.utfpr.cm.cronosmobile.view;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.controller.PeriodAdapter;
import br.edu.utfpr.cm.cronosmobile.model.Book;
import br.edu.utfpr.cm.cronosmobile.model.ClassRoom;
import br.edu.utfpr.cm.cronosmobile.model.Host;
import br.edu.utfpr.cm.cronosmobile.model.Period;
import br.edu.utfpr.cm.cronosmobile.persistence.ConexaoHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BookView extends Activity {

	private ArrayList<NameValuePair> parametrosPost;
	
	private Period period;
	private ClassRoom classroom;
	private Book book;
	
	private Spinner sp_period;
	
    private TextView txtStartDate;
    private TextView txtEndDate;
    private TextView classroomBookShort;
    private TextView classroomBookName;
    private EditText edNote;

    private Button btStartDate;
    private Button btEndDate;
    private Button btBookBack;
    private Button btInfo;
    private Button btBook;

    private int year;
    private int month;
    private int day;
    
    private String periods_id;
    private String startdate;
    private String enddate;
    
    static final int DATE_DIALOG_ID_START_DATE = 998;
    static final int DATE_DIALOG_ID_END_DATE = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);

		this.book = new Book();
		
		this.btInfo = (Button) findViewById(R.id.btInfo);
		this.btBook = (Button) findViewById(R.id.btBook);
		this.edNote = (EditText) findViewById(R.id.edNote);
		
		this.classroomBookShort = (TextView) findViewById(R.id.classroomBookShort);
		this.classroomBookName = (TextView) findViewById(R.id.classroomBookName);
		
		setCurrentDateOnView();
		addListenerOnButton();
		spinnerPeriod();
		
		Intent iService = getIntent();
		
		// verifica se veio alguma coisa pela intent
		if (iService != null) {
			// Recebe os parametros passados pela intent
			Bundle parametrosRecebidos = iService.getExtras();  
			if (parametrosRecebidos != null) {
				// pega um objeto serializado
				this.classroom = (ClassRoom) parametrosRecebidos.getSerializable("classroom");
				this.classroomBookShort.setText(classroom.get_short());
				this.classroomBookName.setText(classroom.getName());
			}
		}
		
		this.book.setStatus("1");
		this.book.setRequestor("1");
		this.book.setClassroom(this.classroom);
		
		this.btInfo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intentBook = new Intent(BookView.this, ClassroomDetailView.class);
				intentBook.putExtra("classroom", classroom);
				finish();
			    startActivity(intentBook);
			}
		});
		
		this.btBook.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				book.setNote(edNote.getText().toString());
				bookClassroom(book);
				finish();
			}
		});
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.book_view, menu);
		return true;
    }
    
    private void bookClassroom(Book book) {
    	
		String resposta = "";

		// Strig com a uri do webservice
		String urlPost = Host.DOMAIN + "services/book/";
		
		setParametrosPost(new ArrayList<NameValuePair>());
		
		 // Onde passamos o nome do parametro e o valor neste caso, "task" é a tarefa que o webservice deve executar.
		 parametrosPost.add(new BasicNameValuePair("task", "insert"));
		 // Aqui os parametros para inserir uma reserva na base de dados.
		 parametrosPost.add(new BasicNameValuePair("endDate", book.getEndDate()));
		 parametrosPost.add(new BasicNameValuePair("note", book.getNote()));
		 parametrosPost.add(new BasicNameValuePair("startdate", book.getStartdate()));
		 parametrosPost.add(new BasicNameValuePair("status", book.getStatus()));
		 parametrosPost.add(new BasicNameValuePair("classroom_id", book.getClassroom().getId() + ""));
		 parametrosPost.add(new BasicNameValuePair("requestor_id", book.getRequestor()));
		 parametrosPost.add(new BasicNameValuePair("periods_id", this.periods_id));
		 
		Log.i("task", "insert");
		Log.i("inicio", book.getStartdate());
		Log.i("fim", book.getEndDate());
		Log.i("note", book.getNote());
		Log.i("status", book.getStatus());
		Log.i("classroom_id", book.getClassroom().getId() + "");
		Log.i("requestor_id", book.getRequestor());

		try {
			if(this.book.getEndDate() != "" && this.book.getStartdate() != "") {
			    // Tenta a conexão com o servidor
			    resposta = ConexaoHttpClient.httpPost(urlPost, parametrosPost);
			    //Log.e("Logs", "Postou");
			    //Log.e("Logs", "Recebeu" + resposta);

			    resposta = resposta.toString();
			    // Retira todos os espaços em Branco
			    //resposta = resposta.replaceAll("\\s+", "");
			    Toast.makeText(getApplicationContext(), resposta.toString(), Toast.LENGTH_SHORT).show();
			} else {
		    	Toast.makeText(getApplicationContext(), "Escolha das datas de inicio e fim", Toast.LENGTH_SHORT).show();
		    }    
		} catch (Exception erro) {
		    Log.e("Logs", "Erro = " + erro);
		    mensagens("Logs", "Erro = " + erro);
		}
		 
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
    	StringBuilder sb = new StringBuilder().append(this.day).append("/").append(this.month + 1).append("/").append(this.year).append(" ");

    	this.txtStartDate.setText(sb);
		this.txtEndDate.setText(sb);
		
		String today = sb.toString();
		
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			Date d1 = sdf1.parse(today);
			sdf1.applyPattern("yyyy-MM-dd");
			today = sdf1.format(d1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.book.setEndDate(today);
		this.book.setStartdate(today);
		
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
	    startdate = txtStartDate.getText().toString();
		
		try {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			Date d1 = sdf1.parse(startdate);
			sdf1.applyPattern("yyyy-MM-dd");
			startdate = sdf1.format(d1);
			book.setStartdate(startdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
		    
		    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			enddate = txtEndDate.getText().toString();
			
			try {
				Date d2 = sdf2.parse(enddate);
				sdf2.applyPattern("yyyy-MM-dd");
				enddate = sdf2.format(d2);
				book.setEndDate(enddate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
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
	        	periods_id = category.period.getId() + "";
	        	Log.i("Periodo", period.getName());
	        	Log.i("Periodo ID", periods_id);
	        }
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
    	
    }
    
    public ArrayList<NameValuePair> getParametrosPost() {
		return parametrosPost;
	}

	public void setParametrosPost(ArrayList<NameValuePair> parametrosPost) {
		this.parametrosPost = parametrosPost;
	}
}
