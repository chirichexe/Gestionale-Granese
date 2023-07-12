package inventario.model;

import java.util.Objects;

public class Articolo {

	private String codice;
	private String sigla;
	
	public Articolo	(String codice, String sigla) {
		this.codice = codice;
		this.sigla = sigla;
	}
	
	public Articolo	(String codice) {
		this.codice = codice;
		this.sigla = "?";
	}

	@Override
	public String toString() {
		return "	[" + codice + " - " + sigla + "]";
	}

	public String getCodice() {
		return codice;
	}

	public String getSigla() {
		return sigla;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codice, sigla);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo) obj;
		return Objects.equals(codice, other.codice) && Objects.equals(sigla, other.sigla);
	}
	
	
}
