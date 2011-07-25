package mondaini.android.bagulhodoido.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import mondaini.android.bagulhodoido.*;

public class Main extends Activity{
	
	private ListView listNoticias; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mapAllActivityObjects();
		
			
	}

	private void mapAllActivityObjects(){
		this.listNoticias = (ListView)findViewById(R.id.listNoticias);
	}
	
	
	//TODO Criar Tab com os Endereços do local onde surgem os eventos
	//TODO Criar Tab com as noticias do Blog	
	//TODO Abrir ListActivity Aqui dentro.
	//TODO Listar Notícias de Algum RSS (para mostrar na listactivity
	//TODO Listar Endereços do Google Maps para abrir aqui no app.
	
}
