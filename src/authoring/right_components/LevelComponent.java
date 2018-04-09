package authoring.right_components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import authoring.utilities.ButtonFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

public class LevelComponent extends BaseComponent {

	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("LevelTitle"));
		getButtonArray().stream().map((button) -> box.getChildren().add(button)).collect(Collectors.toList());
		return box;
	}
	
	@Override
	public List<HBox> getButtonArray(){
		ArrayList<HBox> list = new ArrayList<>();
		list.add(ButtonFactory.makeHBox("Select Background", null));
		list.add(ButtonFactory.makeHBox("Add Music", null));
		list.add(ButtonFactory.makeHBox("Build Win Condition", null));
		return list;
	}
}
