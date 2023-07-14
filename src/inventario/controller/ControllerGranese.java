package inventario.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import inventario.model.Articolo;
import inventario.model.ArticoloOrdinato;
import inventario.model.Magazzino;
import inventario.model.Ordine;
import inventario.model.Scaffale;

public class ControllerGranese implements Controller{
	private Magazzino magazzino;
	private Ordine ordine;
	//private PrintWriter pw;
	
	public ControllerGranese(Magazzino m) {
		this.magazzino = m;
		ordine = new Ordine();
	}
	
	public ControllerGranese(Magazzino m, Ordine o) {
		this.magazzino = m;
		this.ordine = o;
	}
	@Override
	public Magazzino getMagazzino() {
		return magazzino;
	}
	
	@Override
	public Ordine getOrdine() {
		return ordine;
	}
	@Override
	public boolean aggiungiArticolo(Scaffale scaffale, Articolo a) {
		return magazzino.inserisci(scaffale, a);
	}
	
	public void riempiOrdine(List<ArticoloOrdinato> ordineAttuale) {
		ordine.riempi(ordineAttuale); //da cambiare in boolean, qualcosa potrebbe andare storto
	}
	
	@Override
	public String stampaMagazzino() {
		return magazzino.toString();
	}
	
	@Override
	public String stampaOrdine() {
		return ordine.toString();
	}
	
	@Override
	public void scriviSuFile(String s) {
		appendToFile("magazzino.txt", s);
	}
	
	public Optional<Scaffale> posizioneArticoloMagazzino(ArticoloOrdinato a) {
		return magazzino.trovaArticolo(a.getCodice());
	}
	
	private void appendToFile(String filePath, String text)
	{
	    PrintWriter fileWriter = null;

	    try
	    {
	        fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(
	                filePath, true)));

	        fileWriter.println(text);
	    } catch (IOException ioException)
	    {
	        ioException.printStackTrace();
	    } finally
	    {
	        if (fileWriter != null)
	        {
	            fileWriter.close();
	        }
	    }
	}

	
}
