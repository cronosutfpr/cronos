package br.edu.utfpr.cm.cronosmobile.view;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.utfpr.cm.cronosmobile.R;
import br.edu.utfpr.cm.cronosmobile.model.ClassRoom;
import br.edu.utfpr.cm.cronosmobile.persistence.ConexaoHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Ana Claudia Maciel
 * @author Willyan Schultz Dworak
 * 
 */
public class ClassRoomActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// Removi essa linha porque estou usando o layout do proprio Android
	// setContentView(R.layout.class_room_list);

	String retorno = "";
	String resposta = "";

	// Strig com a url do webservice
	String urlPost = "http://192.168.0.8/android/conectar.php";
	
	// ArrayList do tipo "chave valor"
	ArrayList<NameValuePair> parametrosPost = new ArrayList<NameValuePair>();
	
	// Onde passamos o nome do parametro e o valor neste caso, "task" é a tarefa que o webservice deve executar e "listar" é o valor esperado para listar as salas
	parametrosPost.add(new BasicNameValuePair("task", "listar"));

	try {
	    
	    // Tenta a conexão com o servidor
	    resposta = ConexaoHttpClient.httpPost(urlPost, parametrosPost);
	    resposta = resposta.toString();

	    // Retira todos os espaços em Branco
	    resposta = resposta.replaceAll("\\s+", "");

	} catch (Exception erro) {
	    Log.e("Logs", "Erro = " + erro);
	    mensagens("Logs", "Erro = " + erro);
	}

	/*
	 * 
	// Criação de uma lista de ClassRoom para gerar um Json
	List<ClassRoom> classRoomList = new ArrayList<ClassRoom>();
	ClassRoom clasRoom1 = new ClassRoom();
	clasRoom1.setName("E100 - Laboratório de Informática");
	clasRoom1.set_short("E100");

	ClassRoom clasRoom2 = new ClassRoom();
	clasRoom2.setName("E103 - Laboratório de Hardware");
	clasRoom2.set_short("E103");

	ClassRoom clasRoom3 = new ClassRoom();
	clasRoom3.setName("D102 - Sala teórica");
	clasRoom3.set_short("D102");

	classRoomList.add(clasRoom1);
	classRoomList.add(clasRoom2);
	classRoomList.add(clasRoom3);

	Gson gson = new Gson();
	
	// usar esse comando para converter lista em Json
	String retorno = gson.toJson(classRoomList);
	
	*/

	// Variavel que simula o resultado do webservice (Json - ClassRoom)
	retorno = "[{\"_short\":\"E100\",\"name\":\"E100 - Laboratório de Informática\"},{\"_short\":\"E103\",\"name\":\"E103 - Laboratório de Hardware\"},{\"_short\":\"D102\",\"name\":\"D102 - Sala teórica\"}]";

	// TRANSFORMAR UM OBJETO JSON EM UM OBJETO JAVA
	// Define o tipo como ClassRoom
	Type type = new TypeToken<List<ClassRoom>>() {}.getType();

	// Gera a lista com base no tipo da classe e com a string json
	List<ClassRoom> jsonObjectList = new Gson().fromJson(retorno, type);

	// Lista que será usada para exibir as salas
	List<String> salas = new ArrayList<String>();

	// Gera lista com objetos vindos do Json
	for (int i = 0; i < jsonObjectList.size(); i++) {
	    ClassRoom x = jsonObjectList.get(i);
	    salas.add(x.getName());
	}

	// Adaper que pega a lista de String e coloca na ListView
	setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, salas));

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
	    Toast.makeText(this, "Fazer alguma coisa", Toast.LENGTH_SHORT).show();
	    break;

	default:
	    Toast.makeText(this, "Não implementado", Toast.LENGTH_SHORT).show();
	    break;
	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.lista_salas, menu);
	return true;
    }

    /**
     * Método para a exibição de Mensagens
     * 
     * @param title String - Titulo da Mensagem
     * @param msg String - Conteúdo da Mensagem
     * 
     */
    public void mensagens(String title, String msg) {
	AlertDialog.Builder alerta = new AlertDialog.Builder(ClassRoomActivity.this);
	alerta.setTitle(title);
	alerta.setMessage(msg);
	alerta.setNeutralButton("OK", null);
	alerta.show();
    }

}
