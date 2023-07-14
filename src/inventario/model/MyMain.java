package inventario.model;

import java.io.File;  
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class MyMain {

	public static void main(String[] args) throws IOException{

		ArticoloOrdinato a1 = new ArticoloOrdinato("aedgf", "efwq", 5);
		ArticoloOrdinato a2 = new ArticoloOrdinato("aedgf", "efwq", 5);
		Ordine o = new Ordine();
		o.aggiungi(a1);
		o.aggiungi(a2);
		System.out.println(o.toString());
	}
	
	
	
}
