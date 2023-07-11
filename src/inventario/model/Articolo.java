package inventario.model;

public class Articolo {
	private String codice;
	private String sigla;
	private int quantità;
	
	public Articolo	(String codice, String sigla, int quantità) {
		this.codice = codice;
		this.sigla = sigla;
		this.quantità = quantità;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
	
}
