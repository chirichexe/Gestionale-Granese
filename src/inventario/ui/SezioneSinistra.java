package inventario.ui;

import java.util.List;
import java.util.stream.Collectors;

import inventario.controller.Controller;
import inventario.model.Articolo;
import inventario.model.Scaffale;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SezioneSinistra extends VBox {
	
	private Controller controller;
	private Label topLabel;
	private VBox gestioneScaffali;
	private VBox gestioneMagazzino;
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
	private ComboBox<Scaffale> scaffaliBox;
	
	public SezioneSinistra(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		//PRECONFIGURAZIONE
		this.setSpacing(10);
		this.setPadding(new Insets(10, 10, 0, 10));
		this.setBorder(Border.stroke(javafx.scene.paint.Paint.valueOf("Lightgrey")));
		List<String> reparti = controller.getMagazzino().get().stream().map(i->i.getID()).collect(Collectors.toList());
		//TESTO IN ALTO
		topLabel = new Label("Magazzino");
		topLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
		this.getChildren().add(topLabel);
		
		//MAGAZZINO E REPARTI
		HBox gestioneSinistra1 = new HBox(20);
			//1) magazzino
			gestioneMagazzino = new VBox(10);
			{
			magazzinoTextArea = new TextArea();
			magazzinoTextArea.setPrefSize(250, 300);
			magazzinoTextArea.setText(controller.stampaMagazzino());
			gestioneMagazzino.getChildren().add(magazzinoTextArea);
			}
			//2) reparti
			gestioneScaffali = new VBox(10);
			{
			scaffaliTextArea = new TextArea();
			scaffaliTextArea.setPrefWidth(250);
			repartiBox = new ComboBox<>(FXCollections.observableArrayList(reparti));
			repartiBox.setOnAction(this::handleScaffale);
			scaffaliBox = new ComboBox<>();
			gestioneScaffali.getChildren().addAll(new Label("Ottieni il reparto "), repartiBox, scaffaliTextArea);
			}
		
		gestioneSinistra1.getChildren().addAll(gestioneMagazzino, gestioneScaffali);
		
		//RICERCA E AGGIUNTA ARTICOLI	
		HBox gestioneSinistra2 = new HBox(20);
			//1) trova articolo
			VBox trovaArticolo = new VBox(5);
			{
				articoloDaCercare = new TextField();
				siTrova = new TextField();
				siTrova.setEditable(false);
				cerca = new Button("Cerca");
				cerca.setOnAction(this::cercaEl);
				trovaArticolo.getChildren().addAll(new Label("Trova Articolo"),articoloDaCercare, cerca, new Label("Situato nel: "), siTrova);
			}
			//2) aggiungi articolo
			VBox aggiungiArticolo = new VBox();
			{
				repartiBox2 = new ComboBox<>(FXCollections.observableArrayList(reparti));
				nomeNuovo = new TextField();
				marcaNuova = new TextField();
				scaffale = new TextField();
				aggiungi = new Button("Aggiungi");
				aggiungi.setOnAction(this::handleAggiungi);
				aggiungiArticolo.getChildren().addAll(new Label("Aggiungi articolo"),nomeNuovo, marcaNuova, new Label("Nello scaffale:"),scaffale, new Label("Nel reparto:"), repartiBox2, aggiungi);
			}
		gestioneSinistra2.getChildren().addAll(trovaArticolo, aggiungiArticolo);
		
		//AGGIUNTA TOTALE
		this.getChildren().addAll(gestioneSinistra1, gestioneSinistra2);

	}
	
	private void handleScaffale(ActionEvent e) {
		scaffaliTextArea.setText(controller.getMagazzino().getReparto(repartiBox.getValue()).get().toString());
		gestioneScaffali.getChildren().remove(scaffaliBox);
		scaffaliBox = new ComboBox<>(FXCollections.observableArrayList(controller.getMagazzino().getReparto(repartiBox.getValue()).get().getScaffali()));
		scaffaliBox.setOnAction(this::sceltaScaffale);
		gestioneScaffali.getChildren().add(scaffaliBox);
	}
	
	private void sceltaScaffale(ActionEvent e) {
		scaffaliTextArea.setText(scaffaliBox.getValue().toString()+"\n\n");
	controller.getMagazzino().getReparto(repartiBox.getValue()).get().
				getArticoliScaffale(scaffaliBox.getValue())
				.forEach(i->scaffaliTextArea.appendText(i.toString().concat("\n")));
	}
	
	private void handleAggiungi(ActionEvent e) {
		int scaffaleInt = 0;
		try {
			scaffaleInt = Integer.parseInt(scaffale.getText().trim());
		}catch (Exception ex) {
			InventarioGraneseApp.alertError("Errore", "Numero scaffale non valido!", ex.getMessage().toString());
		}
		Articolo daAggiungere = !marcaNuova.getText().isBlank()? 
				new Articolo(nomeNuovo.getText(), marcaNuova.getText()) : 
					new Articolo(nomeNuovo.getText());
		if (controller.aggiungiArticolo(Scaffale.of(scaffaleInt, repartiBox2.getValue()), daAggiungere) == false) InventarioGraneseApp.alertError("Errore", "Errore aggiunta articolo","Articolo non valido o già presente");
		else {
			magazzinoTextArea.setText(controller.stampaMagazzino());
			try {
				controller.scriviSuFile("\nscaffale "+ scaffale.getText() + ","+ repartiBox2.getValue() + ": " + nomeNuovo.getText().toUpperCase() + "," + marcaNuova.getText().toUpperCase());
				System.out.println("Scrittura avvenuta con successo!");
			} catch (Exception e1) {
				InventarioGraneseApp.alertError("", "", "ok");
				e1.printStackTrace();
			}
		}
	}
	
	private void cercaEl(ActionEvent e) {
		if(controller.getMagazzino().trovaArticolo(articoloDaCercare.getText()).isEmpty()) InventarioGraneseApp.alertError("Errore", "Articolo non trovato", "Controlla bene il magazzino!");
		else siTrova.setText(controller.getMagazzino().trovaArticolo(articoloDaCercare.getText()).get().toString());
	}
}
