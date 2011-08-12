package mondaini.android.bagulhodoido.activities;

import mondaini.android.dojorio.R;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TabHost;

public class MainActivity extends TabActivity{
	private static final int LICENSE = 0;
	
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0, LICENSE, 0, "Sobre");
		item.setOnMenuItemClickListener(new MenuSobreItem());
		return true;
	}
	
	class MenuSobreItem implements OnMenuItemClickListener{

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			Intent it = new Intent(MainActivity.this, License.class);
			startActivity(it);
			return true;
		}
		
	}
}
