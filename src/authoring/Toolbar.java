package authoring;

import frontend_utilities.ButtonFactory;
import frontend_utilities.ImageBuilder;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.SplashScreen;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * @author Jennifer Chin
 * Top toolbar class that contains a back button to allow the user to get back to the 
 * splash screen 
 */

public class Toolbar extends BorderPane {

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
		
		this.setRight(makeBackButton());
		this.getStyleClass().add("toolbar");
	}

	private Button makeBackButton() {
		return ButtonFactory.makeButton(null, 
				ImageBuilder.resize(new ImageView(new Image(AuthRes.getString("back"))), 25),
				e -> stage.getScene().setRoot(splash.display()),
				"button-nav");
	}

	/**
	 * Integrates toolbar into existing root pane, by stacking panes and enabling clicks through
	 * @param basepane	already-created pane upon which to place the toolbar
	 * @return	StackPane consisting of toolbar and base pane
	 */
	public StackPane integrateToolbar(Pane basepane) {
		this.setPickOnBounds(false);
		return new StackPane(basepane, this);

	}

}


