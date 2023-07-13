package inventario.model;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import inventario.controller.Controller;
import inventario.controller.ControllerGranese;
import inventario.persistence.*;

public class MyMain {

	public static void main(String[] args) throws IOException, BadFileFormatException {
		
		Reparto r = new Reparto("PianoSotto");
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
		
		Reparto s = new Reparto("PianoSopra");
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
		
		m.aggiungiReparto("test");
		m.inserisciArticoloScaffale("test", 5, new Articolo("Articolo31", "vjs"));
		m.inserisciArticoloScaffale("test", 3, new Articolo("Articolo32", "vjs"));
		
		//prova reader
		/*
		FileWriter w = new FileWriter("magazzino.txt");
		w.append("ciao\n");
		w.append("casc");
		w.close();*/
		
		Controller controller = new ControllerGranese(m);
		System.out.println(controller.stampaMagazzino());
		controller.aggiungiArticolo(Scaffale.of(1, "PianoSotto"), new Articolo("XXXXXX"));
		System.out.println("------------------------------");
		System.out.println(controller.stampaMagazzino());
	}

}
