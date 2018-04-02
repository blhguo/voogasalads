package gameplayer;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
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
	
	private ResourceBundle speedProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "speed");
	private ResourceBundle statusProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "status");
	private ResourceBundle saveLoadProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "save_load");
	private ComboBox<String> speedBox;
	private ComboBox<String> statusBox;
	private ComboBox<String> saveLoadBox;
	private PulldownFactory pullDownFactory = new PulldownFactory();

	public Menu() {
		pane = new HBox(20);
		pane.setAlignment(Pos.CENTER);
	
		pane.getChildren().add(pullDownFactory.SpeedBox(speedBox , speedProperties));
		pane.getChildren().add(pullDownFactory.StatusBox(statusBox , statusProperties));
		pane.getChildren().add(pullDownFactory.SaveLoadBox(saveLoadBox , saveLoadProperties));



			
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
