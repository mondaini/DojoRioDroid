package mondaini.android.bagulhodoido.model;

public class Noticia {
	private String _url;
	private String _titulo;
	private String _conteudo; //Talvez
	private String[] _comentarios;
	//TODO: Pensar se a entidade deve ter mais itens devido à quantidade de informação necessária para armazenamento.
	
	public String get_url() {
		return _url;
	}
	public void set_url(String _url) {
		this._url = _url;
	}
	
	public String get_titulo() {
		return _titulo;
	}
	public void set_titulo(String _titulo) {
		this._titulo = _titulo;
	}
	
	public String get_conteudo() {
		return _conteudo;
	}
	public void set_conteudo(String _conteudo) {
		this._conteudo = _conteudo;
	}
	
	public String[] get_comentarios() {
		return _comentarios;
	}
	public void set_comentarios(String[] _comentarios) {
		this._comentarios = _comentarios;
	}
	
}
