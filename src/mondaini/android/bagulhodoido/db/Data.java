package mondaini.android.bagulhodoido.db;

import java.util.ArrayList;
import java.util.List;

import mondaini.android.bagulhodoido.model.Local;
import android.content.Context;
import android.util.Log;



public class Data {
	private Context context;	
	private DBAdapter mDb;

	public Data(Context context){
		this.context = context;
	}

	public void insertInitialData(){
		mDb = new DBAdapter(this.context);

		try{
			mDb.open();			
			for (Local local : createLocalList()){
				mDb.createLocal(local);
			}
		}
		catch(Exception ex){
			Log.e("insertInitialData", ex.getMessage());
		}
		finally{
			mDb.close();
		}
	}

	private List<Local> createLocalList(){
		ArrayList<Local> locais = new ArrayList<Local>();
		locais.add(new Local("UFF",
				"Rua Passo da Pátria, 156 – Boa Viagem – Niterói",
				"Campus Praia Vermelha (campus de Engenharia da UFF) – Sala 230B – Bloco D (prédio novo)",
				"Quinta-feira às 19h00",
				0l,
				0l));

		locais.add(new Local("UFF",
				"Rua Passo da Pátria, 156 – Boa Viagem – Niterói",
				"Campus Praia Vermelha (campus de Engenharia da UFF) – Sala 230B – Bloco D (prédio novo)",
				"Sexta-feira às 11h00",
				0l,
				0l));

		locais.add(new Local("IFF",
				"Rua Doutor Siqueira, 273 – Parque Tamandaré – Campos dos Goytacazes",
				"Campus Campos-Centro – Sala 6 – Bloco E (coordenação de Informática)",
				"Terça-feira às 16h00",
				0l,
				0l));

		locais.add(new Local("UENF",
				"Avenida Alberto Lamego, 2000 – Parque Califórnia – Campos dos Goytacazes",
				"Prédio P5, Térreo, Auditório 2",
				"Terça-feira às 18h15",
				0l,
				0l));

		locais.add(new Local("Petrópolis",
				"Avenida Getúlio Vargas, 335 – Petrópolis",
				"Sala 5 – IST/CPTI (Ao lado do LNCC)",
				"Sábados a cada 15 dias às 15h00",
				0l,
				0l));
		return locais;
	}
}
