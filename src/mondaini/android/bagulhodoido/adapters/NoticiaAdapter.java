package mondaini.android.bagulhodoido.adapters;

import java.util.List;

import mondaini.android.bagulhodoido.model.Noticia;
import mondaini.android.dojorio.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoticiaAdapter extends BaseAdapter{
	private Context context;
	private List<Noticia> lista;
	private LayoutInflater inflater;

	public NoticiaAdapter(Context context, List<Noticia> lista){
		this.context = context;
		this.lista = lista;		
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
		TextView textViewTitulo;
		TextView textViewDescricaoNoticia;
		TextView textViewPublishDate;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null){
			inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.noticia_row, null);

			holder = new ViewHolder();
			holder.textViewTitulo = (TextView) convertView.findViewById(R.id.textTituloNoticia);
			holder.textViewDescricaoNoticia = (TextView) convertView.findViewById(R.id.textDescricaoNoticia);
			holder.textViewPublishDate = (TextView) convertView.findViewById(R.id.textPublishDate);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}

		Noticia noticia = lista.get(position);
		holder.textViewTitulo.setText("\n"+noticia.titulo+"\n");
		holder.textViewDescricaoNoticia.setText(noticia.descricao+"\n");
		holder.textViewPublishDate.setText(noticia.publishDate+"\n");

		return convertView;

	}
}
