package inventario.ui;

import inventario.controller.Controller;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SezioneDestra  extends VBox {
	
	private Controller controller;
	private Label ordineLabel;
	
	public SezioneDestra(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		this.setSpacing(10);
	}
}
