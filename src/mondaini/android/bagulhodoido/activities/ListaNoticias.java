package mondaini.android.bagulhodoido.activities;

import java.util.List;

import mondaini.android.bagulhodoido.R;
import mondaini.android.bagulhodoido.adapters.NoticiaAdapter;
import mondaini.android.bagulhodoido.model.Noticia;
import mondaini.android.bagulhodoido.util.RSSReader;
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

public class ListaNoticias extends Activity{
	private ListView lvNoticias;
	private List<Noticia> noticias;
	private NoticiaAdapter noticiaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.noticia_list);
		
		this.lvNoticias = (ListView)this.findViewById(R.id.listNoticias);
		new DownloadRSSFeedTask().execute();
	}
	
	
	class DownloadRSSFeedTask extends AsyncTask<Void, Void, Boolean>{   
				
		@Override
		protected void onPreExecute() {
			Toast.makeText(ListaNoticias.this, "Aguarde, as últimas notícias do blog estão sendo carregadas...", Toast.LENGTH_SHORT).show();
		}
		
	    @Override
	    protected void onPostExecute(Boolean result) {	  
	    	Toast.makeText(ListaNoticias.this, "Lista de notícias atualizada com sucesso!", Toast.LENGTH_LONG).show();
			if (result == true){			
				noticiaAdapter = new NoticiaAdapter(ListaNoticias.this, noticias);			
				lvNoticias.setOnItemClickListener(new ListaNoticiasOnClick(ListaNoticias.this, noticias));
				lvNoticias.setAdapter(noticiaAdapter);
			}else{
				Toast.makeText(ListaNoticias.this, "Me desculpe, não foi possível baixar o feed RSS\n:-(", Toast.LENGTH_LONG).show();
			}    	
	    }

		@Override
		protected Boolean doInBackground(Void... params) {			
			noticias = new RSSReader().getNoticias();
			if (noticias!=null){
				return true;		
			}
			else{ 
				return false;
			}
		}		
	}	

	class ListaNoticiasOnClick implements OnItemClickListener{
		private Context context;
		private List<Noticia> noticias;
		
		public ListaNoticiasOnClick(Context context, List<Noticia> noticias){
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

