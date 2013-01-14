package me.jlgarcia.mislugares.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		LugaresDB.Lugares._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
	
	public String[] leerNombresDeLugares()
	{
		
		String ret[];
		
		String[] columnas = {"_ID", "nombre", "descripcion", "latitud", "longitud", "foto"};
		
		Cursor c = this.getReadableDatabase().query(LugaresDB.Lugares.NOMBRE_TABLA, columnas, null, null, null, null, null);
		
		int id, nombre, descripcion, latitud, longitud, foto;
		id = c.getColumnIndex("_ID");
		nombre = c.getColumnIndex("nombre");
		descripcion = c.getColumnIndex("descripcion");
		latitud = c.getColumnIndex("latitud");
		longitud = c.getColumnIndex("longitud");
		foto = c.getColumnIndex("foto");
	
		ret = new String[c.getCount()];
	
		int i = 0;
		// Recorremos el contenido del cursor
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		{
			ret[i] = c.getString(nombre);
			i++;
		}
		
		return ret;
	
	}
	
	public String[] getLugarByName(String name){
		
		SQLiteDatabase db = getWritableDatabase();
		
		String[] ret = new String[6];
		
		String[] args = new String[]{name};
		
		Cursor c = db.query(false, LugaresDB.Lugares.NOMBRE_TABLA, args, "nombre=?", args, null, null, null, null);
		
		return ret;
		
	}
}
