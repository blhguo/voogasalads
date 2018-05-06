package authoring;

import authoring.GUI_Heirarchy.GUINode;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ImageBuilder;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.SplashScreen;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * Top toolbar class that allows for changes to the view of the JavaFX stage
 */

public class Toolbar implements GUINode {

	private Stage stage;
	private BorderPane myNode;
	
	/**
	 * Constructor that takes in a stage and splash screen in order to change the root 
	 * of the scene of the stage and change it to the splash screen
	 * @param stage
	 * @param ss
	 */
	public Toolbar(Stage stage) {
		this.stage = stage;
		myNode = new BorderPane();
		myNode.setRight(new HBox(
				makeFullScreenButton(), 
				makeBackButton()));
		myNode.getStyleClass().add("toolbar");
	}

	/**
	 * Method inherited from GUINode; returns myNode, the core Toolbar node.
	 * Note the method integrateToolbar(Pane basepane), which allows a user
	 * to place myNode onto a larger pane.
	 */
	@Override
	public Node getView() {
		return myNode;
	}
	
	/**
	 * Integrates toolbar into existing root pane, by stacking panes and enabling clicks through
	 * @param basepane	already-created pane upon which to place the toolbar
	 * @return	StackPane consisting of toolbar and base pane
	 */
	public StackPane integrateToolbar(Pane basepane) {
		myNode.setPickOnBounds(false);
		return new StackPane(basepane, myNode);
	}
	
	/**
	 * Calls ButtonFactory to instantiate a button enabling the stage to enter 
	 * and exit fullscreen
	 */
	private Button makeFullScreenButton() {
		return ButtonFactory.makeButton(null,
				ImageBuilder.resize(new ImageView(new Image(AuthRes.getString("full"))), 
						AuthRes.getInt("ToolbarButton")),
				e -> {
					stage.setFullScreen(!stage.isFullScreen());
				}, "button-nav");
	}
	
	/**
	 * Calls ButtonFactory to instantiate a button enabling the user to move
	 * back to the SplashScreen
	 */
	private Button makeBackButton() {
		return ButtonFactory.makeButton(null, 
				ImageBuilder.resize(new ImageView(new Image(AuthRes.getString("back"))), 
						AuthRes.getInt("ToolbarButton")),
				e -> {
					stage.getScene().setRoot(new SplashScreen(stage).display());
				}, "button-nav");
	}
}