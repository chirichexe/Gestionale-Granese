package inventario.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import inventario.controller.Controller;
import inventario.controller.ControllerGranese;
import inventario.model.Articolo;
import inventario.model.ArticoloOrdinato;
import inventario.model.Scaffale;
import inventario.persistence.BadFileFormatException;
import inventario.persistence.OrdineGraneseReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SezioneDestra  extends VBox {
	
	private Stage stage;
	private ControllerGranese controller;
	private Label topLabel;
	private Button mostraOrdine;
	private TextArea ordineTextArea;
	private TextArea marcaTextArea;
	private ObservableList<PieChart.Data> dati;
	private PieChart pieChartArticoli;
	private TextField codiceField;
	private TextField quantita;
	private Button trovaButton;
	private TextArea esito;
	private TextArea resocontoArea;
	private Button stampaSuFile;
	private ArticoloOrdinato cercato;

	
	public SezioneDestra(ControllerGranese controller, Stage stage) {
		this.controller = controller;
		this.stage = stage;
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
			mostraOrdine = new Button("Scegli File");
			mostraOrdine.setOnAction(arg0 -> {
				try {
					scegli(arg0);
				} catch (IOException e) {
					InventarioGraneseApp.alertError("Attenzione!", "Scelta file non valida!", "Clicca ok per chiudere");
				} catch (BadFileFormatException e) {
					InventarioGraneseApp.alertError("Attenzione!", "Errore nella formattazione del file!", "Controllare tramite Excel");
				}
			});
			ordineTextArea = new TextArea();
			ordineTextArea.setPrefSize(250, 400);
			sceltaFile.getChildren().addAll(mostraOrdine, ordineTextArea);
			}
			//2) filtra ordine
			VBox filtraOrdine = new VBox(5);
			{
			pieChartArticoli = new PieChart();
			//pieChartArticoli.setPrefSize(250, 500);
			filtraOrdine.getChildren().addAll(pieChartArticoli);
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
			stampaSuFile = new Button("Stampa resoconto");
			stampaSuFile.setOnAction(e->{
				try {
					controller.creaEScrivi(resocontoArea.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			resoconto.getChildren().addAll(resocontoArea, stampaSuFile);
			}
		gestioneDestra2.getChildren().addAll(ricercaArticolo, resoconto);
		
		this.getChildren().addAll(gestioneDestra1, gestioneDestra2);
	}

	
	private void caricaOrdine(ActionEvent e) {
		ordineTextArea.setText(controller.stampaOrdine());
	}
	
	
	private void scegli(ActionEvent e) throws IOException, BadFileFormatException {
		FileChooser fc = new FileChooser();
		OrdineGraneseReader readerOrdine = new OrdineGraneseReader();
		File file = fc.showOpenDialog(stage);
		if (file == null) controller.riempiOrdine(new ArrayList<>());
		else{controller.riempiOrdine(readerOrdine.leggiOrdine(file));
			caricaOrdine(e);
			dati = FXCollections.observableArrayList(controller.getOrdine().get().stream()
				.map(a->new PieChart.Data(a.getCodice(), a.getQuantità()))
				.collect(Collectors.toList()));
			pieChartArticoli.setData(dati);
		}
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
			controller.getOrdine().rimuovi(new ArticoloOrdinato(cercato.getCodice(), cercato.getSigla(), quantitaCercata)); //da filtrare la rimozione
			ordineTextArea.setText(controller.stampaOrdine());
		}else {
			InventarioGraneseApp.alertError("Attenzione!", "Articolo " + codiceField.getText() +" non trovato!", "Ricontrolla bene o inseriscilo nel resoconto");
		}
	}
	
	private void trovaArticoloNelMagazzino(ArticoloOrdinato a) {
		Optional<Scaffale> el = controller.posizioneArticoloMagazzino(a);
		if(!el.isEmpty()) esito.appendText("\n\nDa inserire nel\n" + el.get().toString());
		else {
			InventarioGraneseApp.alertInfo("Attenzione", "Aggiungi", "L'articolo inserito non è presente nel magazzino", new Articolo( cercato.getCodice(), cercato.getSigla() ));
			
		}
	}

}
