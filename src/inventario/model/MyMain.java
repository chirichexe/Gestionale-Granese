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
		r.inserisci(3, new Articolo("Prova"));
		r.inserisci(20, new Articolo("uf"));
		r.inserisci(8, new Articolo("aa"));
		System.out.println(r.toString());
	}

}
