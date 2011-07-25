package mondaini.android.bagulhodoido.model;

public class Lugar {
	private String _nome;
	private String _endereco;
	private long _latitude;
	private long _longitude;
	
	public String get_nome() {
		return _nome;
	}
	public void set_nome(String _nome) {
		this._nome = _nome;
	}
	
	public String get_endereco() {
		return _endereco;
	}
	public void set_endereco(String _endereco) {
		this._endereco = _endereco;
	}
	
	public long get_latitude() {
		return _latitude;
	}
	public void set_latitude(long _latitude) {
		this._latitude = _latitude;
	}
	
	public long get_longitude() {
		return _longitude;
	}
	public void set_longitude(long _longitude) {
		this._longitude = _longitude;
	}
}
