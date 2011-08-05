package mondaini.android.bagulhodoido.model;


public class Local {
	private long id;
	public String nomeLocal;
	public String endereco;
	public String detalhes;
	public String agenda;

		
	public Local(long id, String nome, String endereco, String detalhes, String agenda) {
		super();
		this.id = id;
		this.nomeLocal = nome;
		this.endereco = endereco;
		this.detalhes = detalhes;
		this.agenda = agenda;
	}
	
	public Local(String nome, String endereco, String detalhes, String agenda) {
		super();
		this.nomeLocal = nome;
		this.endereco = endereco;
		this.detalhes = detalhes;
		this.agenda = agenda;
	}

	public Local() {}

	//TODO: Create a better solution here.	
	public long getId(){
		return id;
	}
}
