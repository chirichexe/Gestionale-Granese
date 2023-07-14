package inventario.ui;

import java.awt.Paint;
import java.io.BufferedWriter;
import java.io.File;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainPane extends BorderPane{
	
	private Stage stage;
	
	private Controller controller;
	private VBox gestioneScaffali;
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
	
	private Button scegliFile;
	private FileChooser chooser;
	private File selectedFile;
	private TextField txt1, txt2;
	
	public MainPane(Controller controller, Stage stage) {
		this.controller = controller;
		this.stage = stage;
		init();
	}
	
	private void init() {
		List<String> reparti = controller.getMagazzino().get().stream().map(i->i.getID()).collect(Collectors.toList());
		
		
		this.setRight(new SezioneDestra(controller));
		this.setLeft(new SezioneSinistra(controller));
	}
	

}
