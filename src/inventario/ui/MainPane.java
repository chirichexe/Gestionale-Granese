package inventario.ui;

import inventario.controller.Controller;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPane extends BorderPane{
	
	private Stage stage;
	
	private Controller controller;
	
	public MainPane(Controller controller, Stage stage) {
		this.controller = controller;
		this.stage = stage;
		init();
	}
	
	private void init() {
		this.setRight(new SezioneDestra(controller));
		this.setCenter(new SezioneSinistra(controller));
	}
	

}
