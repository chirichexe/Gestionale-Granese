package inventario.controller;

import java.io.PrintWriter;

import inventario.model.Articolo;
import inventario.model.Magazzino;
import inventario.model.Scaffale;

public class ControllerGranese implements Controller{
	private Magazzino magazzino;
	private PrintWriter pw;
	
	public ControllerGranese(Magazzino m) {
		this.magazzino = m;
		this.pw = null;
		try {
			pw = new PrintWriter("magazzino.txt");
		}catch(Exception e) {
			
		}
	}
	@Override
	public Magazzino getMagazzino() {
		return magazzino;
	}
	@Override
	public void aggiungiArticolo(Scaffale scaffale, Articolo a) {
		magazzino.inserisci(scaffale, a);
	}
	@Override
	public String stampaMagazzino() {
		return magazzino.toString();
	}
	@Override
	public void scriviSuFile(String s) {
		pw.append(s);
		pw.close();
	}
}
