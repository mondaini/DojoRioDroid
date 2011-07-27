package mondaini.android.bagulhodoido.activities;

import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import mondaini.android.bagulhodoido.R;
import mondaini.android.bagulhodoido.adapters.NoticiaAdapter;
import mondaini.android.bagulhodoido.model.Noticia;
import mondaini.android.bagulhodoido.util.RSSReader;

public class Main extends Activity{
	private ListView listNoticias; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		this.listNoticias = (ListView)findViewById(R.id.listNoticias);
		
		//TODO: chamar uma thread e incluir dialog para o loading 
		List<Noticia> listaNoticias = RSSReader.getInstance().getNoticias();
		//This is just to get it right before I implement the thread thing :)
//		List<Noticia> listaNoticias = new ArrayList<Noticia>();
//		listaNoticias.add(new Noticia("aaa", "bbb", "ccc", "ddd"));
//		listaNoticias.add(new Noticia("aaa", "bbb", "ccc", "ddd"));
//		listaNoticias.add(new Noticia("aaa", "bbb", "ccc", "ddd"));
//		listaNoticias.add(new Noticia("aaa", "bbb", "ccc", "ddd"));
		
		
		NoticiaAdapter na = new NoticiaAdapter(this, listaNoticias);		
		this.listNoticias.setAdapter(na);
			
	}
		
	//TODO Criar Tab com os Endereços do local onde surgem os eventos
	//TODO Criar Tab com as noticias do Blog	
	//TODO Listar Endereços do Google Maps para abrir aqui no app.
	
}
