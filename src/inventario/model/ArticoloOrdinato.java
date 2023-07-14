package inventario.model;

import java.util.Objects;

public class ArticoloOrdinato extends Articolo{
	
	private double quantita;
	
	public ArticoloOrdinato(String codice, String sigla, double quantita) {
		super(codice, sigla);
		this.quantita = quantita;
	}
	
	public double getQuantità() {
		return quantita;
	}
	
	public void aggiungiElementi(double daAggiungere) {
		this.quantita = quantita + daAggiungere;
	}
	
	@Override
	public String toString() {
		return "[" + super.getCodice() + " - " + super.getSigla() + ":	" + quantita + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(quantita);
		return result;
	}
	
	public boolean stessoArticolo(ArticoloOrdinato a) {
		return (a.getCodice().equalsIgnoreCase(getCodice()));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticoloOrdinato other = (ArticoloOrdinato) obj;
		return quantita == other.quantita;
	}
	
	
	
}
