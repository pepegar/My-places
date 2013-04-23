package me.jlgarcia.mislugares;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.*;

// Esta clase se encarga de contener todos los overlays
public class AddItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();

	private Context context;
	
	private Drawable marker;
	
	public AddItemizedOverlay(Drawable defaultMarker)
    {
		super(boundCenterBottom(defaultMarker));
		this.populate();
		this.marker = defaultMarker;
	}

	public AddItemizedOverlay(Drawable defaultMarker, Context context)
    {
		this(defaultMarker);
		this.context = context;
	}

	@Override
	protected OverlayItem createItem(int i)
    {
		return mapOverlays.get(i);
	}

	@Override
	public int size()
    {
		return mapOverlays.size();
	}

	@Override
	protected boolean onTap(int index)
    {

		// Obtenemos el nombre del elemento
		String nombre = mapOverlays.get(index).getTitle();

		// Creamos el Intent para llamar a MostrarLugarActivity
		Intent i = new Intent(context, MostrarLugarActivity.class);

		// A�adimos el nombre al intent para que MLActivity pueda mostrar todos
		// los detalles del lugar que hemos seleccionado
		i.putExtra("nombre", nombre);

		// mostramos la actividad
		context.startActivity(i);

		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView)
    {

        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:

                if (event.getEventTime() - event.getDownTime() <= 200)
                {

                    Projection proj = mapView.getProjection();
                    GeoPoint loc = proj.fromPixels((int)event.getX(), (int)event.getY());

                    // Obtenemos la localización que hemos pulsado como un par de latitud y longitud
                    final double[] coordenadas = {
                            loc.getLongitudeE6() / 1E6,
                            loc.getLatitudeE6() / 1E6
                    };

                    // Creamos el dialogo
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    // A�adimos el boton positivo
                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            // Creamos el intent
                            Intent i = new Intent(context, InsertarLugarActivity.class);

                            // Le pasamos las coordenadas
                            i.putExtra("coordenadas", coordenadas);

                            //Log.d("Coordenadas: ", "" + coordenadas[0]);
                            //Toast.makeText(context, "" + coordenadas[0] + " " + coordenadas[1], Toast.LENGTH_LONG).show();

                            // Iniciamos InsertarLugarActivity
                            context.startActivity(i);

                        }
                    });

                    // el botón negativo
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            return;
                        }
                    });

                    builder.setTitle("Insertar lugar").setMessage("¿Quiere insertar un nuevo lugar aquí?");

                    AlertDialog dialog = builder.create();

                    // Mostramos el dialogo
                    dialog.show();

                }

                break;
        }

		return false;
	}

	public void addOverlay(OverlayItem overlay)
    {
		mapOverlays.add(overlay);
		this.populate();
	}

}
