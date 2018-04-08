package authoring.right_components;

import authoring.GUIComponent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

public class StoryboardComponent extends BaseComponent {

	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("StoryTitle"));
		return box;
	}
	
}
