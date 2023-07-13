package inventario.model;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import inventario.persistence.*;

public class MyMain {

	public static void main(String[] args) throws IOException, BadFileFormatException {
		Reparto r = new Reparto('A');
		r.inserisci(1, new Articolo("CHRDVD", "CIAO"));
		r.inserisci(1, new Articolo("CHRCH"));
		r.inserisci(1, new Articolo("TEST"));
		r.inserisci(1, new Articolo("PAR", "WG"));
		r.inserisci(2, new Articolo("OH", "GRANDI"));
		r.inserisci(2, new Articolo("OH", "EF"));
		r.inserisci(2, new Articolo("OH", "CDE"));
		List<Articolo> test = new ArrayList<Articolo>();
		test.add(new Articolo("ciao"));
		test.add(new Articolo("zio"));
		test.add(new Articolo("come"));
		test.add(new Articolo("va", "a casa"));
		r.inserisci(4, test);
		r.inserisci(3, new Articolo("Provola"));
		r.inserisci(20, new Articolo("uf"));
		r.inserisci(8, new Articolo("aa"));
		
		Reparto s = new Reparto('B');
		s.inserisci(1, new Articolo("wve", "CIAO"));
		s.inserisci(1, new Articolo("CHRCHw"));
		s.inserisci(1, new Articolo("TESTw"));
		s.inserisci(1, new Articolo("PAR", "WG"));
		List<Articolo> test1 = new ArrayList<Articolo>();
		test1.add(new Articolo("ciao"));
		test1.add(new Articolo("zio"));
		test1.add(new Articolo("come"));
		s.inserisci(4, test);
		s.inserisci(3, new Articolo("Prova"));
		
		Magazzino m = new Magazzino();
		m.aggiungiReparto(r);
		m.aggiungiReparto(s);
		System.out.println(m.trovaArticolo("uf"));
	}

}
