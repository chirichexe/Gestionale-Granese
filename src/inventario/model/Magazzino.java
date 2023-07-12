package inventario.model;

import java.util.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Magazzino {
	private Map<Integer, List<Articolo>> magazzino;
	private int dimensione;
	
	public Magazzino(int dim) {
		magazzino = new TreeMap<>();
		//ricevo la dimensione del magazzino e inizializzo una mappa che associa a
		//ogni cella del magazzino una lista vuota
		this.dimensione = dim;
		for(int i = 0; i<dim; i++) {
			magazzino.put(i+1, new ArrayList<Articolo>());
		}
	}
	
	public Magazzino(Map<Integer, List<Articolo>> magazzino, int dim) {
		this.dimensione = dim;
		//permette di creare una lista 
		if (magazzino.keySet().size() > dim) throw new IllegalArgumentException("Magazzino più grande della dimensione dichiarata!");
		this.magazzino = magazzino;
		sortByKeys(magazzino);
	}
	
	public static <K extends Comparable, V> Map<K, V> sortByKeys(Map<K, V> map)
    {
        // crea un elenco di chiavi della mappa e ordinalo
        List<K> keys = new ArrayList(map.keySet());
        Collections.sort(keys);
 
        // crea una `LinkedHashMap` vuota con ordine di inserimento
        Map<K, V> linkedHashMap = new LinkedHashMap<>();
 
        // per ogni chiave nell'elenco ordinato, inserisci il valore-chiave
        // accoppia in `LinkedHashMap`
        for (K key: keys) {
            linkedHashMap.put(key, map.get(key));
        }
 
        return linkedHashMap;
    }

	public int getDimensione() {
		return dimensione;
	}
	
	public Map<Integer, List<Articolo>> get() {
		return magazzino;
	}
	
	public Set<Integer> getScaffali(){
		return magazzino.keySet();
	}
	
	public void inserisci(int r, Articolo a) {
		if(r+1 > dimensione || r < 1 || a==null) throw new IllegalArgumentException("Errore nell'inserimento dell'articolo");
		magazzino.get(r).add(a);
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
		return Objects.hash(magazzino, dimensione);
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
		return Objects.equals(magazzino, other.magazzino) && dimensione == other.dimensione;
	}
	
	
}
