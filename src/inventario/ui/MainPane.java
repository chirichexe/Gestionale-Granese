package inventario.ui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;


import inventario.controller.Controller;
import inventario.model.Articolo;
import inventario.model.Reparto;
import inventario.model.Scaffale;
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
	private TextField nomeNuovo;
	private TextField marcaNuova;
	private TextField scaffale;
	private Button aggiungi;
	private ComboBox<String> repartiBox;
	private ComboBox<String> repartiBox2;
	
	public MainPane(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		List<String> reparti = controller.getMagazzino().get().stream().map(i->i.getID()).collect(Collectors.toList());
		
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
				magazzinoTextArea.setText(controller.stampaMagazzino());
				gestioneMagazzino.getChildren().add(magazzinoTextArea);
				scaffaliTextArea = new TextArea();
				scaffaliTextArea.setPrefSize(250, 120);
				repartiBox = new ComboBox<>(FXCollections.observableArrayList(reparti));
				repartiBox.setOnAction(this::handleScaffale);
				gestioneScaffali.getChildren().addAll(new Label("Ottieni il reparto "), repartiBox);
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
				trovaArticolo.getChildren().add(new Label("Situato nel: "));
				trovaArticolo.getChildren().add(siTrova);
				aggiungiArticolo.getChildren().add(new Label("Aggiungi articolo"));
				repartiBox2 = new ComboBox<>(FXCollections.observableArrayList(reparti));
				nomeNuovo = new TextField();
				marcaNuova = new TextField();
				scaffale = new TextField();
				aggiungi = new Button("Aggiungi");
				aggiungi.setOnAction(this::handleAggiungi);
				aggiungiArticolo.getChildren().addAll(nomeNuovo, marcaNuova, new Label("Nello scaffale:"),scaffale, new Label("Nel reparto:"), repartiBox2, aggiungi);
			}
			gestioneSinistra2.getChildren().addAll(trovaArticolo, aggiungiArticolo);
			sinistra.getChildren().addAll(gestioneSinistra1, gestioneSinistra2);
		}
		this.setLeft(sinistra);
		this.setRight(destra);
	}
	
	private void handleScaffale(ActionEvent e) {
		scaffaliTextArea.setText(controller.getMagazzino().getReparto(repartiBox.getValue()).get().toString());
	}
	
	private void handleAggiungi(ActionEvent e) {
		int sc = 0;
		try {
			sc = Integer.parseInt(scaffale.getText().trim());
		}catch (Exception ex) {
			InventarioGraneseApp.alertError("Errore", "Numero scaffale non valido!", ex.getMessage().toString());
		}
		Articolo daAggiungere = !marcaNuova.getText().isBlank()? 
				new Articolo(nomeNuovo.getText(), marcaNuova.getText()) : 
					new Articolo(nomeNuovo.getText());
		if (controller.aggiungiArticolo(Scaffale.of(sc, repartiBox2.getValue()), daAggiungere) == false) InventarioGraneseApp.alertError("Errore", "Errore aggiunta articolo","Articolo non valido o già presente");
		magazzinoTextArea.setText(controller.stampaMagazzino());
		
		try {
			controller.scriviSuFile("\nscaffale "+ scaffale.getText() + ","+ repartiBox2.getValue() + ": " + nomeNuovo.getText().toUpperCase());
			System.out.println("Scrittura avvenuta con successo!");
		} catch (Exception e1) {
			InventarioGraneseApp.alertError("", "", "ok");
			e1.printStackTrace();
		}
	}
	
	private void cercaEl(ActionEvent e) {
		if(controller.getMagazzino().trovaArticolo(articoloDaCercare.getText()).isEmpty()) InventarioGraneseApp.alertError("Errore", "Articolo non trovato", "Controlla bene il magazzino!");
		else siTrova.setText(controller.getMagazzino().trovaArticolo(articoloDaCercare.getText()).get().toString());
	}
}
