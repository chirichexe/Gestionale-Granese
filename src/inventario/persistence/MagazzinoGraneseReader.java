package inventario.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import inventario.model.Articolo;

public class MagazzinoGraneseReader implements MagazzinoReader {

	@Override
	public Map<Integer, List<Articolo>> readRates(Reader reader) throws IOException, BadFileFormatException {
		if (reader==null) throw new IOException("Errore: reader nullo!");
		BufferedReader bf = new BufferedReader(reader);
		String line;
		while((line = bf.readLine())!=null) {
			
		}
		return null;
	}

}
