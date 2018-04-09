package authoring.right_components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import authoring.utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

public class EventComponent extends BaseComponent {
	
	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("EventTitle"));
		getButtonArray().stream().map((button) -> box.getChildren().add(button)).collect(Collectors.toList());
		box.getChildren().addAll(newSeparator(), RelationshipView());
		return box;
	}
	
	@Override 
	public List<Node> getButtonArray(){
		List<Node> list = new ArrayList<>();
		list.add(ButtonFactory.makeHBox("Add Relationship", null));
		return list;
	}
	
	private TitledPane RelationshipView() {
		TitledPane tp = new TitledPane();
		tp.setExpanded(false);
		tp.setText("View Current Relationships");
		tp.setContent(new ListView<String>());
		tp.getStyleClass().add("titled-pane > .title");
		return tp;
	}
}