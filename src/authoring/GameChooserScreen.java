package authoring;

import authoring.utilities.ButtonFactory;
import game_player.PlayerMain;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class GameChooserScreen extends GUIGridPaneSuper {

	private Stage myStage;
	
	public GameChooserScreen(Stage stage){
		//uses stage to switch scene once game is chosen
		myStage = stage;
	}

	@Override
	public void finishScene(GridPane gridpane) {
		Text title = new Text(AuthRes.getString("ChooserTitle"));
		title.getStyleClass().add("title2");
		gridpane.add(title, 20, 10);
		
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
	
	//class also needs to load saved games to be edited/played - each game needs thumbnail

	
	//test loader
	public void testLoad(VBox vbox) {
		vbox.getChildren().add(ButtonFactory.makeButton(null,new ImageView(new Image(AuthRes.getString("sunset"))), e -> new PlayerMain().start(myStage)));
	}
}
