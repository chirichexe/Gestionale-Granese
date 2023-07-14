package inventario.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import inventario.model.ArticoloOrdinato;
import inventario.model.Ordine;

public class OrdineGraneseReader implements OrdineReader {

	@Override
	public List<ArticoloOrdinato> leggiOrdine(File file) throws IOException, BadFileFormatException {
		if (file==null) throw new IOException("Errore! File nullo");
		
		List<ArticoloOrdinato> ordine =new ArrayList<>();
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0); //inizia da 0
		//dal foglio otteniamo una riga -> dalla riga otteniamo una cella
		int rowCount = sheet.getPhysicalNumberOfRows();
		//System.out.println(rowCount);
		for(int i = 1; i < rowCount; i++) {
			String marca = sheet.getRow(i).getCell(2).getStringCellValue();
			String codice = sheet.getRow(i).getCell(3).getStringCellValue();
			double quantita = sheet.getRow(i).getCell(6).getNumericCellValue();
			ordine.add(new ArticoloOrdinato(codice, marca, quantita));
		}
		workbook.close();
		fis.close();
		return ordine;
	}

}
