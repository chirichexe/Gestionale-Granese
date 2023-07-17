package inventario.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import inventario.model.Articolo;
import inventario.model.ArticoloOrdinato;
import inventario.model.Magazzino;
import inventario.model.Ordine;
import inventario.model.Scaffale;
import inventario.persistence.BadFileFormatException;
import inventario.persistence.OrdineGraneseReader;
import inventario.ui.InventarioGraneseApp;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerGranese implements Controller{
	private Magazzino magazzino;
	private Ordine ordine;
	
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
		try {
			scriviSuFile("\nscaffale "+ scaffale.getNumero() + ","+ scaffale.getReparto() + ": " + a.getCodice().toUpperCase() + "," + a.getSigla().toUpperCase());
			System.out.println("Scrittura avvenuta con successo!");
		} catch (Exception e1) {
			InventarioGraneseApp.alertError("Errore", "Errore scrittura su file!", "Premi ok per chiudere");
			e1.printStackTrace();
		}
		return magazzino.inserisci(scaffale, a);
	}
	
	public boolean aggiungiArticolo(String scaffale, String reparto,  Articolo a) {
		int posizione = 0;
		try {
			posizione = Integer.parseInt(scaffale);
		}catch(Exception e){
		}
		return aggiungiArticolo(Scaffale.of(posizione, reparto), a);
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

	@Override
	public void creaEScrivi(String s) throws IOException {
		String nomeString = "resoconto_bolletta_" + LocalDate.now().toString();
		System.out.println(nomeString+".txt");
		File myFile = new File(nomeString+".txt");
		FileWriter fWriter = new FileWriter(myFile);
		fWriter.append("Resoconto" + LocalDate.now().toString() + "\n\n" + s);
		fWriter.close();
	}

	
}
