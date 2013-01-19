package me.jlgarcia.mislugares;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EditarLugarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_lugar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_editar_lugar, menu);
		return true;
	}

}
