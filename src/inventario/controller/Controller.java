package inventario.controller;

import inventario.model.Articolo;
import inventario.model.Magazzino;
import inventario.model.Ordine;
import inventario.model.Scaffale;

public interface Controller {
	Magazzino getMagazzino();
	Ordine getOrdine();
	boolean aggiungiArticolo(Scaffale scaffale, Articolo a);
	void scriviSuFile(String s);
	String stampaMagazzino();
}
