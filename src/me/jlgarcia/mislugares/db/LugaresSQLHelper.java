package me.jlgarcia.mislugares.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LugaresSQLHelper extends SQLiteOpenHelper
{
	public LugaresSQLHelper(Context context ) 
	{
		super(context, LugaresDB.DB_NAME, null, LugaresDB.DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		if(db.isReadOnly()){db = getWritableDatabase();}
		db.execSQL("CREATE TABLE " +
		LugaresDB.Lugares.NOMBRE_TABLA + "(" +
		LugaresDB.Lugares._ID + " INTEGER PRIMARY KEY AUTO INCREMENT, " +
		LugaresDB.Lugares.NOMBRE + " VARCHAR(40), " +
		LugaresDB.Lugares.DESCRIPCION + " TEXT, " +
		LugaresDB.Lugares.LATITUD + " REAL, " +
		LugaresDB.Lugares.LONGITUD + " REAL, " +
		LugaresDB.Lugares.FOTO + " TEXT) ");
		
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) 
	{
	}

	/**
	 * MŽtodo que se encarga de insertar lugares en nuestra tabla lugares
	 * 
	 * @param nombre String nombre del lugar
	 * @param descripcion String descripcion del lugar
	 * @param latitud long latitud del lugar
	 * @param longitud long longitud del lugar
	 * @param foto String uri de la foto 
	 */
	public void insertarLugar(String nombre, String descripcion, long latitud, long longitud, String foto)
	{
		
		ContentValues valores = new ContentValues();
		
		valores.put("nombre", nombre);
		valores.put("descripcion", descripcion);
		valores.put("latitud", latitud);
		valores.put("longitud", longitud);
		valores.put("foto", foto);
		
		this.getWritableDatabase().insert(LugaresDB.Lugares.NOMBRE_TABLA, null, valores);
	}
}
