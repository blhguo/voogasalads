package gameplayer;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Brandon Dalla Rosa, Dana Park
 *
 */
public class Menu {
	
	private HBox pane;
	private PulldownFactory pullDownFactory = new PulldownFactory();

	public Menu() {
		pane = new HBox(20);
		pane.setAlignment(Pos.CENTER);
	
		pane.getChildren().add(pullDownFactory.SpeedBox());
		pane.getChildren().add(pullDownFactory.StatusBox());
		pane.getChildren().add(pullDownFactory.SaveLoadBox());



			
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
