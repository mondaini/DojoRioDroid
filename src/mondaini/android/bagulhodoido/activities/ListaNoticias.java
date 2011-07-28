package mondaini.android.bagulhodoido.activities;

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
import android.widget.ListView;


public class ListaNoticias extends Activity{
	private ListView lvNoticias; 
	private List<Noticia> listNoticias;
	private NoticiaAdapter noticiaAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.noticia_list);		
		
		final ProgressDialog dialog = ProgressDialog.show(ListaNoticias.this, "", "Loading. Please wait...", true);
		
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
				noticiaAdapter = new NoticiaAdapter(ListaNoticias.this, listNoticias);
				lvNoticias = (ListView)findViewById(R.id.listNoticias);
				lvNoticias.setAdapter(noticiaAdapter);
			}
		};
	};
	
}
