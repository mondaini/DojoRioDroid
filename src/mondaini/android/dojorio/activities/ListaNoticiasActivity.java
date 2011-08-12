package mondaini.android.dojorio.activities;

import java.util.List;

import mondaini.android.dojorio.R;
import mondaini.android.dojorio.adapters.NoticiaAdapter;
import mondaini.android.dojorio.model.Noticia;
import mondaini.android.dojorio.util.RSSReader;
import mondaini.android.dojorio.util.Validations;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListaNoticiasActivity extends Activity{
	private ListView lvNoticias;
	private List<Noticia> noticias;
	private NoticiaAdapter noticiaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		setContentView(R.layout.noticia_list);
		
		this.lvNoticias = (ListView)this.findViewById(R.id.listNoticias);
		
		if (Validations.isOnline(this)){
			new DownloadRSSFeedTask().execute();
		}
		else{
			Toast.makeText(this, "Não foi possível carregar notícias.\nSem acesso à internet.", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	class DownloadRSSFeedTask extends AsyncTask<Void, Void, Boolean>{   
				
		@Override
		protected void onPreExecute() {
			Toast.makeText(ListaNoticiasActivity.this, "Aguarde, as últimas notícias do blog estão sendo carregadas...", Toast.LENGTH_SHORT).show();
		}
		
	    @Override
	    protected void onPostExecute(Boolean result) {	  	    	
			if (result == true){
				Toast.makeText(ListaNoticiasActivity.this, "Lista de notícias atualizada com sucesso!", Toast.LENGTH_LONG).show();
				noticiaAdapter = new NoticiaAdapter(ListaNoticiasActivity.this, noticias);			
				lvNoticias.setOnItemClickListener(new OnClickListaNoticias(ListaNoticiasActivity.this, noticias));
				lvNoticias.setAdapter(noticiaAdapter);
			}else{
				Toast.makeText(ListaNoticiasActivity.this, "Me desculpe, não foi possível baixar o feed RSS\n:-(", Toast.LENGTH_LONG).show();
			}    	
	    }

		@Override
		protected Boolean doInBackground(Void... params) {		
			noticias = new RSSReader().getNoticias();
			if (noticias.size() != 0){
				return true;		
			}
			else{ 
				return false;
			}
		}		
	}	

	class OnClickListaNoticias implements OnItemClickListener{
		private Context context;
		private List<Noticia> noticias;
		
		public OnClickListaNoticias(Context context, List<Noticia> noticias){
			this.context = context;
			this.noticias = noticias;
		}
		
		@Override
		public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
			Noticia noticia = noticias.get(position);
			callBrowserIntent(noticia.link);
		}
		
		public void callBrowserIntent(String link){
			Uri uri = Uri.parse(link);
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			context.startActivity(it);
		}
		
	}

}

