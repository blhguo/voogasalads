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
	private SplashScreen splash;
	
	public Toolbar(Stage stage, SplashScreen ss) {
		this.stage = stage;
		splash = ss;
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
				ImageBuilder.resize(new ImageView(new Image(AuthRes.getString("back"))), 25),
				e -> stage.getScene().setRoot(splash.display()),
				"button-nav");
	}
}


