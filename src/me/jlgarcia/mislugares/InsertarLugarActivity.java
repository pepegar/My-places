package me.jlgarcia.mislugares;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import me.jlgarcia.mislugares.db.LugaresSQLHelper;

public class InsertarLugarActivity extends Activity {

    private static int RESULT_LOAD_IMAGE = 1;

    private Context context;

    @Override
	protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar_lugar);
		
		Bundle extras = getIntent().getExtras();
		double[] coordenadas = null;
        this.context = this.getApplicationContext();
        final LugaresSQLHelper dbHelper = new LugaresSQLHelper(getApplicationContext());
		
		if (extras != null)
        {
			coordenadas = extras.getDoubleArray("coordenadas");
		}

        // Declaramos los objetos del layout
        final EditText campoNombre;
        final EditText campoDescripcion;
        final EditText campoLatitud;
        final EditText campoLongitud;
        final EditText campoFoto;
        final Button botonInsertar;
        final Button botonFoto;

        // inicializamos
        campoNombre = (EditText) findViewById(R.id.anade_editTextNombre);
        campoDescripcion = (EditText) findViewById(R.id.anade_editTextDescripcion);
        campoLatitud = (EditText) findViewById(R.id.anade_editTextLatitud);
        campoLongitud = (EditText) findViewById(R.id.anade_editTextLongitud);
        campoFoto = (EditText) findViewById(R.id.anade_editTextFoto);
        botonInsertar = (Button) findViewById(R.id.anade_buttonGuardar);
        botonFoto = (Button) findViewById(R.id.anade_botonSeleccionaFoto);

        // Seteamos los campos de latitud y longitud
        campoLatitud.setText("" + coordenadas[1]);
        campoLongitud.setText("" + coordenadas[0]);

        final double latitud = Double.parseDouble(campoLatitud.getText().toString());
        final double longitud = Double.parseDouble(campoLongitud.getText().toString());

        botonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI );

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }

        });

        // Listener del boton de insertar
        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    dbHelper.insertarLugar(
                            campoNombre.getText().toString(),
                            campoDescripcion.getText().toString(),
                            latitud,
                            longitud,
                            campoFoto.getText().toString()
                    );

                    Toast.makeText(context, "Insertado con éxito el lugar " + campoNombre.getText().toString(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                    Log.d("Error", "Algo ha ido mal insertando lugar");

                }

            }
        });

	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 4;

            ImageView imageView = (ImageView) findViewById(R.id.anade_foto);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath, opts));

            EditText campoFoto = (EditText) findViewById(R.id.anade_editTextFoto);
            campoFoto.setText(picturePath);
        }
    }

}
