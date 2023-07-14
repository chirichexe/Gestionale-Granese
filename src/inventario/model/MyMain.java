package inventario.model;

import java.io.File;  
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MyMain {

	public static void main(String[] args) throws IOException{
		File file =new File("C:\\Users\\utente\\Documents\\GitHub\\Gestionali\\elettrix_ILUCCHI001C.xlsx");
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
			
			System.out.println(marca + " - " + codice + ": " + quantita);
		}
		
		workbook.close();
		fis.close();
	}
}
