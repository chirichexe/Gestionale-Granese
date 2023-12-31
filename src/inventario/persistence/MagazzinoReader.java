package inventario.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import inventario.model.*;

public interface MagazzinoReader {
	Magazzino leggiMagazzino(Reader reader) throws IOException, BadFileFormatException;
}
