package mondaini.android.bagulhodoido.activities;

import java.util.List;

import mondaini.android.bagulhodoido.R;
import mondaini.android.bagulhodoido.adapters.NoticiaAdapter;
import mondaini.android.bagulhodoido.model.Noticia;
import mondaini.android.bagulhodoido.util.RSSReader;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

		final ProgressDialog dialog = ProgressDialog.show(ListaNoticias.this, "", "Loading. Please wait...", true);

		new Thread(){
			@Override
			public void run() {
				noticias = new RSSReader().getNoticias();
				//noticias = new ArrayList<Noticia>();
				//noticias.add(new Noticia("aaa", "bbb", "ccc", "ddd"));

				Bundle bundle = new Bundle();
				
				if (noticias.size() == 0){
					bundle.putBoolean("loaded", false);						
				}
				else{ 
					bundle.putBoolean("loaded", true);
				}

				Message msg = new Message();
				msg.setData(bundle);

				handler.sendMessage(msg);
				dialog.dismiss();
			}
		}.start();
	}

	private Handler handler = new Handler(){

		
		@Override
		public void handleMessage(android.os.Message msg) {
			Bundle bundle = msg.getData();
			Boolean result = bundle.getBoolean("loaded");
			if (result != null && result == true){
				noticiaAdapter = new NoticiaAdapter(ListaNoticias.this, noticias);
				lvNoticias = (ListView)findViewById(R.id.listNoticias);
				lvNoticias.setOnItemClickListener(new ListaNoticiasOnClick(ListaNoticias.this, noticias));
				lvNoticias.setAdapter(noticiaAdapter);
			}else if (result == false){
				Toast.makeText(getApplicationContext(), "Me desculpe, não foi possível baixar o feed RSS\n:-(", Toast.LENGTH_LONG).show();
			}

		};
	};

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