package inventario.controller;

import java.io.IOException;
import java.util.List;

import inventario.model.Articolo;
import inventario.model.ArticoloOrdinato;
import inventario.model.Magazzino;
import inventario.model.Ordine;
import inventario.model.Scaffale;

public interface Controller {
	Magazzino getMagazzino();
	Ordine getOrdine();
	boolean aggiungiArticolo(Scaffale scaffale, Articolo a);
	void riempiOrdine(List<ArticoloOrdinato> ordineAttuale);
	void scriviSuFile(String s);
	void creaEScrivi(String s) throws IOException;
	String stampaMagazzino();
	String stampaOrdine();
}
