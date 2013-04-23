package me.jlgarcia.mislugares;

import java.util.ArrayList;
import java.util.List;

import me.jlgarcia.mislugares.db.LugaresSQLHelper;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;

public class MapaLugaresActivity extends MapActivity {

	MapView mapa = null;

    private static LugaresSQLHelper dbHelper;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa_lugares);

        dbHelper = new LugaresSQLHelper(getApplicationContext());
        Cursor lugares = null;

        // Obtenemos una referencia al control MapView
		mapa = (MapView) findViewById(R.id.mapa);

		// Mostramos los controles de zoom sobre el mapa
		mapa.setBuiltInZoomControls(true);

		List<Overlay> mapOverlays = mapa.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.marker);
		AddItemizedOverlay itemizedOverlay = new AddItemizedOverlay(drawable,
				this);

        try {

            lugares = dbHelper.getLugares();

            for (lugares.moveToFirst(); !lugares.isAfterLast(); lugares.moveToNext())
            {

                double lat = Double.parseDouble(lugares.getString(lugares.getColumnIndex("latitud")));
                double lon = Double.parseDouble(lugares.getString(lugares.getColumnIndex("longitud")));

                String nombre = lugares.getString(lugares.getColumnIndex("nombre"));
                String descripcion = lugares.getString(lugares.getColumnIndex("descripcion"));

                GeoPoint geoPoint = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
                OverlayItem overlayitem = new OverlayItem(geoPoint, nombre, descripcion);

                itemizedOverlay.addOverlay(overlayitem);
            }

            mapOverlays.add(itemizedOverlay);

        } catch (Exception e){

            Log.d("Excepcion en mapaLugaresActivity(): ", e.getMessage());

        } finally {

            lugares.close();
            Log.d("Excepcion en mapaLugaresActivity(): ", "asdf");


        }

    }
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
