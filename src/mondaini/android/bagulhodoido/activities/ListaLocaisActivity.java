package mondaini.android.bagulhodoido.activities;

import java.util.List;

import mondaini.android.bagulhodoido.R;
import mondaini.android.bagulhodoido.adapters.LocalAdapter;
import mondaini.android.bagulhodoido.db.DBAdapter;
import mondaini.android.bagulhodoido.model.Local;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListaLocaisActivity extends Activity {
	private ListView lvLocais;
	private List<Local> locais;
	private LocalAdapter localAdapter;
	private DBAdapter mDb;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.local_list);
		this.lvLocais = (ListView) findViewById(R.id.listLocais);
		mDb = new DBAdapter(this);
		mDb.open();
		new GetAllLocais().execute();

	}

	class GetAllLocais extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg) {			
			ListaLocaisActivity.this.locais = mDb.getLocais();
			if (locais != null && locais.size() > 0){
				return true;
			}
			else{
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			Toast.makeText(ListaLocaisActivity.this, "Lista de notícias atualizada com sucesso!", Toast.LENGTH_LONG).show();
			if (result == true){			
				localAdapter = new LocalAdapter(ListaLocaisActivity.this, locais);
				lvLocais.setOnItemClickListener(new ListaNoticiasOnClick(ListaLocaisActivity.this, locais));
				lvLocais.setAdapter(localAdapter);
			}else{
				Toast.makeText(ListaLocaisActivity.this, "Me desculpe, não foi possível baixar o feed RSS\n:-(", Toast.LENGTH_LONG).show();
			}
			mDb.close();
		}

	}

	class ListaNoticiasOnClick implements OnItemClickListener {
		private Context context;
		private List<Local> locais;
		
		public ListaNoticiasOnClick(Context context, List<Local> locais){
			this.context = context;
			this.locais = locais;
		}

		@Override
		public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
			//TODO: Set intent, to redirect to google maps or to another activity with the mapview.
			Toast.makeText(this.context, "Voce clicou na position: "+String.valueOf(position), Toast.LENGTH_SHORT);
		}
	}
}
