package me.jlgarcia.mislugares.db;

import android.provider.BaseColumns;

public class LugaresDB {

	public static final String DB_NAME = "lugares.db";
	public static final int DB_VERSION = 1;
	
	// impedimos que actue el constructor por defecto
	public LugaresDB(){}
	
	
	public static final class Lugares implements BaseColumns
	{
		private Lugares(){}
		
		public static final String NOMBRE_TABLA = "lugares";
		public static final String _ID = "_id";
		public static final String NOMBRE = "nombre";
		public static final String DESCRIPCION = "descripcion";
		public static final String LATITUD = "latitud";
		public static final String LONGITUD = "longitud";
		public static final String FOTO = "foto";
		
	}
	
}
