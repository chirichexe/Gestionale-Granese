package inventario.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Ordine {
	private List<ArticoloOrdinato> ordineDelGiorno;
	
	public Ordine() {
		this.ordineDelGiorno = new ArrayList<ArticoloOrdinato>();
	}
	
	public void riempi(List<ArticoloOrdinato> lista) {
		for(ArticoloOrdinato art: lista) {
			aggiungi(art);
		}
	}
	
	public List<ArticoloOrdinato> get(){
		return ordineDelGiorno;
	}
	
	public void aggiungi(ArticoloOrdinato a) {
		if (!presente(a)) {
			ordineDelGiorno.add(a);
		} else {
			System.out.println(a.toString());
			ordineDelGiorno.get(indice(a)).aggiungiElementi(a.getQuantità());

		}
	}
	
	private boolean presente(ArticoloOrdinato a) {
		for(ArticoloOrdinato articolo: get()) {
			if (articolo.getCodice().equalsIgnoreCase(a.getCodice())) return true;
		}
		return false;
	}
	
	private int indice(ArticoloOrdinato a) {
		int i = 0;
		for(ArticoloOrdinato articolo: get()) {
			if (articolo.getCodice().equalsIgnoreCase(a.getCodice())) return i;
			i++;
		}
		return -1;
	}

	
	public void rimuovi(ArticoloOrdinato a) {
		ordineDelGiorno.remove(a);
	}
	
	public boolean contains(String articolo) {
		return ordineDelGiorno.stream().map(i->i.getCodice().equalsIgnoreCase(articolo)).count()>0;
	}
	
	public boolean contains(ArticoloOrdinato articolo) {
		return ordineDelGiorno.contains(articolo);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Ordine del "+ 
				LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.ITALY))
				+ "\n");
		ordineDelGiorno.forEach(i->sb.append(i.toString().concat("\n")));
		return sb.toString();
	}
	
}
