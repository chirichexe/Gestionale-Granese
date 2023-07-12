package inventario.model;

import java.util.ArrayList;
import java.util.List;

public class MyMain {

	public static void main(String[] args) {
		Magazzino m = new Magazzino(20);
		Articolo test1 = new Articolo("REEL10283", "BTC");
		m.inserisci(4, test1);
		
		List<Articolo> test2 = new ArrayList<Articolo>();
		test2.add(new Articolo("TEST1", "PALLE"));
		test2.add(new Articolo("TEST2", "PALLE"));
		test2.add(new Articolo("TEST3", "PALLO"));
		m.inserisci(2, test2);
		
		System.out.println(m.toString());
		
		
	}

}
