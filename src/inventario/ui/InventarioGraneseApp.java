package inventario.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import inventario.controller.Controller;
import inventario.controller.ControllerGranese;
import inventario.model.Magazzino;
import inventario.persistence.BadFileFormatException;
import inventario.persistence.MagazzinoGraneseReader;
import inventario.persistence.OrdineGraneseReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InventarioGraneseApp extends Application {
	
	public static void alertError(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert= new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public static void alertInfo(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert= new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	private TextField txt1, txt2;
	private FileChooser chooser;
	private File selectedFile;
	
	@Override
	public void start(Stage stage) throws Exception { 
		stage.setTitle("Magazzino Granese");
		try {
			//1. LETTURA MAGAZZINO DA FILE TESTO
			
			MagazzinoGraneseReader reader = new MagazzinoGraneseReader();
			Magazzino magazzino = reader.leggiMagazzino(new FileReader("magazzino.txt"));
			Controller controller = new ControllerGranese(magazzino);
			//2. LETTURA MAGAZZINO DA FILE EXCEL
			OrdineGraneseReader readerOrdine = new OrdineGraneseReader();
	
	File file =new File("C:\\Users\\utente\\Documents\\GitHub\\Gestionali\\elettrix_ILUCCHI001C.xlsx"); //DA FAR INTERAGIRE CON FILEPICKER
			
			controller.riempiOrdine(readerOrdine.leggiOrdine(file));
			//IMPOSTAZIONE SCENA
			MainPane mainPanel = new MainPane(controller, stage);
			Scene scene = new Scene(mainPanel, 1000, 700, Color.WHITE);
			stage.setScene(scene);
			stage.show();
				
		} catch (IOException e) {
			alertError(
					"ERRORE DI I/O",
					"Errore di lettura: impossibile leggere i dati",
					"Dettagli: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			alertError(
					"ERRORE DI I/O",
					"Formato dei file errato: impossibile leggere i dati",
					"Dettagli: " + e.getMessage());
		} catch (BadFileFormatException e) {
			alertError(
					"ERRORE LETTURA FILE",
					"Errore di lettura: uno dei file ha avuto fallimenti nella lettura",
					"Dettagli: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
