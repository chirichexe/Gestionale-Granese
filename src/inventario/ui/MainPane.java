package inventario.ui;

import inventario.controller.Controller;
import inventario.controller.ControllerGranese;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPane extends BorderPane{
	
	private ControllerGranese controller;
	
	public MainPane(ControllerGranese controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		SezioneDestra destra = new SezioneDestra(controller);
		SezioneSinistra sinistra = new SezioneSinistra(controller);
		
		this.setCenter(destra);
		this.setLeft(sinistra);
	}
	

}
