package inventario.ui;

import javax.naming.InitialContext;

import inventario.controller.Controller;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MainPane extends BorderPane{
	Controller controller;
	TextArea magazzinoTextArea;
	TextArea scaffaliTextArea;
	TextField articoloDaCercare;
	
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
			//gestione sinistra 1
			VBox gestioneMagazzino = new VBox();
			VBox gestioneScaffali = new VBox();
			{
				magazzinoTextArea = new TextArea();
				gestioneMagazzino.getChildren().add(magazzinoTextArea);
				scaffaliTextArea = new TextArea();
				gestioneScaffali.getChildren().add(scaffaliTextArea);
			}
			gestioneSinistra1.getChildren().addAll(gestioneMagazzino, gestioneScaffali);
			//gestione sinistra 2
			HBox gestioneSinistra2 = new HBox();
			{
				VBox trovaArticolo = new VBox();
				
				VBox aggiungiArticolo = new VBox();
			}
			sinistra.getChildren().addAll(gestioneSinistra1, gestioneSinistra2);
		}
		this.setLeft(sinistra);
		this.setRight(destra);
	}
}
