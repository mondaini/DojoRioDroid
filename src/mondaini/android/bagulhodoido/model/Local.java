package mondaini.android.bagulhodoido.model;


public class Local {
	private long id;
	public String nomeLocal;
	public String endereco;
	public String detalhes;
	public String agenda;
	private long latitude; 
	private long longitude;

		
	public Local(long id, String nome, String endereco, String detalhes, String agenda, long latitude, long longitude) {
		super();
		this.id = id;
		this.nomeLocal = nome;
		this.endereco = endereco;
		this.detalhes = detalhes;
		this.agenda = agenda;
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}
	
	public Local(String nome, String endereco, String detalhes, String agenda, long latitude, long longitude) {
		super();
		this.nomeLocal = nome;
		this.endereco = endereco;
		this.detalhes = detalhes;
		this.agenda = agenda;
		this.setLatitude(latitude);
		this.setLongitude(longitude);
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
