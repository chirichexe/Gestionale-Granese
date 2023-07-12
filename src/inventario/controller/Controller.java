package inventario.controller;

import inventario.model.Articolo;
import inventario.model.Magazzino;

public interface Controller {
	Magazzino getMagazzino();
	void aggiungiArticolo(Integer scaffale, Articolo a);
	String stampaMagazzino();
}
