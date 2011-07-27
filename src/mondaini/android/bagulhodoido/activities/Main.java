package mondaini.android.bagulhodoido.activities;

import java.util.ArrayList;
import java.util.List;

import mondaini.android.bagulhodoido.R;
import mondaini.android.bagulhodoido.adapters.NoticiaAdapter;
import mondaini.android.bagulhodoido.model.Noticia;
import mondaini.android.bagulhodoido.util.RSSReader;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;


public class Main extends Activity{
	private ListView lvNoticias; 
	private List<Noticia> listNoticias;
	private NoticiaAdapter noticiaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);		
		
		final ProgressDialog dialog = ProgressDialog.show(Main.this, "", "Loading. Please wait...", true);
		
		new Thread(){
			@Override
			public void run() {				
				listNoticias = new RSSReader().getNoticias();
				//listNoticias = new ArrayList<Noticia>();
				//listNoticias.add(new Noticia("aaa", "bbb", "ccc", "ddd"));
				
				Bundle bundle = new Bundle();
				bundle.putBoolean("loading", true);
				
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
			Boolean result = bundle.getBoolean("loading");
			if (result != null && result == true){
				noticiaAdapter = new NoticiaAdapter(Main.this, listNoticias);
				lvNoticias = (ListView)findViewById(R.id.listNoticias);
				lvNoticias.setAdapter(noticiaAdapter);
			}
		};
	};
	
	
	//TODO Criar Tab com os Endereços do local onde surgem os eventos
	//TODO Criar Tab com as noticias do Blog	
	//TODO Listar Endereços do Google Maps para abrir aqui no app.
	
}
