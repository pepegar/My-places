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

public class MostrarLugarActivity extends Activity {

	private LugaresSQLHelper sQLHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_lugar);
		setTitle(R.string.titulo_mostar_lugar);

		// Obtenemos el nombre del lugar
		Bundle extras = getIntent().getExtras();
		String nombre = "";

		if (extras != null) {
			nombre = extras.getString("nombre");
			setTitle(nombre);
		}

		sQLHelper = new LugaresSQLHelper(this);

		String lugar[] = sQLHelper.getLugarByName(nombre);

		// Obtenemos los valores de todos los campos del lugar
		final String nom = lugar[0];
		String descripcion = lugar[1];
		String latitud = lugar[2];
		String longitud = lugar[3];
		String foto = lugar[4];

		// Identificamos los editText donde introduciremos los valores.
		EditText campoNombre, campoDescripcion, campoLatitud, campoLongitud, campoFoto;
		campoNombre = (EditText) findViewById(R.id.editTextNombre);
		campoDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
		campoLatitud = (EditText) findViewById(R.id.editTextLatitud);
		campoLongitud = (EditText) findViewById(R.id.editTextLongitud);
		campoFoto = (EditText) findViewById(R.id.editTextFoto);

		// Llenamos los campos con los valores
		campoNombre.setText(nom);
		campoDescripcion.setText(descripcion);
		campoLatitud.setText(latitud);
		campoLongitud.setText(longitud);
		campoFoto.setText(foto);

		// Le damos comportamiento al bot—n de Editar
		Button editar = (Button) findViewById(R.id.buttonEditar);
		editar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(MostrarLugarActivity.this, EditarLugarActivity.class);
				i.putExtra("nombre", nom);
				startActivity(i);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mostrar_lugar, menu);
		return true;
	}

}
