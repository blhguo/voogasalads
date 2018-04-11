package authoring;

import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.SplashScreen;
import resources.keys.AuthRes;

public class Toolbar implements GUINode {

	Stage stage;
	
	public Toolbar(Stage stage) {
		this.stage = stage;
	}
	
	@Override
	public Pane getView() {
		BorderPane toolbar = new BorderPane();
		toolbar.setRight(makeBackButton());
		toolbar.getStyleClass().add("toolbar");
		return toolbar;
	}

	private Button makeBackButton() {
		return ButtonFactory.makeButton(null, 
				ImageBuilder.resize(new ImageView(new Image(AuthRes.getString("back"))), 20),
				e -> stage.setScene(new SplashScreen(stage).display()),
				"button-close");
	}
}


