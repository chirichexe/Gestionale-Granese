package inventario.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
	
	public boolean presente(ArticoloOrdinato a) {
		for(ArticoloOrdinato articolo: get()) {
			if (articolo.getCodice().equalsIgnoreCase(a.getCodice())) return true;
		}
		return false;
	}
	
	public Optional<ArticoloOrdinato> getDaCodice(String codice) {
		for(ArticoloOrdinato articolo: get()) {
			if (articolo.getCodice().equalsIgnoreCase(codice)) return Optional.of(articolo);
		}
		return Optional.empty();
	}
	
	public int indice(ArticoloOrdinato a) {
		int i = 0;
		for(ArticoloOrdinato articolo: get()) {
			if (articolo.getCodice().equalsIgnoreCase(a.getCodice())) return i;
			i++;
		}
		return -1;
	}

	
	public void rimuovi(ArticoloOrdinato a) {
		System.out.println(a.toString());
		ArticoloOrdinato presente =	getDaCodice(a.getCodice()).get();
		double differenzaQuantita = Math.abs(presente.getQuantità()-a.getQuantità());
		System.out.println(differenzaQuantita);
		if(differenzaQuantita==0)
			ordineDelGiorno.remove(a);
		else {
			getDaCodice(a.getCodice()).get().aggiungiElementi(-differenzaQuantita);
		}
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
