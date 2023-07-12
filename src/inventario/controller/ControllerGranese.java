package inventario.controller;

import inventario.model.Articolo;
import inventario.model.Magazzino;

public class ControllerGranese implements Controller{
	Magazzino magazzino;
	
	public ControllerGranese(Magazzino m) {
		this.magazzino = m;
	}
	@Override
	public Magazzino getMagazzino() {
		return magazzino;
	}
	@Override
	public void aggiungiArticolo(Integer scaffale, Articolo a) {
		magazzino.inserisci(scaffale, a);
	}
	@Override
	public String stampaMagazzino() {
		return magazzino.toString();
	}
}
