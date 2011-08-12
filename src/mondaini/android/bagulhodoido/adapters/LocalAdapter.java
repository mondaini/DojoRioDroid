package mondaini.android.bagulhodoido.adapters;

import java.util.List;

import mondaini.android.bagulhodoido.model.Local;
import mondaini.android.dojorio.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LocalAdapter extends BaseAdapter{
	private Context context;
	private List<Local> lista;
	private LayoutInflater inflater;

	public LocalAdapter(Context context, List<Local> locais){
		this.context = context;
		this.lista = locais;		
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder{
		TextView textViewNomeLocal;
		TextView textViewEndereco;
		TextView textViewDetalhes;
		TextView textViewAgenda;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null){
			inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.local_row, null);

			holder = new ViewHolder();
			holder.textViewNomeLocal = (TextView) convertView.findViewById(R.id.textNomeLocal);
			holder.textViewEndereco = (TextView) convertView.findViewById(R.id.textEndereco);
			holder.textViewDetalhes = (TextView) convertView.findViewById(R.id.textDetalhes);
			holder.textViewAgenda = (TextView) convertView.findViewById(R.id.textAgenda);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}

		Local local = lista.get(position);
		holder.textViewNomeLocal.setText("\n"+local.nomeLocal+"\n");
		holder.textViewEndereco.setText(local.endereco+"\n");
		holder.textViewDetalhes.setText(local.detalhes+"\n");
		holder.textViewAgenda.setText(local.agenda+"\n");

		return convertView;

	}
}
