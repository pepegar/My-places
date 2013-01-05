package me.jlgarcia.mislugares;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		// Instanciamos el bot—n Lista
		Button botonLista = (Button) findViewById(R.id.lista);

		// Este bloque se encarga de llamar a la actividad ListaLugaresActivity
		botonLista.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent LlamaAListaIntent = new Intent(PrincipalActivity.this, ListaLugaresActivity.class);
				startActivity(LlamaAListaIntent);
				
			}
			
		});
		
		// Instanciamos el bot—n Mapa
		Button botonMapa = (Button) findViewById(R.id.mapa);

		// Este bloque se encarga de llamar a la actividad MapaLugaresActivity
		botonMapa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent LlamaAMapaIntent = new Intent(PrincipalActivity.this, MapaLugaresActivity.class);
				startActivity(LlamaAMapaIntent);
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_principal, menu);
		return true;
	}

}
