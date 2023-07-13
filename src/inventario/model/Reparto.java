package inventario.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Reparto {
	private Map<Scaffale, List<Articolo>> reparto;
	private char id;
	
	public Reparto(char id) {
		reparto = new TreeMap<>();
		this.id = id;
	}
	
	public Reparto(Map<Scaffale, List<Articolo>> reparto, char id) {
		this.id = id;
		this.reparto = reparto;
	}
	


	public int getID() {
		return id;
	}
	
	public Map<Scaffale, List<Articolo>> get() {
		return reparto;
	}
	
	public Set<Scaffale> getScaffali(){
		return reparto.keySet();
	}
	
	public void inserisci(int r, Articolo a) {
		if(r < 1 || a==null) throw new IllegalArgumentException("Errore nell'inserimento dell'articolo");
		if (reparto.get(Scaffale.of(r, id)) == null) reparto.put(Scaffale.of(r, id), new ArrayList<Articolo>()); //istanza il tipo
		reparto.get(Scaffale.of(r, id)).add(a);
	}
	
	public void inserisci(int r, List<Articolo> l) {
		for(Articolo a: l) {
			inserisci(r, a);
		}
	}
	
	public int trovaArticoloReparto(String a) {
		for(Scaffale i: reparto.keySet()) {
			for(int j=0;j<reparto.get(i).size();j++)
				if (reparto.get(i).get(j).getCodice().equalsIgnoreCase(a.trim())) return i.getNumero();
		}
		return 0;
	}
	
	public List<Articolo> getScaffale(int i){
		return reparto.get(Scaffale.of(i, id));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Reparto " + id);
		for(Scaffale s: reparto.keySet()) {
			sb.append("\n- Scaffale "+ s.getNumero() + "\n	" + 
			getScaffale( s.getNumero() ).stream().map(i->i.toString()).collect(Collectors.joining("\n	") ) );
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, reparto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reparto other = (Reparto) obj;
		return id == other.id && Objects.equals(reparto, other.reparto);
	}


}
