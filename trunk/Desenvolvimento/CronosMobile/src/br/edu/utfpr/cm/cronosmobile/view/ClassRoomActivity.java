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
	String urlPost = "http://192.168.1.3/cronos/";
	
	// ArrayList do tipo "chave valor"
	ArrayList<NameValuePair> parametrosPost = new ArrayList<NameValuePair>();
	
	// Onde passamos o nome do parametro e o valor neste caso, "task" é a tarefa que o webservice deve executar e "listar" é o valor esperado para listar as salas
	parametrosPost.add(new BasicNameValuePair("task", "listar"));

	try {
	    
	    // Tenta a conexão com o servidor
	    resposta = ConexaoHttpClient.httpPost(urlPost, parametrosPost);
	    Log.e("Logs", "Postou");
	    resposta = resposta.toString();
	    Log.e("Logs", "Recebeu" + resposta);

	    // Retira todos os espaços em Branco
	    //resposta = resposta.replaceAll("\\s+", "");

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
//	retorno = "[{\"id\":\"55\",\"0\":\"55\",\"_short\":\"B001\",\"1\":\"B001\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"BEA5D27A049A365F\",\"5\":\"BEA5D27A049A365F\",\"name\":\"B001 - Laborat\u00f3rio de Inform\u00e1tica\",\"6\":\"B001 - Laborat\u00f3rio de Inform\u00e1tica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"54\",\"0\":\"54\",\"_short\":\"B004\",\"1\":\"B004\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"2E2F8B1D4F4F1810\",\"5\":\"2E2F8B1D4F4F1810\",\"name\":\"B004 - Laborat\u00f3rio\",\"6\":\"B004 - Laborat\u00f3rio\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"56\",\"0\":\"56\",\"_short\":\"B007\",\"1\":\"B007\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"22B7C1CAF24D1768\",\"5\":\"22B7C1CAF24D1768\",\"name\":\"B007 - Laborat\u00f3rio COELE\",\"6\":\"B007 - Laborat\u00f3rio COELE\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"45\",\"0\":\"45\",\"_short\":\"B102\",\"1\":\"B102\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"47A97E839F28A75B\",\"5\":\"47A97E839F28A75B\",\"name\":\"B102 - Sala te\u00f3rica\",\"6\":\"B102 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"46\",\"0\":\"46\",\"_short\":\"B103\",\"1\":\"B103\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"7CA8C29771E458F7\",\"5\":\"7CA8C29771E458F7\",\"name\":\"B103 - Sala te\u00f3rica\",\"6\":\"B103 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"47\",\"0\":\"47\",\"_short\":\"B104\",\"1\":\"B104\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"FF797F8EAF2427CE\",\"5\":\"FF797F8EAF2427CE\",\"name\":\"B104 - Sala te\u00f3rica\",\"6\":\"B104 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"48\",\"0\":\"48\",\"_short\":\"B105\",\"1\":\"B105\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"50C35C679EB3B6D9\",\"5\":\"50C35C679EB3B6D9\",\"name\":\"B105 - Sala te\u00f3rica\",\"6\":\"B105 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"49\",\"0\":\"49\",\"_short\":\"B106\",\"1\":\"B106\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"B50F9F53582FFB46\",\"5\":\"B50F9F53582FFB46\",\"name\":\"B106 - Sala te\u00f3rica\",\"6\":\"B106 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"50\",\"0\":\"50\",\"_short\":\"B107\",\"1\":\"B107\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"9A3E2E413EA38932\",\"5\":\"9A3E2E413EA38932\",\"name\":\"B107 - Sala te\u00f3rica\",\"6\":\"B107 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"1\",\"0\":\"1\",\"_short\":\"C001\",\"1\":\"C001\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"4DDC060C46BEC0D0\",\"5\":\"4DDC060C46BEC0D0\",\"name\":\"C001 - Lab. de Veg., Higiene e Opera\u00e7\u00f5es Unitarias\",\"6\":\"C001 - Lab. de Veg., Higiene e Opera\u00e7\u00f5es Unitarias\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"2\",\"0\":\"2\",\"_short\":\"C002\",\"1\":\"C002\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"ACE2BB47C195354F\",\"5\":\"ACE2BB47C195354F\",\"name\":\"C002 - Laborat\u00f3rio de Carnes, leite e bromatologia\",\"6\":\"C002 - Laborat\u00f3rio de Carnes, leite e bromatologia\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"3\",\"0\":\"3\",\"_short\":\"C003 \",\"1\":\"C003 \",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"A0B230119678B129\",\"5\":\"A0B230119678B129\",\"name\":\"C003 - Laborat\u00f3rio de Qu\u00edmica Org\u00e2nica\/Bioqu\u00edmica\",\"6\":\"C003 - Laborat\u00f3rio de Qu\u00edmica Org\u00e2nica\/Bioqu\u00edmica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"4\",\"0\":\"4\",\"_short\":\"C005\",\"1\":\"C005\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"F34855EC8BA83CD1\",\"5\":\"F34855EC8BA83CD1\",\"name\":\"C005 - Laborat\u00f3rio de Microscopia\",\"6\":\"C005 - Laborat\u00f3rio de Microscopia\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"5\",\"0\":\"5\",\"_short\":\"C101\",\"1\":\"C101\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"5BC9788FD603C977\",\"5\":\"5BC9788FD603C977\",\"name\":\"C101 - Laborat\u00f3rio de Ecossistemas\",\"6\":\"C101 - Laborat\u00f3rio de Ecossistemas\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"6\",\"0\":\"6\",\"_short\":\"C102\",\"1\":\"C102\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"A5A8A93B6F7B2420\",\"5\":\"A5A8A93B6F7B2420\",\"name\":\"C102 - Laborat\u00f3rio de Zoologia\",\"6\":\"C102 - Laborat\u00f3rio de Zoologia\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"7\",\"0\":\"7\",\"_short\":\"C103\",\"1\":\"C103\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"CEE7D1AA5770F0DB\",\"5\":\"CEE7D1AA5770F0DB\",\"name\":\"C103 - Laborat\u00f3rio de Panifica\u00e7\u00e3o\",\"6\":\"C103 - Laborat\u00f3rio de Panifica\u00e7\u00e3o\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"8\",\"0\":\"8\",\"_short\":\"C104\",\"1\":\"C104\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"39BE0495B45EA0D8\",\"5\":\"39BE0495B45EA0D8\",\"name\":\"C104 - Laborat\u00f3rio de Saneamento\",\"6\":\"C104 - Laborat\u00f3rio de Saneamento\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"9\",\"0\":\"9\",\"_short\":\"C105\",\"1\":\"C105\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"C24CE84EC7B684CB\",\"5\":\"C24CE84EC7B684CB\",\"name\":\"C105 - Laborat\u00f3rio de qu\u00edmica anal\u00edtica\",\"6\":\"C105 - Laborat\u00f3rio de qu\u00edmica anal\u00edtica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"27\",\"0\":\"27\",\"_short\":\"D101\",\"1\":\"D101\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"99AF395048F974F9\",\"5\":\"99AF395048F974F9\",\"name\":\"D101 - Sala te\u00f3rica\",\"6\":\"D101 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"28\",\"0\":\"28\",\"_short\":\"D102\",\"1\":\"D102\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"EEB27695857DACC4\",\"5\":\"EEB27695857DACC4\",\"name\":\"D102 - Sala te\u00f3rica\",\"6\":\"D102 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"29\",\"0\":\"29\",\"_short\":\"D103\",\"1\":\"D103\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"7461795E7B548E66\",\"5\":\"7461795E7B548E66\",\"name\":\"D103 - Sala te\u00f3rica\",\"6\":\"D103 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"30\",\"0\":\"30\",\"_short\":\"D104\",\"1\":\"D104\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"BA97B95DAB0104AE\",\"5\":\"BA97B95DAB0104AE\",\"name\":\"D104 - Sala te\u00f3rica\",\"6\":\"D104 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"31\",\"0\":\"31\",\"_short\":\"D105\",\"1\":\"D105\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"07347B14390B9AAC\",\"5\":\"07347B14390B9AAC\",\"name\":\"D105 - Sala te\u00f3rica\",\"6\":\"D105 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"32\",\"0\":\"32\",\"_short\":\"D106\",\"1\":\"D106\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"FED22ED500AFA49A\",\"5\":\"FED22ED500AFA49A\",\"name\":\"D106 - Sala te\u00f3rica\",\"6\":\"D106 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"33\",\"0\":\"33\",\"_short\":\"D107\",\"1\":\"D107\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"6756CD4F362BB889\",\"5\":\"6756CD4F362BB889\",\"name\":\"D107 - Sala te\u00f3rica\",\"6\":\"D107 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"34\",\"0\":\"34\",\"_short\":\"D108\",\"1\":\"D108\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"B9ECF32A2B5B4431\",\"5\":\"B9ECF32A2B5B4431\",\"name\":\"D108 - Sala te\u00f3rica\",\"6\":\"D108 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"35\",\"0\":\"35\",\"_short\":\"E001\",\"1\":\"E001\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"45\",\"4\":\"45\",\"idxml\":\"ECC2C902D78B53A1\",\"5\":\"ECC2C902D78B53A1\",\"name\":\"E001 - Sala te\u00f3rica\",\"6\":\"E001 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"36\",\"0\":\"36\",\"_short\":\"E002\",\"1\":\"E002\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"45\",\"4\":\"45\",\"idxml\":\"1C15A43D2620ECFA\",\"5\":\"1C15A43D2620ECFA\",\"name\":\"E002 - Sala te\u00f3rica\",\"6\":\"E002 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"37\",\"0\":\"37\",\"_short\":\"E003\",\"1\":\"E003\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"45\",\"4\":\"45\",\"idxml\":\"EABBA93559900BEB\",\"5\":\"EABBA93559900BEB\",\"name\":\"E003 - Sala te\u00f3rica\",\"6\":\"E003 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"10\",\"0\":\"10\",\"_short\":\"E004\",\"1\":\"E004\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"2AD20AF212321171\",\"5\":\"2AD20AF212321171\",\"name\":\"E004 - Laborat\u00f3rio de res\u00edduos s\u00f3lidos\",\"6\":\"E004 - Laborat\u00f3rio de res\u00edduos s\u00f3lidos\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"11\",\"0\":\"11\",\"_short\":\"E005\",\"1\":\"E005\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"7D63ACA3F2422461\",\"5\":\"7D63ACA3F2422461\",\"name\":\"E005 - Herb\u00e1rio\",\"6\":\"E005 - Herb\u00e1rio\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"12\",\"0\":\"12\",\"_short\":\"E006\",\"1\":\"E006\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"45\",\"4\":\"45\",\"idxml\":\"F60C32941C141013\",\"5\":\"F60C32941C141013\",\"name\":\"E006 - Laborat\u00f3rio de Geoprocessamento\",\"6\":\"E006 - Laborat\u00f3rio de Geoprocessamento\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"26\",\"0\":\"26\",\"_short\":\"E007\",\"1\":\"E007\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"73B5071ED0AC691E\",\"5\":\"73B5071ED0AC691E\",\"name\":\"E007 - Laborat\u00f3rio de Inform\u00e1tica\",\"6\":\"E007 - Laborat\u00f3rio de Inform\u00e1tica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"20\",\"0\":\"20\",\"_short\":\"E100\",\"1\":\"E100\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"60\",\"4\":\"60\",\"idxml\":\"7FD183046E18BC45\",\"5\":\"7FD183046E18BC45\",\"name\":\"E100 - Laborat\u00f3rio de Inform\u00e1tica\",\"6\":\"E100 - Laborat\u00f3rio de Inform\u00e1tica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"21\",\"0\":\"21\",\"_short\":\"E101\",\"1\":\"E101\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"8053F52E319A00B5\",\"5\":\"8053F52E319A00B5\",\"name\":\"E101 - Laborat\u00f3rio de Inform\u00e1tica\",\"6\":\"E101 - Laborat\u00f3rio de Inform\u00e1tica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"22\",\"0\":\"22\",\"_short\":\"E102\",\"1\":\"E102\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"20\",\"4\":\"20\",\"idxml\":\"541D24B928C1AC8B\",\"5\":\"541D24B928C1AC8B\",\"name\":\"E102 - Laborat\u00f3rio de Inform\u00e1tica\",\"6\":\"E102 - Laborat\u00f3rio de Inform\u00e1tica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"23\",\"0\":\"23\",\"_short\":\"E103\",\"1\":\"E103\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"40\",\"4\":\"40\",\"idxml\":\"2EBC404C82A7506E\",\"5\":\"2EBC404C82A7506E\",\"name\":\"E103 - Laborat\u00f3rio de Hardware\",\"6\":\"E103 - Laborat\u00f3rio de Hardware\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"24\",\"0\":\"24\",\"_short\":\"E104\",\"1\":\"E104\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"20\",\"4\":\"20\",\"idxml\":\"2F3E2E4D31291C26\",\"5\":\"2F3E2E4D31291C26\",\"name\":\"E104 - Laborat\u00f3rio de Inform\u00e1tica\",\"6\":\"E104 - Laborat\u00f3rio de Inform\u00e1tica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"25\",\"0\":\"25\",\"_short\":\"E105\",\"1\":\"E105\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"25\",\"4\":\"25\",\"idxml\":\"146C8F4587AE6BAB\",\"5\":\"146C8F4587AE6BAB\",\"name\":\"E105 - Laborat\u00f3rio de Inform\u00e1tica\",\"6\":\"E105 - Laborat\u00f3rio de Inform\u00e1tica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"13\",\"0\":\"13\",\"_short\":\"F001 \",\"1\":\"F001 \",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"3D1F29621D250C6D\",\"5\":\"3D1F29621D250C6D\",\"name\":\"F001 - Laborat\u00f3rio de Hidr\u00e1ulica\",\"6\":\"F001 - Laborat\u00f3rio de Hidr\u00e1ulica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"14\",\"0\":\"14\",\"_short\":\"F002\",\"1\":\"F002\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"BA4B5E0A01AF8C74\",\"5\":\"BA4B5E0A01AF8C74\",\"name\":\"F002 - Laborat\u00f3rio de ensaios tecnol\u00f3gicos\",\"6\":\"F002 - Laborat\u00f3rio de ensaios tecnol\u00f3gicos\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"15\",\"0\":\"15\",\"_short\":\"F003\",\"1\":\"F003\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"*\",\"4\":\"*\",\"idxml\":\"BAC94C0AB011582B\",\"5\":\"BAC94C0AB011582B\",\"name\":\"F003 - Laborat\u00f3rio de solos\",\"6\":\"F003 - Laborat\u00f3rio de solos\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"16\",\"0\":\"16\",\"_short\":\"F004\",\"1\":\"F004\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"2484227CE6E1FA59\",\"5\":\"2484227CE6E1FA59\",\"name\":\"F004 - Laborat\u00f3rio de Fen\u00f4menos de Transportes e Hidr\u00e1ulica\",\"6\":\"F004 - Laborat\u00f3rio de Fen\u00f4menos de Transportes e Hidr\u00e1ulica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"17\",\"0\":\"17\",\"_short\":\"F005 \",\"1\":\"F005 \",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"A4B0797065B14CAB\",\"5\":\"A4B0797065B14CAB\",\"name\":\"F005 - Laborat\u00f3rio de f\u00edsica\",\"6\":\"F005 - Laborat\u00f3rio de f\u00edsica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"18\",\"0\":\"18\",\"_short\":\"F006\",\"1\":\"F006\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"0D359C13B02CD952\",\"5\":\"0D359C13B02CD952\",\"name\":\"F006 - Laborat\u00f3rio de instala\u00e7\u00f5es el\u00e9tricas\",\"6\":\"F006 - Laborat\u00f3rio de instala\u00e7\u00f5es el\u00e9tricas\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"38\",\"0\":\"38\",\"_short\":\"F101\",\"1\":\"F101\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"48\",\"4\":\"48\",\"idxml\":\"BCD8B78B5593A7CD\",\"5\":\"BCD8B78B5593A7CD\",\"name\":\"F101 - Sala te\u00f3rica\",\"6\":\"F101 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"39\",\"0\":\"39\",\"_short\":\"F102\",\"1\":\"F102\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"45\",\"4\":\"45\",\"idxml\":\"B0AC2C552A9723A7\",\"5\":\"B0AC2C552A9723A7\",\"name\":\"F102 - Sala te\u00f3rica\",\"6\":\"F102 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"40\",\"0\":\"40\",\"_short\":\"F103\",\"1\":\"F103\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"45\",\"4\":\"45\",\"idxml\":\"033E52301FA6AF4F\",\"5\":\"033E52301FA6AF4F\",\"name\":\"F103 - Sala te\u00f3rica\",\"6\":\"F103 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"51\",\"0\":\"51\",\"_short\":\"F104\",\"1\":\"F104\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"15\",\"4\":\"15\",\"idxml\":\"6BC374D36A223BF6\",\"5\":\"6BC374D36A223BF6\",\"name\":\"F104 - Sala teorica\",\"6\":\"F104 - Sala teorica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"41\",\"0\":\"41\",\"_short\":\"F105\",\"1\":\"F105\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"25\",\"4\":\"25\",\"idxml\":\"81E572AE28BA84A0\",\"5\":\"81E572AE28BA84A0\",\"name\":\"F105 - Sala te\u00f3rica\",\"6\":\"F105 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"42\",\"0\":\"42\",\"_short\":\"F106\",\"1\":\"F106\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"25\",\"4\":\"25\",\"idxml\":\"EDA7F1BF98C7E83F\",\"5\":\"EDA7F1BF98C7E83F\",\"name\":\"F106 - Sala te\u00f3rica\",\"6\":\"F106 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"52\",\"0\":\"52\",\"_short\":\"F107\",\"1\":\"F107\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"15\",\"4\":\"15\",\"idxml\":\"562D1462E34374E5\",\"5\":\"562D1462E34374E5\",\"name\":\"F107 - Sala te\u00f3rica\",\"6\":\"F107 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"19\",\"0\":\"19\",\"_short\":\"F108\",\"1\":\"F108\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"45\",\"4\":\"45\",\"idxml\":\"03BEC6FC20B4B701\",\"5\":\"03BEC6FC20B4B701\",\"name\":\"F108 - Sala de Desenho\",\"6\":\"F108 - Sala de Desenho\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"43\",\"0\":\"43\",\"_short\":\"F109\",\"1\":\"F109\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"35\",\"4\":\"35\",\"idxml\":\"314999CDD68B6BC8\",\"5\":\"314999CDD68B6BC8\",\"name\":\"F109 - Sala te\u00f3rica\",\"6\":\"F109 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"44\",\"0\":\"44\",\"_short\":\"F110\",\"1\":\"F110\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"25\",\"4\":\"25\",\"idxml\":\"AE7DCE75BB55EBCF\",\"5\":\"AE7DCE75BB55EBCF\",\"name\":\"F110 - Sala te\u00f3rica\",\"6\":\"F110 - Sala te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null},{\"id\":\"53\",\"0\":\"53\",\"_short\":\"H105\",\"1\":\"H105\",\"bookable\":\"0\",\"2\":\"0\",\"building\":null,\"3\":null,\"capacity\":\"22\",\"4\":\"22\",\"idxml\":\"6E354B33E618D3DB\",\"5\":\"6E354B33E618D3DB\",\"name\":\"H105 - Canteiro de obras _ Sala Te\u00f3rica\",\"6\":\"H105 - Canteiro de obras _ Sala Te\u00f3rica\",\"status\":null,\"7\":null,\"type\":null,\"8\":null,\"owner_id\":null,\"9\":null}]";
//	retorno = "[{\"_short\":\"E100\",\"name\":\"E100 - Laboratório de Informática\"},{\"_short\":\"E103\",\"name\":\"E103 - Laboratório de Hardware\"},{\"_short\":\"D102\",\"name\":\"D102 - Sala teórica\"}]";
	retorno = resposta;

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
