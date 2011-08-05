package mondaini.android.bagulhodoido.activities;

import mondaini.android.bagulhodoido.R;
import mondaini.android.bagulhodoido.db.DBAdapter;
import mondaini.android.bagulhodoido.model.Local;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class LocalActivity extends MapActivity{
	private DBAdapter mDb;	
	private MapView map;
	private TextView textNomeLocal; 
	private TextView textEndereco;
	private TextView textDetalhes;
	private TextView textAgenda;
	private Local local;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {			
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.local_map);		
		
		long idLocal = getIntent().getExtras().getLong("idLocal");
		
		findViews();
				
		local = getLocal(idLocal);
		setLocalTextViews(local);
		
		MapController mapController = map.getController();
		mapController.setZoom(21);
		mapController.setCenter(setLocalMapCoordinates(local.endereco));	
	}
	
	private GeoPoint setLocalMapCoordinates(String endereco) {
		//FIXME
		double longitude = -122.0828878;
		double latitude = 37.4220881;
		
		return new GeoPoint(calculateLatLng(latitude), calculateLatLng(longitude));	
	}
	
	private void findViews() {
		map = (MapView)findViewById(R.id.mapa);
		textNomeLocal = (TextView)findViewById(R.id.textNomeLocalMap);
		textEndereco = (TextView)findViewById(R.id.textEnderecoMap);
		textDetalhes = (TextView)findViewById(R.id.textDetalhesMap);
		textAgenda = (TextView)findViewById(R.id.textAgendaMap);		
	}

	private Local getLocal(long id) {
		Local local = null;
		try{
			mDb = new DBAdapter(getApplicationContext());
			mDb.open();
			local = mDb.getLocal(id);
		}
		catch(Exception ex){
			Log.e("BagulhoDoido", ex.getMessage());
		}
		finally{
			mDb.close();
		}
		return local;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	private int calculateLatLng(double value){
		return (int)(value*1E6);
	}
	
	private void setLocalTextViews(Local l){
		textNomeLocal.setText(local.nomeLocal);
		textEndereco.setText(local.endereco);
		textDetalhes.setText(local.detalhes);
		textAgenda.setText(local.agenda);
	}
	
}
