package authoring;

import authoring.GUI_Heirarchy.GUINode;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ImageBuilder;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.SplashScreen;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * @author Jennifer Chin
 * Top toolbar class that contains a back button to allow the user to get back to the 
 * splash screen 
 */

public class Toolbar implements GUINode {

	Stage stage;
	private SplashScreen splash;
	
	/**
	 * Constructor that takes in a stage and splash screen in order to change the root 
	 * of the scene of the stage and change it to the splash screen
	 * @param stage
	 * @param ss
	 */
	public Toolbar(Stage stage, SplashScreen ss) {
		this.stage = stage;
		splash = ss;
	}
	
	/**
	 * Method from GUINode interface. Implements GUINode because it is a sub-part of a 
	 * larger scene
	 */
	@Override
	public Pane getView() {
		BorderPane toolbar = new BorderPane();
		toolbar.setRight(makeBackButton());
		toolbar.getStyleClass().add("toolbar");
		return toolbar;
	}

	private Button makeBackButton() {
		return ButtonFactory.makeButton(null, 
				ImageBuilder.resize(new ImageView(new Image(AuthRes.getString("play"))), 25),
				e -> stage.getScene().setRoot(splash.display()),
				"button-nav");
	}
}


