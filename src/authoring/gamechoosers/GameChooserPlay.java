package authoring.gamechoosers;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class GameChooserPlay extends GameChooserBase {

	public GameChooserPlay(Stage stage) {
		super(stage);
	}

	@Override
	public void finishScene(GridPane gridpane) {
		addTitle(gridpane, AuthRes.getString("Play"));
		addCoreFinishingElements(gridpane);
	}
}
