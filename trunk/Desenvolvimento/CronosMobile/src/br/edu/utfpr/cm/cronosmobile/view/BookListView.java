package br.edu.utfpr.cm.cronosmobile.view;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.model.Book;
import br.edu.utfpr.cm.cronosmobile.model.Host;
import br.edu.utfpr.cm.cronosmobile.persistence.ConexaoHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressLint("SimpleDateFormat")
public class BookListView extends ListActivity {

	private MenuItem menuItemHome;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	Intent iService = getIntent();
	String service = "";
	
	if (iService != null) {
		Bundle parametrosRecebidos = iService.getExtras();  
		if (parametrosRecebidos != null) {
			service = parametrosRecebidos.getString("service"); 
		}
	}
	 
	String retorno = "";
	String resposta = "";

	if (service == "") {
		service = "list/";
	}
	
	// Strig com a url do webservice
	String urlGet = Host.DOMAIN + "services/book/"+service;
	Log.i("Servico", urlGet);
	try {
	    
	    // Tenta a conexão com o servidor
	    resposta = ConexaoHttpClient.httpGet(urlGet);
	    resposta = resposta.toString();

	    // Retira todos os espaços em Branco
	    //resposta = resposta.replaceAll("\\s+", "");

	} catch (Exception erro) {
	    Log.e("Logs", "Erro = " + erro);
	    mensagens("Logs", "Erro = " + erro);
	}

	retorno = resposta;

	// TRANSFORMAR UM OBJETO JSON EM UM OBJETO JAVA
	// Define o tipo como ClassRoom
	Type type = new TypeToken<List<Book>>() {}.getType();

	// Gera a lista com base no tipo da classe e com a string json
	List<Book> jsonObjectList = new Gson().fromJson(retorno, type);

	// Lista que será usada para exibir as salas
	List<Book> salas = new ArrayList<Book>();

	// Gera lista com objetos vindos do Json
	for (int i = 0; i < jsonObjectList.size(); i++) {
	    
		Book x = jsonObjectList.get(i);
		Log.i("sala", x.getClassroom().getName());
	    salas.add(x);
	}

	// Adaper que pega a lista de String e coloca na ListView
	setListAdapter(new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1, jsonObjectList));

	ListView listView = getListView();
	listView.setTextFilterEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    	MenuItem pesquisar = menu.add(0, 0, 0, "Pesquisar");
    	pesquisar.setIcon(R.drawable.search);
    	setHome(menu.add(0, 1, 0, "Início"));
    	
		return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case 0:
			Intent intentSearch = new Intent(BookListView.this, SearchBookView.class);
			startActivity(intentSearch);
			break;
		case 1:
			Intent intentHome = new Intent(BookListView.this, PrincipalView.class);
			startActivity(intentHome);
			break;

		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
    
    /**
     * Método para a exibição de Mensagens
     * 
     * @param title String - Titulo da Mensagem
     * @param msg String - Conteúdo da Mensagem
     * 
     */
    public void mensagens(String title, String msg) {
    	AlertDialog.Builder alerta = new AlertDialog.Builder(BookListView.this);
    	alerta.setTitle(title);
    	alerta.setMessage(msg);
    	alerta.setNeutralButton("OK", null);
    	alerta.show();
    }

	public MenuItem getHome() {
		return menuItemHome;
	}

	public void setHome(MenuItem home) {
		this.menuItemHome = home;
	}
	
    public static Date StringToCalendar(String data) {

        Calendar c = null;

        try {

            SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            c = Calendar.getInstance();
            c.setTime(formatoData.parse(data));

        } catch (Exception e) {
        }
        return c.getTime();
    }
}
