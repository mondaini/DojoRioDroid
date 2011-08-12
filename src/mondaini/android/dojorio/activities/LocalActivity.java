package mondaini.android.dojorio.activities;

import mondaini.android.dojorio.R;
import mondaini.android.dojorio.db.DBAdapter;
import mondaini.android.dojorio.db.json.JsonResponse;
import mondaini.android.dojorio.db.json.PlacemarkEntity;
import mondaini.android.dojorio.model.Local;
import mondaini.android.dojorio.util.MapPointOverlay;
import mondaini.android.dojorio.util.Validations;

import org.springframework.web.client.RestTemplate;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.gson.Gson;

public class LocalActivity extends MapActivity{
	private DBAdapter mDb;	
	private MapView map;
	private TextView textNomeLocal; 
	private TextView textEndereco;
	private TextView textDetalhes;
	private TextView textAgenda;
	private Local local;
	public String response;
	private GeoPoint geoPoint;
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {			
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.local_map);		
		
		long idLocal = getIntent().getExtras().getLong("idLocal");
		
		findViews();
				
		local = getLocal(idLocal);
		
		setLocalTextViews(local);
		
		if (Validations.isOnline(this)){
			new requestAdressToGoogleMaps().execute();
		}
		else{
			Toast.makeText(this, "Não foi possível carregar mapa.\nSem acesso à internet.", Toast.LENGTH_SHORT).show();
		}
	}
	
	private GeoPoint setLocalMapCoordinates(String endereco) {
		String url = "http://maps.google.com/maps/geo?q={endereco}&output={output}&key={key}";
		String output = "json";
		String key = "0_CK7uBHqhyrxPsgFVRCpPU3_B_t3Egk54Q7xTQ";
		double longitude = 0;
		double latitude = 0;
		
		RestTemplate rest = new RestTemplate();
		String response = (String)rest.getForObject(url, String.class, endereco, output, key);
		Gson gson = new Gson();
		JsonResponse json = gson.fromJson(response, JsonResponse.class);

		for (PlacemarkEntity placemark : json.getPlacemark()){
			longitude = placemark.getPoint().getCoordinates()[0];
			latitude = placemark.getPoint().getCoordinates()[1];
		}

		json.getPlacemark();
		
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
	
	private void setMapConfiguration(){
		MapPointOverlay overlay = new MapPointOverlay(geoPoint, R.drawable.map_point);			
		map.getOverlays().add(overlay);
		map.setBuiltInZoomControls(true);		
		MapController mapController = map.getController();
		mapController.setZoom(17);
		mapController.setCenter(geoPoint);	
	}
	
	class requestAdressToGoogleMaps extends AsyncTask<Void, Void, Boolean>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(LocalActivity.this, "Buscando endereço", "Aguarde...", true);
		}
		
		@Override
		protected Boolean doInBackground(Void... arg0) {
			geoPoint = setLocalMapCoordinates(local.endereco);
			return null;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			setMapConfiguration();			
			dialog.dismiss();
			super.onPostExecute(result);
		}
	}
}
