package authoring.gamechoosers;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class GameChooserEdit extends GameChooserBase {

	public GameChooserEdit(Stage stage) {
		super(stage);
	}

	@Override
	public void finishScene(GridPane gridpane) {
		addTitle(gridpane, AuthRes.getString("Edit"));
		addCoreFinishingElements(gridpane);
	}
}
