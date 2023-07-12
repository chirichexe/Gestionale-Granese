package inventario.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import inventario.model.Articolo;
import inventario.model.Magazzino;
import javafx.scene.shape.Line;

public class MagazzinoGraneseReader implements MagazzinoReader {
	
	@Override
	public Magazzino leggiMagazzino(Reader reader)
			throws IOException, BadFileFormatException {
		if(reader == null) throw new IOException("Errore: Reader nullo!");
		BufferedReader bf = new BufferedReader(reader);
		String line = bf.readLine();
		String[] primaLinea = line.split(":");
		int dimensione = 0;
		try {
			dimensione = Integer.parseInt(primaLinea[1].trim());
		}catch(Exception e) {
			throw new BadFileFormatException("Errore lettura dimensione!");
		}
		Magazzino magazzino = new Magazzino(dimensione);
		while((line = bf.readLine())!=null) {
			if(line.isBlank()) continue;
			String[] items	= line.split(":");
			if (!items[0].toLowerCase().contains("scaffale")) throw new BadFileFormatException("La prima riga deve essere uno scaffale! ");
			items[0] = items[0].toLowerCase().replace("scaffale", "");
			int scaffale;
			try {
				scaffale = Integer.parseInt(items[0].trim());
			}catch(Exception e) {
				throw new BadFileFormatException("Errore lettura linea! " + line + "\n"+ e.getMessage());
			}
			if(!items[1].contains(";")) magazzino.inserisci(scaffale, leggiUno(items[1]));
			else magazzino.inserisci(scaffale, leggiElenco(items[1]));
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
