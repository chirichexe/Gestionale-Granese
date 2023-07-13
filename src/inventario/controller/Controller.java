package inventario.controller;

import inventario.model.Articolo;
import inventario.model.Magazzino;
import inventario.model.Scaffale;

public interface Controller {
	Magazzino getMagazzino();
	void aggiungiArticolo(Scaffale scaffale, Articolo a);
	String stampaMagazzino();
}
