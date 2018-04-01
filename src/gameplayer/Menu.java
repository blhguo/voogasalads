package gameplayer;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class Menu {
	private HBox pane;
	private static final String DEFAULT_RESOURCE_PACKAGE = "gamePlayerResources/";
	private ResourceBundle menuProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "menu_options");

	public Menu() {
		pane = new HBox(20);
		pane.setAlignment(Pos.CENTER);
		//Remove this later, just for example
		for(int i=0;i<5;i++) {
				pane.getChildren().add(new Button("button "+i));
		}

			
	}
	/**
	 * Method to add the menu into the VBox for the View Manager
	 * 
	 * @param root
	 */
	public void addMenu(Pane root) {
		root.getChildren().add(pane);
	}

}
