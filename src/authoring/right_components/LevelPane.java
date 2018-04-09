package authoring.right_components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import authoring.utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import resources.keys.AuthRes;

public class LevelPane extends BasePane {

	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("LevelTitle"));
		getButtonArray().stream().map((button) -> box.getChildren().add(button));
		return box;
	}

	@Override
	public List<Node> getButtonArray() {
		ArrayList<Node> list = new ArrayList<>();
		Button backButton = ButtonFactory.makeButton(event -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose Background Image");
			File file = fc.showOpenDialog(null);

		});
		list.add(ButtonFactory.makeHBox("Select Background", null, backButton));
		return list;

	}
}
