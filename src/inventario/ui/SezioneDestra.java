package inventario.ui;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import inventario.controller.Controller;
import inventario.controller.ControllerGranese;
import inventario.model.Articolo;
import inventario.model.ArticoloOrdinato;
import inventario.model.Scaffale;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SezioneDestra  extends VBox {
	
	private ControllerGranese controller;
	private Label topLabel;
	private Button scegliFile;
	private TextArea ordineTextArea;
	private TextArea marcaTextArea;
	private TextField codiceField;
	private TextField quantita;
	private Button trovaButton;
	private TextArea esito;
	private TextArea resocontoArea;
	private Button stampaSuFile;
	private ArticoloOrdinato cercato;
	
	public SezioneDestra(ControllerGranese controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		this.setSpacing(10);
		this.setBorder(Border.stroke(javafx.scene.paint.Paint.valueOf("Lightgrey")));
		this.setPadding(new Insets(10, 10, 0, 10));
		//TESTO IN ALTO
		topLabel = new Label("Ordine");
		topLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
		this.getChildren().add(topLabel);
		
		//ORDINE E FILTRAGGIO PER MARCA
		HBox gestioneDestra1 = new HBox(20);
			//1) scelta file ordine
			VBox sceltaFile = new VBox(5);
			{
			scegliFile = new Button("Scegli file");
			scegliFile.setOnAction(this::caricaOrdine);
			ordineTextArea = new TextArea();
			ordineTextArea.setPrefSize(250, 400);
			sceltaFile.getChildren().addAll(scegliFile, ordineTextArea);
			}
			//2) filtra ordine
			VBox filtraOrdine = new VBox(5);
			{
			marcaTextArea = new TextArea();
			marcaTextArea.setPrefSize(250, 300);
			filtraOrdine.getChildren().addAll(marcaTextArea);
			}
		
		gestioneDestra1.getChildren().addAll(sceltaFile, filtraOrdine);
		//RICERCA ORDINI
		HBox gestioneDestra2 = new HBox(20);
			//CERCA ARTICOLO
			VBox ricercaArticolo = new VBox(5);
			{
			codiceField = new TextField();
			quantita = new TextField();
			trovaButton = new Button("Cerca Articolo nell'ordine");
			trovaButton.setOnAction(this::trovaArticoloOrdine);
			esito = new TextArea();
			esito.setPrefSize(200, 100);
			ricercaArticolo.getChildren().addAll(codiceField, quantita, trovaButton, esito);
			}
			//SCRITTURA RESOCONTO
			VBox resoconto = new VBox(5);
			{
			resocontoArea = new TextArea();
			resocontoArea.setPrefWidth(200);
			stampaSuFile = new Button("Stampa resoconto");
		
			resoconto.getChildren().addAll(resocontoArea, stampaSuFile);
			}
		gestioneDestra2.getChildren().addAll(ricercaArticolo, resoconto);
		
		this.getChildren().addAll(gestioneDestra1, gestioneDestra2);
	}
	
	public void aggiornaPannelliDastra() {
		
	}
	
	private void caricaOrdine(ActionEvent e) {
		ordineTextArea.setText(controller.stampaOrdine());
	}
	
	private void trovaArticoloOrdine(ActionEvent e){
		double quantitaCercata = -1;
		try {
			quantitaCercata = Double.parseDouble(quantita.getText());
		}
		catch(Exception ex ) {
			InventarioGraneseApp.alertError("Errore", "Errore conversione intero", ex.getMessage());
		}
		if (controller.getOrdine().presente(new ArticoloOrdinato(codiceField.getText(), "",	quantitaCercata))) {
			cercato = controller.getOrdine().getDaCodice(codiceField.getText()).get();
			esito.setText("Articolo [" + cercato.getCodice()  + "] Presente\n" + 
						  "Quantità " + (cercato.getQuantità() == quantitaCercata ? 
								  "giusta:\nTrovati ": "sbagliata:\nTrovati " ) + quantitaCercata + " su " + cercato.getQuantità() );
			
			trovaArticoloNelMagazzino(cercato);
			controller.getOrdine().rimuovi(cercato); //da filtrare la rimozione
			ordineTextArea.setText(controller.stampaOrdine());
		}else {
			InventarioGraneseApp.alertError("Attenzione!", "Articolo " + codiceField.getText() +" non trovato!", "Ricontrolla bene o inseriscilo nel resoconto");
		}
	}
	
	private void trovaArticoloNelMagazzino(ArticoloOrdinato a) {
		Optional<Scaffale> el = controller.posizioneArticoloMagazzino(a);
		if(!el.isEmpty()) esito.appendText("\n\nDa inserire nel\n" + el.get().toString());
		else {
			System.out.println(cercato.getCodice());
			InventarioGraneseApp.alertInfo("Attenzione", "Aggiungi", "L'articolo inserito non è presente nel magazzino", new Articolo( cercato.getCodice(), cercato.getSigla() ));
			
		}
	}

}
