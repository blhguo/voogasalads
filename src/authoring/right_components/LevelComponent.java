package authoring.right_components;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

public class LevelComponent extends BaseComponent {

	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("LevelTitle"));
		return box;
	}
}
