package inventario.model;

import java.util.*;

public class Magazzino {
	private List<Reparto> magazzino;
	
	public Magazzino() {
		magazzino = new ArrayList<Reparto>();
	}
	
	public Magazzino(List<Reparto> magazzino) {
		this.magazzino = magazzino;
	}

	public int getDimensione() {
		return magazzino.size();
	}
	
	public List<Reparto> get() {
		return magazzino;
	}
	
	public void aggiungiReparto(char id) {
		magazzino.add(new Reparto(id));
	}
	
	public void aggiungiReparto(Reparto r) {
		magazzino.add(r);
	}
	
	public void inserisciArticoloScaffale(char id, int s, Articolo a) {
		magazzino.get(id).inserisci(s, a);
	}
	
	public void inserisciArticoliScaffale(char id, int s, List<Articolo> l) {
		for(Articolo a: l) {
			inserisciArticoloScaffale(id, s, a);
		}
	}
	
	public Optional<Scaffale> trovaArticolo(String a) {
		for(Reparto r: magazzino) {
			for(Scaffale scaffale : r.getScaffali()) {
				for(int i = 0;i< r.getArticoliScaffale(scaffale).size();i++) {
					if (r.getArticoliScaffale(scaffale).get(i).getCodice().equalsIgnoreCase(a)) 
						return Optional.of(scaffale);
				}
			}
		}
		return Optional.empty();
	}
	
	public List<Articolo> getScaffale(char id, int i){
		return magazzino.get(id).getArticoliScaffale(i);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Magazzino GRANESE S.R.L. \n\n");
		for(Reparto r: magazzino) {
			sb.append(r.toString() + "\n\n");
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(magazzino);
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
		return Objects.equals(magazzino, other.magazzino);
	}


	
	
}
