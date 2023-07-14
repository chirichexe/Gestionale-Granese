package inventario.ui;

import java.util.List;
import java.util.stream.Collectors;

import inventario.controller.Controller;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SezioneDestra  extends VBox {
	
	private Controller controller;
	private Label topLabel;
	private Button scegliFile;
	private TextArea ordineTextArea;
	private TextArea marcaTextArea;
	
	public SezioneDestra(Controller controller) {
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
		{
			
		}
		
		this.getChildren().addAll(gestioneDestra1, gestioneDestra2);
	}
	
	private void caricaOrdine(ActionEvent e) {
		ordineTextArea.setText(controller.getOrdine().toString());
	}
}
