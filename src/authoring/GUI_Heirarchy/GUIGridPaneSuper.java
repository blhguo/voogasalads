package authoring.GUI_Heirarchy;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import resources.keys.AuthRes;

/**
 * @author Jennifer Chin
 * GridPane Super class. This class is extended by any GUIBuilder objects using a 
 * gridpane. 
 */
public abstract class GUIGridPaneSuper extends GUIBuilder {
	
	/**
	 * Implements the GUIBuilder abstract display method for a gridpane. Initializes 
	 * all gridpanes with the same padding.
	 * @return Pane
	 */
	@Override
	public Pane display() {
		GridPane gridpane = new GridPane();
		gridpane.setHgap(AuthRes.getInt("Padding"));
		gridpane.setVgap(AuthRes.getInt("Padding"));
		gridpane.setPadding(new Insets(AuthRes.getInt("Padding")));
		
		setBackground(gridpane);
		return finishScene(gridpane);
	}
	
	/**
	 * Abstract method that allows for customizing the gridpane and adding different
	 * objects to the scene. Called by display. Adds objects to the gridpane initialized
	 * by display. 
	 * @param gridpane
	 */
	public abstract Pane finishScene(GridPane gridpane);

}
