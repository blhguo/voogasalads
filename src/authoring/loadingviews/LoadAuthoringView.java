package authoring.loadingviews;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class LoadAuthoringView extends BaseLoadView {

	public LoadAuthoringView(Stage stage) {
		super(stage);
	}

	@Override
	public Pane finishScene(GridPane gridpane) {
		addTitle(gridpane, AuthRes.getString("Edit"));
		return addCoreFinishingElements(gridpane);
	}
	
}
