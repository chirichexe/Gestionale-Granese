package inventario.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
		appendToFile("magazzino.txt", s);
		
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
