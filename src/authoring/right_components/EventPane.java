package authoring.right_components;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring.controllers.EntityController;
import frontend_utilities.ButtonFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * @author Liam Pulsifer
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
	private ResourceBundle bundle;
	private VBox comboBoxView;
	public EventPane(EntityController e){
		this();
		myController = e;
	}
	public EventPane(){
		bundle = ResourceBundle.getBundle("resources.keys/Conditions");
		box = new VBox();
		box = buildBasicView(AuthRes.getString("EventTitle"));
		box.setAlignment(Pos.TOP_CENTER);
		subBox = new VBox();
		subBox.setSpacing(20);
		box.getChildren().add(subBox);
		comboBoxView = new VBox();
		initStart();
		initNewEvent();
		initAddCondition();
//		initAddAction();
		initViewEvents();
		
	}
	private void initViewEvents() {
		viewEvents = new Pane();
		VBox events = new VBox();
		events.setSpacing(20);
		events.getChildren().add(new ImageView(
				new Image("default.jpg")));
		Button button = ButtonFactory.makeButton(e ->{
			initStart();
		});
		HBox buttonBox = ButtonFactory.makeHBox("Back", null, button);
		events.getChildren().add(buttonBox);
		viewEvents.getChildren().add(events);
	}
	private void initAddCondition() {
		addCondition = new Pane();
		VBox conditionBox = new VBox();
		Label addComp = new Label("New Condition");
		conditionBox.getChildren().add(addComp);
		ComboBox<String> box = getComboBox();
		box.valueProperty().addListener((observable, oldValue, newValue) -> {
			updateComboBoxView(newValue);
		});
		comboBoxView.getChildren().add(new ImageView(new Image("default.jpg")));
		conditionBox.getChildren().add(box);
		conditionBox.getChildren().add(comboBoxView);
		addCondition.getChildren().add(conditionBox);
	}

	private void updateComboBoxView(String newValue) {
		comboBoxView.getChildren().clear();
		System.out.println(newValue);
		System.out.println(bundle.getString(newValue));
		String[] array = bundle.getString(newValue).split(",");
		for (int i = 0; i < Integer.parseInt(array[0]); i++){
			comboBoxView.getChildren().add(new Rectangle(20, 20, Color.RED));
		}
		for (int i = 0; i < Integer.parseInt(array[1]); i++){
			comboBoxView.getChildren().add(new Circle());
		}
		for (int i = 0; i < Integer.parseInt(array[2]); i++){
			comboBoxView.getChildren().add(new TextField());
		}
	}

	private ComboBox<String> getComboBox() {
		ComboBox<String> box = new ComboBox<>();
		box.setItems(FXCollections.observableArrayList(
				bundle.keySet().stream().collect(Collectors.toList())
		));
		return box;
	}

	private void clearAndAdd(Node n){
		subBox.getChildren().clear();
		subBox.getChildren().add(n);
	}
	private void initStart() {
		start = new Pane();
		VBox startBox = new VBox();
		startBox.setSpacing(20);
		Button create = ButtonFactory.makeButton(e -> {
			clearAndAdd(newEvent);
		});
		HBox createBox = ButtonFactory.makeHBox("Create New Event", 
				null, create);
		Button view = ButtonFactory.makeButton(a -> {
			clearAndAdd(viewEvents);
		});
		HBox viewBox = ButtonFactory.makeHBox("View Existing Events", null,
				view);
		startBox.getChildren().addAll(createBox, viewBox);
		start.getChildren().add(startBox);
		clearAndAdd(start);
		
	}
	private void initNewEvent() {
		newEvent = new Pane();
		VBox eventBox = new VBox();
		eventBox.setSpacing(20);
		Button condition = ButtonFactory.makeButton(e -> {
			clearAndAdd(addCondition);
		});
		Button action = ButtonFactory.makeButton(f -> {
			clearAndAdd(addAction);
		});
		HBox conditionBox = ButtonFactory.makeHBox("Add a new Condition", 
				null, condition);
		HBox actionBox = ButtonFactory.makeHBox("Add a new Action", 
				null, action);
		eventBox.getChildren().addAll(conditionBox, actionBox);
		Button back = ButtonFactory.makeButton(g -> {
			initStart();
		});
		HBox backBox = ButtonFactory.makeHBox("Back", null, back);
		eventBox.getChildren().add(backBox);
		newEvent.getChildren().add(eventBox);
		
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