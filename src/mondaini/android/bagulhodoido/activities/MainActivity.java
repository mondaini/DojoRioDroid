package mondaini.android.bagulhodoido.activities;

import mondaini.android.bagulhodoido.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);		
		
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        
        intent = new Intent(getApplicationContext(), ListaNoticiasActivity.class);
        spec = tabHost.newTabSpec("noticias").setIndicator("Blog", res.getDrawable(R.drawable.rss_high)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent(getApplicationContext(), ListaLocaisActivity.class);
        spec = tabHost.newTabSpec("locais").setIndicator("Onde?", res.getDrawable(R.drawable.calendar_high)).setContent(intent);
        tabHost.addTab(spec);
		
	}
			
	//TODO Implementar Tab com os Endereços do local onde surgem os eventos
	//TODO Finalizar implementação da Tab com as noticias do Blog: eventos nos itens da lista, mais informações, etc.	
	//TODO Listar Endereços do Google Maps para abrir aqui no app.
	
}
