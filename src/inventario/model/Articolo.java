package inventario.model;

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
	
	
	
	
}
