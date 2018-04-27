package authoring.right_components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.prism.paint.Color;

import authoring.controllers.EntityController;
import frontend_utilities.ButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * Event menu that extends BasePane, which implements GUINode. Menu for toggling 
 * relationships between multiple entities
 */
public class EventPane extends BasePane {
	
	
	private EntityController myController;
	
	private Pane start;
	private Pane newEvent;
	private Pane addCondition;
	private Pane addAction;
	private Pane viewEvents;
	private VBox box;
	private VBox subBox;
	
	public EventPane(EntityController e){
		myController = e;
	}
	public EventPane(){
		box = new VBox();
		box = buildBasicView(AuthRes.getString("EventTitle"));
		box.setAlignment(Pos.TOP_CENTER);
		subBox = new VBox();
		box.getChildren().add(subBox);
		initStart();
//		initNewEvent();
//		initAddCondition();
//		initAddAction();
//		initViewEvents();
	}
	
	private void initStart() {
		start = new Pane();
		Button create = ButtonFactory.makeButton(e -> {
			//box.getChildren().remove(subBox);
			subBox.getChildren().clear();
			//subBox.getChildren().add(newEvent);
			//box.getChildren().add(subBox);
		});
		HBox createBox = ButtonFactory.makeHBox("Create New Entity", 
				null, create);
		subBox.getChildren().add(createBox);
		
	}
	public void setController(EntityController e){
		myController = e;
	}
	/**
	 * GUINode method that returns the view of this Pane
	 * @return Pane
	 */
	@Override
	public Pane getView() {
		return box;
	}
	
	
	/**
	 * BasePane method that initializes all the buttons for this right pane
	 */
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