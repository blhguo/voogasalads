package authoring.right_components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import authoring.controllers.PaneController;
import authoring.utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import resources.keys.AuthRes;

public class LevelPane extends BasePane {
	
	private PaneController controller;

	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("LevelTitle"));
		getButtonArray().stream().map((button) -> box.getChildren().add(button)).collect(Collectors.toList());
		return box;
	}
	
	@Override
	public List<Node> getButtonArray(){
		ArrayList<Node> list = new ArrayList<>();
		Button backButton = ButtonFactory.makeButton(event -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose Background Image");
			File file = fc.showOpenDialog(null);
			controller.setBackground(file);
			
		});
		list.add(ButtonFactory.makeHBox("Select Background", null, backButton));
		list.add(ButtonFactory.makeHBox("Add Music", null));
		list.add(ButtonFactory.makeHBox("Build Win Condition", null));
		return list;
	}
	
	public void setController(PaneController pc){
		controller = pc;
	}
}
