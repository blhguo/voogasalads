package authoring.loadingviews;

import authoring.Toolbar;
import authoring.GUI_Heirarchy.GUIGridPaneSuper;
import frontend_utilities.ButtonFactory;
import game_player.PlayerMain;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.SplashScreen;
import resources.keys.AuthRes;

/**
 * @author Jennifer Chin
 * Extends GUIGridPaneSuper. This class is a GUIBuilder object because it has its own
 * Scene, and it extends GUIGridPaneSuper because it uses a gridpane as the root of that
 * Scene. 
 */
public abstract class BaseLoader extends GUIGridPaneSuper {

	private Stage myStage;
	
	/**
	 * Constructor that takes in a Stage in order to change the scene of the stage to 
	 * the GameChooserScreen
	 * @param stage
	 */
	public BaseLoader(Stage stage){
		//uses stage to switch scene once game is chosen
		myStage = stage;
	}

	/**
	 * Builds the basic view for the GameChooser screen, factoring out all the common elements
	 * between the Load Game for Play view and the Load Game for Edit view
	 * @param gridpane
	 */
	public Pane addCoreFinishingElements(GridPane gridpane) {
		double chooserWidth = AuthRes.getInt("EnvironmentX") - (AuthRes.getInt("Margin") * 10);
		double chooserHeight = AuthRes.getInt("EnvironmentY") - (AuthRes.getInt("Margin") * 10);
		VBox vbox = new VBox();
		vbox.setPrefWidth(chooserWidth);
		vbox.setPrefHeight(chooserHeight);
		vbox.getStyleClass().add("chooser-back");
		gridpane.add(vbox, 20, 13);
		testLoad(vbox);
		return new Toolbar(myStage, new SplashScreen(myStage)).integrateToolbar(gridpane);
	}
	
	/**
	 * Adds a title specific to the type of view (e.g. "Load Game for Edit" vs. "Load Game
	 * for Play"
	 * @param gridpane	current pane set as root in scene
	 * @param type		which specific GameChooser view is needed; determines end of title string
	 */
	public void addTitle(GridPane gridpane, String type) {
		try {
			Text title = new Text(AuthRes.getString("ChooserTitle") + AuthRes.getString(type));
			title.getStyleClass().add("title2");
			gridpane.add(title, 20, 10);
		} catch (NullPointerException e) {
			Alert noType = new Alert(AlertType.ERROR);
			noType.setContentText(AuthRes.getString("NoChooserType"));
			noType.showAndWait();
		}
	}
	
	
	//TEST LOADING
	//class also needs to load saved games to be edited/played - each game needs thumbnail
	/**
	 * Loads a thumbnail for a game. Creates a button out of the thumbnail so that when
	 * pressed, user can play that game
	 * @param vbox
	 */
	//test loader
	public void testLoad(VBox vbox) {
		Text mtncap = new Text("   Mountain ~vIbes~");
		mtncap.getStyleClass().add("game-chooser");
		vbox.getChildren().addAll(
				ButtonFactory.makeButton(null,new ImageView(new Image(AuthRes.getString("mtnthumb"))), 
						e -> new PlayerMain().start(myStage), "button-nav"),
				mtncap);
	}
}
