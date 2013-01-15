package me.jlgarcia.mislugares;

import me.jlgarcia.mislugares.db.LugaresSQLHelper;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MostrarLugarActivity extends Activity {

	private LugaresSQLHelper sQLHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_lugar);
	
		// Obtenemos el nombre del lugar
		Bundle extras = getIntent().getExtras();
		String nombre = "";
		if (extras != null) {
		    nombre = extras.getString("nombre");
		    setTitle(nombre);
		}
		
		sQLHelper = new LugaresSQLHelper(this);
		sQLHelper.getLugarByName(nombre);
		
//		Log.d("qwerqwer", lugar[0]);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mostrar_lugar, menu);
		return true;
	}

}
