package authoring.loadingviews;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class LoadPlayerView extends BaseLoadView {

	public LoadPlayerView(Stage stage) {
		super(stage);
	}

	@Override
	public Pane finishScene(GridPane gridpane) {
		addTitle(gridpane, AuthRes.getString("Play"));
		return addCoreFinishingElements(gridpane);
	}

}
