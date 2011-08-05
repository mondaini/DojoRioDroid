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
        spec = tabHost.newTabSpec("noticias").setIndicator("Blog", res.getDrawable(R.drawable.rss)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent(getApplicationContext(), ListaLocaisActivity.class);
        spec = tabHost.newTabSpec("locais").setIndicator("Onde?", res.getDrawable(R.drawable.calendar)).setContent(intent);
        tabHost.addTab(spec);
		
	}
	
}
