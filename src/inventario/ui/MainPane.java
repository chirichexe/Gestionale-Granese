package inventario.ui;

import inventario.controller.Controller;
import javafx.scene.layout.BorderPane;

public class MainPane extends BorderPane{
	Controller controller;
	public MainPane(Controller controller) {
		this.controller = controller;
	}
	
}
