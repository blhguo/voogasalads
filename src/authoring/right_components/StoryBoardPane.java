package authoring.right_components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import authoring.utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

public class StoryBoardPane extends BasePane {

	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("StoryTitle"));
		box.getChildren().addAll(LevelOrderer(), newSeparator());
		getButtonArray().stream().map((button) -> box.getChildren().add(button)).collect(Collectors.toList());
		return box;
	}

	@Override 
	public List<Node> getButtonArray(){
		ArrayList<Node> list = new ArrayList<>();
		list.add(ButtonFactory.makeHBox("Change Game Name", null));
		return list;
	}
	
	private TitledPane LevelOrderer() {
		TitledPane tp = new TitledPane();
		tp.setExpanded(false);
		tp.setText("Reorder Current Levels");
		tp.setContent(new ListView<String>());
		tp.getStyleClass().add("titled-pane > .title");
		return tp;
	}
	
	
	
}
