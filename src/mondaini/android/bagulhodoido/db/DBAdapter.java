package mondaini.android.bagulhodoido.db;

import java.util.ArrayList;
import java.util.List;

import mondaini.android.bagulhodoido.model.Local;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	
	private static final String DATABASE_NAME = "db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_LOCAL = "local";
	
	/** column names **/
	public static final String LOCAL_ID = "_id";
	public static final String LOCAL_NOME = "nome";
	public static final String LOCAL_ENDERECO = "endereco";
	public static final String LOCAL_DETALHES = "detalhes";
	public static final String LOCAL_LATITUDE = "latidude"; 
	public static final String LOCAL_LONGITUDE = "longitude";	
	public static final String LOCAL_AGENDA = "agenda";
	
	/** column keys **/
	public static final int KEY_LOCAL_ID = 0;
	public static final int KEY_LOCAL_NOME = 1;
	public static final int KEY_LOCAL_ENDERECO = 2;
	public static final int KEY_LOCAL_DETALHES = 3;
	public static final int KEY_AGENDA = 4;
	public static final int KEY_LOCAL_LATITUDE = 5; 
	public static final int KEY_LOCAL_LONGITUDE = 6;	

	
	private static final String DATABASE_CREATE = "create table "+TABLE_LOCAL+" ("
			+ LOCAL_ID + " integer primary key autoincrement, "
			+ LOCAL_NOME + " text not null, "
			+ LOCAL_ENDERECO + " text not null,"
			+ LOCAL_DETALHES + " text null,"
			+ LOCAL_AGENDA + " text null,"
			+ LOCAL_LATITUDE + " real null, "
			+ LOCAL_LONGITUDE + " real null);";

	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	private Context context;	
		
	private class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}
	
	public DBAdapter (Context context){
		this.context = context;
	}
	
	public DBAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(this.context);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}
	
	public boolean deleteLocal(long rowId){
		boolean b = mDb.delete(TABLE_LOCAL, LOCAL_ID + "=" + rowId, null) > 0;
		return b;
	}
	
	public long createLocal(Local local) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(LOCAL_NOME, local.nomeLocal);
		initialValues.put(LOCAL_ENDERECO, local.endereco);
		initialValues.put(LOCAL_DETALHES, local.detalhes);
		initialValues.put(LOCAL_LATITUDE, local.getLatitude());
		initialValues.put(LOCAL_LATITUDE, local.getLongitude());
		initialValues.put(LOCAL_AGENDA, local.agenda);
	
		long result = mDb.insert(TABLE_LOCAL, null, initialValues);

		return result;
	}
	
	public Cursor fetchAllLocais(){
		Cursor c = mDb.query(TABLE_LOCAL, new String[] { LOCAL_ID,
				LOCAL_NOME, LOCAL_ENDERECO, LOCAL_DETALHES, LOCAL_AGENDA, LOCAL_LATITUDE, LOCAL_LONGITUDE},
				null, null, null, null, null);
		return c;
	}

	public List<Local> getLocais() {

		Cursor c = fetchAllLocais();
		ArrayList<Local> locais = new ArrayList<Local>();
		while (c.moveToNext()) {
			Long id = c.getLong(KEY_LOCAL_ID);
			String nomeLocal = c.getString(KEY_LOCAL_NOME);
			String endereco = c.getString(KEY_LOCAL_ENDERECO);
			String detalhes = c.getString(KEY_LOCAL_DETALHES);
			String agenda = c.getString(KEY_AGENDA);
			long latitude = c.getLong(KEY_LOCAL_LATITUDE);
			long longitude = c.getLong(KEY_LOCAL_LONGITUDE);
			Local l = new Local(id, nomeLocal, endereco, detalhes, agenda, latitude, longitude);
			locais.add(l);
		}
		c.close();
		return locais;
	}
	
}
