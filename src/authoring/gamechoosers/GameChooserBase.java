package authoring.gamechoosers;

import authoring.GUI_Heirarchy.GUIGridPaneSuper;
import frontend_utilities.ButtonFactory;
import game_player.PlayerMain;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.AuthRes;

/**
 * @author Jennifer Chin
 * Extends GUIGridPaneSuper. This class is a GUIBuilder object because it has its own
 * Scene, and it extends GUIGridPaneSuper because it uses a gridpane as the root of that
 * Scene. 
 */
public class GameChooserBase extends GUIGridPaneSuper {

	private Stage myStage;
	
	/**
	 * Constructor that takes in a Stage in order to change the scene of the stage to 
	 * the GameChooserScreen
	 * @param stage
	 */
	public GameChooserBase(Stage stage){
		//uses stage to switch scene once game is chosen
		myStage = stage;
	}

	/**
	 * Implement abstract method from GUIGridPaneSuper. Finishes adding elements to the
	 * gridpane, such as title and thumbnails.
	 */
	@Override
	public void finishScene(GridPane gridpane) {
		addTitle(gridpane, null);
		addCoreFinishingElements(gridpane);
	}
	
	public void addCoreFinishingElements(GridPane gridpane) {
		double chooserWidth = AuthRes.getInt("EnvironmentX") - (AuthRes.getInt("Margin") * 10);
		double chooserHeight = AuthRes.getInt("EnvironmentY") - (AuthRes.getInt("Margin") * 10);
		VBox vbox = new VBox();
		vbox.setPrefWidth(chooserWidth);
		vbox.setPrefHeight(chooserHeight);
		vbox.getStyleClass().add("chooser-back");
		gridpane.add(vbox, 20, 13);
		testLoad(vbox);
//		gridpane.add(new Toolbar(myStage).getView(), AuthRes.getInt("EnvironmentX"), AuthRes.getInt("EnvironmentY")/10);
	}
	
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
