package mondaini.android.dojorio.test;


import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

import mondaini.android.dojorio.model.Noticia;
import mondaini.android.dojorio.util.RSSReader;

public class Tests {
	private List<Noticia> noticiasRSS;
		
	@Test
	public void testPreencherListaNoticiasRSS(){
		this.noticiasRSS = RSSReader.getInstance().getNoticias();
		assertNotNull(noticiasRSS);
	}

}
