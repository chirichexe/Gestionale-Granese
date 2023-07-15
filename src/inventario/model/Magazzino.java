package inventario.model;

import java.util.*;
import java.util.stream.Collectors;

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
	
	public List<Articolo> getArticoli() {
		List<Articolo> totaleArticoli = new ArrayList<>();
		for(Reparto r: magazzino) {
			for(Scaffale s: r.getScaffali()) {
				totaleArticoli.addAll(r.getArticoliScaffale(s));
			}
		}
		return totaleArticoli;
	}
	
	public boolean containsReparto(String r) {
		return magazzino.stream().filter(i->i.getID().equals(r)).collect(Collectors.toList()).size()>0;
	}
	
	public Optional<Reparto> getReparto(String r) {
		for(int i = 0; i<magazzino.size() ;i++) {
			if (magazzino.get(i).getID().equals(r)) return Optional.of(magazzino.get(i));
		}
		return Optional.empty();
	}
	
	public List<Reparto> get() {
		return magazzino;
	}
	
	public void aggiungiReparto(String id) {
		magazzino.add(new Reparto(id));
	}
	
	public void aggiungiReparto(Reparto r) {
		magazzino.add(r);
	}
	
	public boolean inserisciArticoloScaffale(String id, int s, Articolo a) {
		if (!containsReparto(id)) return false;
		if(getArticoli().contains(a)) return false;
		getReparto(id).get().inserisci(s, a);;
		return true;
	}
	
	public boolean inserisciArticoliScaffale(String id, int s, List<Articolo> l) {
		if (!containsReparto(id)) return false;
		for(Articolo a: l) {
			inserisciArticoloScaffale(id, s, a);
		}
		return true;
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
	
	public List<String> getIdReparti() {
		return magazzino.stream().map(i->i.getID()).collect(Collectors.toList());
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

	public boolean inserisci(Scaffale scaffale, Articolo a) {
		return (inserisciArticoloScaffale(scaffale.getReparto(), scaffale.getNumero(), a));
	}


	
	
}
