package inventario.controller;

import java.io.PrintWriter;

import inventario.model.Articolo;
import inventario.model.Magazzino;
import inventario.model.Scaffale;

public class ControllerGranese implements Controller{
	private Magazzino magazzino;
	//private PrintWriter pw;
	
	public ControllerGranese(Magazzino m) {
		this.magazzino = m;
	}
	@Override
	public Magazzino getMagazzino() {
		return magazzino;
	}
	@Override
	public boolean aggiungiArticolo(Scaffale scaffale, Articolo a) {
		return magazzino.inserisci(scaffale, a);
	}
	@Override
	public String stampaMagazzino() {
		return magazzino.toString();
	}
	@Override
	public void scriviSuFile(String s) {
		// TODO Auto-generated method stub
		
	}
	
}
