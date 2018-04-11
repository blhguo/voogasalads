package authoring.right_components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import authoring.controllers.LevelController;
import authoring.controllers.PaneController;
import authoring.utilities.ButtonFactory;
import game_engine.Level;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import resources.keys.AuthRes;

public class LevelPane extends BasePane {
	
	private PaneController controller;
	private LevelController lcontroller;

	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("LevelTitle"));
		box.getChildren().addAll(getButtonArray());
		return box;
	}

	@Override
	public List<Node> getButtonArray() {
		ArrayList<Node> list = new ArrayList<>();
		Button backButton = ButtonFactory.makeButton(event -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose Background Image");
			File file = fc.showOpenDialog(null);
			controller.setBackground(file);
		});
		list.add(ButtonFactory.makeHBox("Select Background", null, backButton));
		return list;

	}
	
	public void setController(PaneController pc){
		controller = pc;
	}
	
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}
	
	//eventually we will need to have some button similar to entity
	//pane where when clicked it initializes a new level
}
