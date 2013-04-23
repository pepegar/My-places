package me.jlgarcia.mislugares.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LugaresSQLHelper extends SQLiteOpenHelper
{
	
	private static SQLiteDatabase db;
	
	public LugaresSQLHelper(Context context ) 
	{
		
		super(context, LugaresDB.DB_NAME, null, LugaresDB.DB_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) 
	{

		if(db.isReadOnly()){db = getWritableDatabase();}
		
		LugaresSQLHelper.setDb(db);
		
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
	 * M�todo que se encarga de insertar lugares en nuestra tabla lugares
	 * 
	 * @param nombre String nombre del lugar
	 * @param descripcion String descripcion del lugar
	 * @param d long latitud del lugar
	 * @param e long longitud del lugar
	 * @param foto String uri de la foto 
	 */
	public void insertarLugar(String nombre, String descripcion, double d, double e, String foto)
	{
		
		ContentValues valores = new ContentValues();
		
		valores.put("nombre", nombre);
		valores.put("descripcion", descripcion);
		valores.put("latitud", d);
		valores.put("longitud", e);
		valores.put("foto", foto);
		
		this.getWritableDatabase().insert(LugaresDB.Lugares.NOMBRE_TABLA, null, valores);
	}
	
	public String[] getNombresLugares() 
	{
		
		String ret[] = null;
        Cursor c = null;
		
		String[] columnas = {"_ID", "nombre", "descripcion", "latitud", "longitud", "foto"};

        try {
            c = this.getReadableDatabase().query(LugaresDB.Lugares.NOMBRE_TABLA, columnas, null, null, null, null, null);

            int nombre;
            nombre = c.getColumnIndex("nombre");

            ret = new String[c.getCount()];

            int i = 0;
            // Recorremos el contenido del cursor
            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
            {
                ret[i] = c.getString(nombre);
                i++;
            }

        }catch (Exception e){

            Log.d("Excepcion: ", e.getMessage());

        } finally {

            c.close();

        }

		return ret;
	
	}

	public Cursor getLugares()
	{
		
		String[] columnas = {"_ID", "nombre", "descripcion", "latitud", "longitud", "foto"};
        Cursor c = null;

        try {

            c = this.getReadableDatabase().query(LugaresDB.Lugares.NOMBRE_TABLA, columnas, null, null, null, null, null);

        } catch (Exception e){

            Log.d("Excepcion desde getLugares(): ", e.getMessage());

        }

		return c;
		
	}	
	
	public String[] getLugarByName(String name){
		
		SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] ret = new String[6];

        try {

            // Devolvemos el lugar con nombre = name
            c = db.rawQuery("select nombre, descripcion, latitud, longitud, foto, _id from lugares where nombre = '" + name + "'", null);

            // Si el cursor no es nulo, nos vamos a la primera posici�n
            if (c != null)
                c.moveToFirst();



            ret[0] = c.getString(c.getColumnIndex("nombre"));
            ret[1] = c.getString(c.getColumnIndex("descripcion"));
            ret[2] = String.valueOf(c.getDouble(c.getColumnIndex("latitud")));
            ret[3] = String.valueOf(c.getDouble(c.getColumnIndex("longitud")));
            ret[4] = c.getString(c.getColumnIndex("foto"));
            ret[5] = String.valueOf(c.getInt(c.getColumnIndex("_id")));

        } catch (Exception e) {

            Log.d("Excepcion en getLugarByName(): ", e.getMessage());

        } finally {

            c.close();

        }

		return ret;
		
	}

	public void updateLugar(int id, String nombre, String descripcion, double latitud, double longitud, String foto) 
	{
		SQLiteDatabase db = getWritableDatabase();
		
		ContentValues valores = new ContentValues();
		
		valores.put("nombre", nombre);
		valores.put("descripcion", descripcion);
		valores.put("latitud", latitud);
		valores.put("longitud", longitud);
		valores.put("foto", foto);

        try {

            db.update(LugaresDB.Lugares.NOMBRE_TABLA, valores, "_id = " + id, null);

        } catch (Exception e){

            Log.d("Excepcion en updateLugar(): ", e.getMessage());

        } finally {

            db.close();

        }

	}

	public static SQLiteDatabase getDb() {
		return db;
	}

	public static void setDb(SQLiteDatabase db) {
		LugaresSQLHelper.db = db;
	}
}
