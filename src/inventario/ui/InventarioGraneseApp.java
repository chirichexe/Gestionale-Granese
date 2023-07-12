package inventario.ui;

import java.io.FileReader;
import java.io.IOException;

import inventario.controller.Controller;
import inventario.controller.ControllerGranese;
import inventario.model.Magazzino;
import inventario.persistence.MagazzinoGraneseReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
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
	
	@Override
	public void start(Stage stage) throws Exception { 
		stage.setTitle("Magazzino Granese");
		try {
				MagazzinoGraneseReader reader = new MagazzinoGraneseReader();
				Magazzino magazzino = reader.leggiMagazzino(new FileReader("magazzino.txt"));
				Controller controller = new ControllerGranese(magazzino);
				
				MainPane mainPanel = new MainPane(controller);
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
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
