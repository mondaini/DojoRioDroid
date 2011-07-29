package mondaini.android.bagulhodoido.util;

/**
 * @author vijay
 * @URL: http://javamix.wordpress.com/2009/06/04/read-rss-feeds-by-using-java/
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import mondaini.android.bagulhodoido.model.Noticia;
import android.util.Log;

public class RSSReader {

	private static RSSReader instance = null;
	private List<Noticia> listaNoticias;
	
	
	public static RSSReader getInstance() {
		if(instance == null) {
			instance = new RSSReader();
		}
		return instance;
	}

	public List<Noticia> getNoticias() {
		try {

			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			URL u = new URL("http://dojorio.wordpress.com/feed/"); 

			Document doc = builder.parse(u.openStream());

			NodeList nodes = doc.getElementsByTagName("item");
			
			this.listaNoticias = new ArrayList<Noticia>();
			Noticia noticia;
			for(int i=0;i<nodes.getLength();i++) {				
				Element element = (Element)nodes.item(i);
				noticia = new Noticia(
						getElementValue(element,"link"),
						getElementValue(element,"title"),
						getElementValue(element,"description"),
						getElementValue(element,"pubDate"));
				listaNoticias.add(noticia);
			}
			return listaNoticias;
		}
		catch(Exception ex) {
			Log.e("BagulhoDoido", "Erro fatal em getNoticias(): "+ex.toString());
			return new ArrayList<Noticia>();
		}
	}

	private String getCharacterDataFromElement(Element e) {
		try {
			Node child = e.getFirstChild();
			if(child instanceof CharacterData) {
				CharacterData cd = (CharacterData) child;
				return cd.getData();
			}
		}
		catch(Exception ex) {
			Log.e("BagulhoDoido", "Erro fatal em getCharacterDataFromElement(): "+ex.getMessage());
		}
		return "";
	} 

	protected float getFloat(String value) {
		if(value != null && !value.equals("")) {
			return Float.parseFloat(value);
		}
		return 0;
	}

	protected String getElementValue(Element parent,String label) {
		return getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));
	}
}