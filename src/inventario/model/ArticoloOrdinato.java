package inventario.model;

public class ArticoloOrdinato extends Articolo{
	
	private int quantita;
	
	public ArticoloOrdinato(String codice, String sigla, int quantita) {
		super(codice, sigla);
		this.quantita = quantita;
	}
	
	public int getQuantità() {
		return quantita;
	}
	
}
