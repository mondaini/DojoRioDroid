package mondaini.android.dojorio.model;

public class Noticia {
	public String link;
	public String titulo;
	public String descricao;
	public String publishDate;
	
	public Noticia(String link, String titulo, String descricao, String publishDate){
		this.link = link;
		this.titulo = titulo;
		this.descricao = descricao;
		this.publishDate = publishDate;
	}
}
