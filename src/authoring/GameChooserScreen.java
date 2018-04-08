package authoring;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
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
		
		double chooserWidth = AuthRes.getInt("EnvironmentX") - (AuthRes.getInt("Margin") * 2);
		double chooserHeight = AuthRes.getInt("EnvironmentY") - (AuthRes.getInt("Margin") * 2);
		Rectangle rect = new Rectangle(chooserWidth, chooserHeight);
		rect.getStyleClass().add("chooser-back");
		gridpane.add(rect, 20, 13);
		
	}
	
	//class also needs to load saved games to be edited/played - each game needs thumbnail

}
