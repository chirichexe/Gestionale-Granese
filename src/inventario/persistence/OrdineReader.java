package inventario.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;

import inventario.model.ArticoloOrdinato;

public interface OrdineReader {
	List<ArticoloOrdinato> leggiOrdine(File file) throws IOException, BadFileFormatException;
}
