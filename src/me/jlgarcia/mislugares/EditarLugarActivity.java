package me.jlgarcia.mislugares;

import me.jlgarcia.mislugares.db.LugaresSQLHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarLugarActivity extends Activity 
{

	private LugaresSQLHelper sQLHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_lugar);
		setTitle(R.string.title_activity_editar_lugar);
		
		// Obtenemos el id y el nombre del intent
		Bundle extras = getIntent().getExtras();
		String nom = "";
		
		// guardamos el nombre y id s—lo si se han pasado como extras	
		if (extras != null) 
		{
			nom = extras.getString("nombre");
			setTitle("Editando " + nom);
		}
	
		sQLHelper = new LugaresSQLHelper(this);
		
		String lugar[] = sQLHelper.getLugarByName(nom);
		
		String nombre, descripcion, foto, latitud, longitud;
		final int id;
		
		// Valores del lugar
		nombre = lugar[0];
		descripcion = lugar[1];
		latitud = lugar[2];
		longitud = lugar[3];
		foto = lugar[4];
		id = Integer.parseInt(lugar[5]);
		
		// Campos en que podremos editar
		final EditText campoNombre;
		final EditText campoDescripcion;
		final EditText campoLatitud;
		final EditText campoLongitud;
		final EditText campoFoto;
		campoNombre = (EditText) findViewById(R.id.editTextNombre);
		campoDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
		campoLatitud = (EditText) findViewById(R.id.editTextLatitud);
		campoLongitud = (EditText) findViewById(R.id.editTextLongitud);
		campoFoto = (EditText) findViewById(R.id.editTextFoto);
		
		// Insertamos los valores en los campos
		campoNombre.setText(nombre);
		campoDescripcion.setText(descripcion);
		campoLatitud.setText(latitud);
		campoLongitud.setText(longitud);
		campoFoto.setText(foto);
		
		// Identificamos el bot—n
		Button boton = (Button) findViewById(R.id.buttonGuardar);
		
		boton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				sQLHelper.updateLugar(
						id, 
						campoNombre.getText().toString(), 
						campoDescripcion.getText().toString(), 
						Double.parseDouble(campoLatitud.getText().toString()), 
						Double.parseDouble(campoLongitud.getText().toString()),
						campoFoto.getText().toString());
				
				// Mostramos mensaje de confirmaci—n
				Toast.makeText(getApplicationContext(), "Actualizado el lugar " + campoNombre.getText().toString() , Toast.LENGTH_LONG).show();
				
				// Refrescamos la actividad de MostrarLugar y de ListaLugares
				MostrarLugarActivity.refrescar(campoNombre.getText().toString());
				ListaLugaresActivity.refrescar();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_editar_lugar, menu);
		return true;
	}

}
