package inventario.ui;

import java.util.stream.Collectors;


import inventario.controller.Controller;
import inventario.model.Reparto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainPane extends BorderPane{
	private Controller controller;
	private TextArea magazzinoTextArea;
	private TextArea scaffaliTextArea;
	private TextField articoloDaCercare;
	private TextField siTrova;
	private Button cerca;
	private ComboBox<Reparto> repartiBox;
	
	public MainPane(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		VBox destra = new VBox();
		VBox sinistra = new VBox();{
			sinistra.getChildren().add(new Label("Magazzino"));
			sinistra.setSpacing(10);
			HBox gestioneSinistra1 = new HBox();
			gestioneSinistra1.setSpacing(20);
			//gestione sinistra 1
			VBox gestioneMagazzino = new VBox();
			VBox gestioneScaffali = new VBox();
			{
				magazzinoTextArea = new TextArea();
				magazzinoTextArea.setText(controller.getMagazzino().toString());
				gestioneMagazzino.getChildren().add(magazzinoTextArea);
				scaffaliTextArea = new TextArea();
				scaffaliTextArea.setPrefSize(250, 120);
				repartiBox = new ComboBox<>(FXCollections.observableArrayList(controller.getMagazzino().get()));
				repartiBox.setOnAction(this::handleScaffale);
				gestioneScaffali.getChildren().addAll(new Label("Ottieni lo scaffale "), repartiBox);
				gestioneScaffali.getChildren().add(scaffaliTextArea);
			}
			gestioneSinistra1.getChildren().addAll(gestioneMagazzino, gestioneScaffali);
			//gestione sinistra 2
			HBox gestioneSinistra2 = new HBox();
			VBox trovaArticolo = new VBox();
			VBox aggiungiArticolo = new VBox();
			{
				trovaArticolo.getChildren().add(new Label("Trova Articolo"));
				articoloDaCercare = new TextField();
				siTrova = new TextField();
				siTrova.setEditable(false);
				trovaArticolo.getChildren().add(articoloDaCercare);
				cerca = new Button("Cerca");
				cerca.setOnAction(this::cercaEl);
				trovaArticolo.getChildren().add(cerca);
				trovaArticolo.getChildren().add(new Label("Situato nello: "));
				trovaArticolo.getChildren().add(siTrova);
				aggiungiArticolo.getChildren().add(new Label("Aggiungi articolo"));
			}
			gestioneSinistra2.getChildren().addAll(trovaArticolo, aggiungiArticolo);
			sinistra.getChildren().addAll(gestioneSinistra1, gestioneSinistra2);
		}
		this.setLeft(sinistra);
		this.setRight(destra);
	}
	
	private void handleScaffale(ActionEvent e) {
		//scaffaliTextArea.setText(controller.getMagazzino().getScaffale(repartiBox.getValue()).stream().map(i->i.toString()).collect(Collectors.joining("\n")));
	}
	
	private void cercaEl(ActionEvent e) {
		//if(controller.getMagazzino().posizioneArticolo(articoloDaCercare.getText())==0) InventarioGraneseApp.alertError("Errore", "Articolo non trovato", "Controlla bene il magazzino!");
		//else siTrova.setText("Scaffale " + controller.getMagazzino().posizioneArticolo(articoloDaCercare.getText()));
	}
}
