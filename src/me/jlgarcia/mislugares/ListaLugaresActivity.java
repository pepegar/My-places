package me.jlgarcia.mislugares;

import me.jlgarcia.mislugares.db.LugaresSQLHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;

public class ListaLugaresActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_lugares);
		setTitle("Mis lugares");
	
		final ListView lista = (ListView) findViewById(R.id.listaLugares);
		
		LugaresSQLHelper dbHelper = new LugaresSQLHelper(this);

//		dbHelper.insertarLugar("Nombre del primer elemento", "Descripcion del primer elemento", (long) 44.276671, (long) -106.888183, "no-foto");
//		dbHelper.insertarLugar("Nombre del segundo elemento", "Descripcion del segundo elemento", (long) 40.276671, (long) -110.888183, "no-foto");
	
		String[] lugares = dbHelper.getNombresLugares();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, lugares);
		lista.setAdapter(adapter);

		// Listener de cada uno de los elementos del listview
		lista.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				// Intent 
				Intent llamaAMostrarIntent = new Intent(ListaLugaresActivity.this, MostrarLugarActivity.class);
				
		        String itemSeleccionado = (String) (lista.getItemAtPosition(position));
				llamaAMostrarIntent.putExtra("nombre", itemSeleccionado);
				startActivity(llamaAMostrarIntent);
			}
			
		});
		
	}

}
