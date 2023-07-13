package inventario.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import inventario.model.Articolo;
import inventario.model.Magazzino;

public class MagazzinoGraneseReader implements MagazzinoReader {
	
	@Override
	public Magazzino leggiMagazzino(Reader reader)
			throws IOException, BadFileFormatException {
		if(reader == null) throw new IOException("Errore: Reader nullo!");
		Magazzino magazzino = new Magazzino();
		BufferedReader bf = new BufferedReader(reader);
		String line = bf.readLine();
		String[] primaLinea = line.split(":");
		String[] repartiStrings = primaLinea[1].split(",");
		try {
			for(String s: repartiStrings) {
				magazzino.aggiungiReparto(s.trim());
			}
		}catch(Exception e) {
			throw new BadFileFormatException("Errore lettura stringa reparto! " + e.getMessage());
		}
		while((line = bf.readLine())!=null) {
			if(line.isBlank()) continue;
			String[] items	= line.split(":");
			if (!items[0].toLowerCase().contains("scaffale")) throw new BadFileFormatException("La prima riga deve essere uno scaffale! ");
			items[0] = items[0].replace("scaffale", "");
			//Lettura primo campo: scaffale 1, A (esempio)
			String[] primoCampo = items[0].split(",");
			int scaffale; //
			String reparto = primoCampo[1].trim(); //
			try {
				scaffale = Integer.parseInt(primoCampo[0].trim());
			}catch(Exception e) {
				throw new BadFileFormatException("Errore lettura linea! " + line + "\n"+ e.getMessage());
			}
			System.out.println(reparto);
			if(!items[1].contains(";")) magazzino.inserisciArticoloScaffale(reparto, scaffale, leggiUno(items[1]));
			else magazzino.inserisciArticoliScaffale(reparto, scaffale, leggiElenco(items[1]));
		}
		return magazzino;
	}
	
	private Articolo leggiUno(String line) throws BadFileFormatException {
		String[] items = line.split(",");
		if(items.length!=2 && items.length!=1) throw new BadFileFormatException("Errore! Linea troppo corta/Lunga: " + line);
		if(items.length==2) return new Articolo(items[0].trim().toUpperCase(), items[1].trim().toUpperCase());
		else return new Articolo(items[0].trim().toUpperCase());
	}
	
	private List<Articolo> leggiElenco(String line) throws BadFileFormatException {
		String[] items = line.split(";");
		List<Articolo> list = new ArrayList<>();
		for(String s: items) {
			list.add(leggiUno(s));
		}
		return list;
	}


}
