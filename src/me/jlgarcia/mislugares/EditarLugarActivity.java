package me.jlgarcia.mislugares;

import me.jlgarcia.mislugares.db.LugaresSQLHelper;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
		int id;
		
		// Valores del lugar
		nombre = lugar[0];
		descripcion = lugar[0];
		latitud = lugar[0];
		longitud = lugar[0];
		foto = lugar[0];
		id = Integer.parseInt(lugar[0]);
		
		// Campos en que podremos editar
		EditText campoNombre, campoDescripcion, campoLatitud, campoLongitud, campoFoto, campoId;
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
		
		// Le damos comportamiento al bot—n
		Button guardar = (Button) findViewById(R.id.buttonGuardar);
		
		guardar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				
				
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
