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

	private static final String DATABASE_CREATE = "create table "+TABLE_LOCAL+" ("
			+ LOCAL_ID + " integer primary key autoincrement, "
			+ LOCAL_NOME + " text not null, "
			+ LOCAL_ENDERECO + " text not null,"
			+ LOCAL_DETALHES + " text null,"
			+ LOCAL_AGENDA + " text null);";

	String QUERY_INSERT_LOCAIS = "insert into "+TABLE_LOCAL
			+ " ("
			+ LOCAL_NOME + ", " 
			+ LOCAL_ENDERECO + ", " 
			+ LOCAL_DETALHES + ", " 
			+ LOCAL_AGENDA + ") values (?, ?, ?, ?);";
	
	public static final ArrayList<Object[]> createLocalList(){
		ArrayList<Object[]> locais = new ArrayList<Object[]>();		
			
		locais.add(new Object[]{"UFF",
			"Rua Passo da Pátria, 156 – Boa Viagem – Niterói",
			"Campus Praia Vermelha (campus de Engenharia da UFF) - Sala 230B – Bloco D (prédio novo)",
			"Quinta-feira às 19h00"});
		
		locais.add(new Object[]{"UFF ",
			"Rua Passo da Pátria, 156 – Boa Viagem – Niterói",
			"Campus Praia Vermelha (campus de Engenharia da UFF) - Sala 230B – Bloco D (prédio novo)",
			"Sexta-feira às 11h00"});
						
		locais.add(new Object[]{"IFF",
			"Rua Doutor Siqueira, 273 – Parque Tamandaré – Campos dos Goytacazes",
			"Campus Campos-Centro - Sala 6 – Bloco E (coordenação de Informática)",
			"Terça-feira às 16h00"});
					
		locais.add(new Object[]{"UENF",
			"Avenida Alberto Lamego, 2000 – Parque Califórnia – Campos dos Goytacazes",
			"Prédio P5, Térreo, Auditório 2",
			"Terça-feira às 18h15"});
						
		locais.add(new Object[]{"Petrópolis",
			"Avenida Getúlio Vargas, 335 – Petrópolis",
			"Sala 5 – IST/CPTI (Ao lado do LNCC)",
			"Sábados a cada 15 dias às 15h00"});
		
		return locais;
	}
	
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
			
			for (Object[] values: createLocalList()){
				db.execSQL(QUERY_INSERT_LOCAIS, values); //Populate Initial Data
			}
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
		initialValues.put(LOCAL_AGENDA, local.agenda);
	
		long result = mDb.insert(TABLE_LOCAL, null, initialValues);

		return result;
	}
	
	public Cursor fetchAllLocais(){
		Cursor c = mDb.query(TABLE_LOCAL, new String[] { LOCAL_ID,
				LOCAL_NOME, LOCAL_ENDERECO, LOCAL_DETALHES, LOCAL_AGENDA},
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
			Local l = new Local(id, nomeLocal, endereco, detalhes, agenda);
			locais.add(l);
		}
		c.close();
		return locais;
	}

	public Local getLocal(long id) {
		Local local = new Local();
		Cursor c = fetchAllLocais();
		while(c.moveToNext()){
			if (c.getLong(KEY_LOCAL_ID) == id){
			local = new Local(
					c.getLong(KEY_LOCAL_ID),
					c.getString(KEY_LOCAL_NOME),
					c.getString(KEY_LOCAL_ENDERECO),
					c.getString(KEY_LOCAL_DETALHES),
					c.getString(KEY_AGENDA));
			return local;
			}
		}
		return local;
	}
}
