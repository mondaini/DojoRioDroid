package mondaini.android.bagulhodoido.model;


public class Local {
	private long id;
	public String nomeLocal;
	public String endereco;
	private long latitude; 
	private long longitude;
	public String agenda;
		
	public Local(long id, String nome, String endereco, long latitude, long longitude, String agenda) {
		super();
		this.id = id;
		this.nomeLocal = nome;
		this.endereco = endereco;
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.agenda = agenda;
	}

	//TODO: Create a better solution here.	
	public long getId(){
		return id;
	}
	
	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}	
}
