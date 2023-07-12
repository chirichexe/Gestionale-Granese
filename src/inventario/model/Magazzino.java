package inventario.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Magazzino {
	private Map<Integer, List<Articolo>> magazzino;
	private int reparti;
	
	public Magazzino(int dim) {
		magazzino = new TreeMap<>();
		//ricevo la dimensione del magazzino e inizializzo una mappa che associa a
		//ogni cella del magazzino una lista vuota
		this.reparti = dim;
		for(int i = 0; i<dim; i++) {
			magazzino.put(i+1, new ArrayList<Articolo>());
		}
	}

	public int getReparti() {
		return reparti;
	}
	
	public void inserisci(int r, Articolo a) {
		if(r+1 > reparti || r < 1 || a==null) throw new IllegalArgumentException("Errore nell'inserimento dell'articolo");
		magazzino.get(r+1).add(a);
	}
	
	public void inserisci(int r, List<Articolo> l) {
		for(Articolo a: l) {
			inserisci(r, a);
		}
	}
	
	public int posizioneArticolo(Articolo a) {
		for(int i: magazzino.keySet()) {
			if (magazzino.get(i).contains(a)) return i;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Magazzino GRANESE S.R.L. \n\n");
		for(int a: magazzino.keySet()) {
			sb.append("	Scaffale " + a + ": ").append(magazzino
					.get(a)
					.stream()
					.map(i->i.toString())
					.collect(Collectors.joining("\n		"))).append("\n\n");
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(magazzino, reparti);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Magazzino other = (Magazzino) obj;
		return Objects.equals(magazzino, other.magazzino) && reparti == other.reparti;
	}
	
	
}
