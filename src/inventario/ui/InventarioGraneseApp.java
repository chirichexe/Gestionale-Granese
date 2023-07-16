package inventario.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.apache.poi.xdgf.usermodel.shape.exceptions.StopVisitingThisBranch;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import inventario.controller.Controller;
import inventario.controller.ControllerGranese;
import inventario.model.Articolo;
import inventario.model.Magazzino;
import inventario.model.Reparto;
import inventario.persistence.BadFileFormatException;
import inventario.persistence.MagazzinoGraneseReader;
import inventario.persistence.OrdineGraneseReader;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InventarioGraneseApp extends Application {
	
	private static ControllerGranese c;
	private ControllerGranese controller;
	private Stage stage;
	private static TextField scegliScaffale;
	private static ComboBox<String> scegliReparto;
	private TextField txt1, txt2;
	private FileChooser chooser;
	private File selectedFile;
	
	public static void alertError(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert= new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public static void alertInfo(String title, String headerMessage, String contentMessage, Articolo a) {
		javafx.scene.control.Alert alert= new Alert(AlertType.INFORMATION);
		scegliScaffale = new TextField();
		scegliReparto = new ComboBox<>(FXCollections.observableArrayList(c.getMagazzino().getIdReparti()));
		HBox interfacciaAggiunta = new HBox();
		interfacciaAggiunta.getChildren().addAll(scegliReparto, scegliScaffale);
		alert.setTitle(title);
		alert.setContentText(contentMessage);
		alert.setHeaderText(headerMessage);
		alert.setGraphic(interfacciaAggiunta);
		alert.setOnCloseRequest(e -> c.aggiungiArticolo(scegliScaffale.getText(), scegliReparto.getValue(), a));
		alert.showAndWait();
	}
	

	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		stage.setTitle("Magazzino Granese");
		try {
			//1. LETTURA MAGAZZINO DA FILE TESTO
			
			MagazzinoGraneseReader reader = new MagazzinoGraneseReader();
			Magazzino magazzino = reader.leggiMagazzino(new FileReader("magazzino.txt"));
			controller = new ControllerGranese(magazzino);
			c = controller;
			
			//2. LETTURA MAGAZZINO DA FILE EXCEL
			
			//IMPOSTAZIONE SCENA
			MainPane mainPanel = new MainPane(controller, stage);
			Scene scene = new Scene(mainPanel, 1100, 720, Color.WHITE);
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
