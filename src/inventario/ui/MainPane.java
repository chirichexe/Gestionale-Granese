package inventario.ui;

import java.io.File;
import java.io.IOException;

import inventario.controller.Controller;
import inventario.controller.ControllerGranese;
import inventario.persistence.BadFileFormatException;
import inventario.persistence.OrdineGraneseReader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainPane extends BorderPane{
	
	private ControllerGranese controller;
	private Stage stage;
	private Button scegliFile;
	
	public MainPane(ControllerGranese controller, Stage s) {
		this.controller = controller;
		this.stage = s;
		init();
	}
	
	private void init() {
		SezioneDestra destra = new SezioneDestra(controller, stage);
		SezioneSinistra sinistra = new SezioneSinistra(controller);
		this.setCenter(destra);
		this.setLeft(sinistra);
		
	}

}
