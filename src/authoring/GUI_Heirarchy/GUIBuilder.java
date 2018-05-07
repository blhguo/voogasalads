package authoring.GUI_Heirarchy;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import resources.keys.AuthRes;

/**
 * @author Jennifer Chin
 * @author Elizabeth Shulman
 * Abstract super class to be extended by all classes that initialize scenes or
 * serve as scene roots. This class is intended to provide hierarchy to the front end GUI
 * elements. GUIBuilder views are made up of GUINode objects. Because all GUINode classes
 * implement the same method (.getView() to return the highest-level of that object's view), 
 * it is easy for the GUIBuilder class the use the GUINode classes interchangeably.
 */
public abstract class GUIBuilder {
	
	/**
	 * Abstract method called display. Returns a pane, which is usually set as the root
	 * of the scene (initialized below). Pulls front end visual logic out of the rest of
	 * the class and places it in this method.
	 * @return Pane
	 */
	public abstract Pane display();
	
	/**
	 * Initializes the scene of the GUIBuilder objects. The background and stylesheets
	 * are standardized and set here. In order to change one of these values for the entire 
	 * program, only this method needs to be updated. 
	 * @param pane
	 * @return Scene
	 */
	public Scene initScene(Pane pane){
		Scene scene = new Scene(pane, AuthRes.getInt("EnvironmentX"), AuthRes.getInt("EnvironmentY"));
		scene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());
		setBackground(pane);
		return scene;
	}
	
	/**
	 * Sets the background of the highest-level pane for the view.
	 * @param pane
	 */
	public void setBackground(Pane pane){
		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		pane.setBackground(new Background(back));
	}
}
