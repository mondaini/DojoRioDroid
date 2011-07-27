package mondaini.android.bagulhodoido.adapters;

import java.util.List;

import mondaini.android.bagulhodoido.R;
import mondaini.android.bagulhodoido.model.Noticia;
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
		//TODO: Test if this thing really works.
	}

	static class ViewHolder{
		TextView tvTitulo;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null){
			inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.noticia_row, null);

			holder = new ViewHolder();
			holder.tvTitulo = (TextView) convertView.findViewById(R.id.textTituloNoticia);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}

		Noticia noticia = lista.get(position);		 
		holder.tvTitulo.setText(noticia.titulo);

		return convertView;

	}
}
