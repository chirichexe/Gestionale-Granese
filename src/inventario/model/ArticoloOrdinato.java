package inventario.model;

import java.util.Objects;

public class ArticoloOrdinato extends Articolo{
	
	private int quantita;
	
	public ArticoloOrdinato(String codice, String sigla, int quantita) {
		super(codice, sigla);
		this.quantita = quantita;
	}
	
	public int getQuantità() {
		return quantita;
	}
	
	@Override
	public String toString() {
		return "	[" + super.getCodice() + " - " + super.getSigla() + ": " + quantita + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(quantita);
		return result;
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
