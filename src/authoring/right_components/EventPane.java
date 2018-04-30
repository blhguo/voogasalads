package authoring.right_components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring.AddActionPane;
import authoring.AddConditionPane;
import authoring.component_menus.KeyMenuElement;
import authoring.component_menus.MenuElement;
import authoring.component_menus.StringMenuElement;
import authoring.controllers.EntityController;
import authoring.controllers.LevelController;
import authoring.right_components.EntityComponent.EntityWrapper;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ComboBoxBuilder;
import frontend_utilities.ImageBuilder;
import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.NullComponent;
import game_engine.event.Action;
import game_engine.event.Condition;
import game_engine.event.ConditionFactory;
import game_engine.event.Event;
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
	private ResourceBundle components;
	private VBox comboBoxView;
	private List<Event> eventList;
	private Event currentEvent;
	private LevelController levelController;
	private AddActionPane addActionPane;
	private AddConditionPane addConditionPane;

	public EventPane(EntityController e){
		this();
		myController = e;
	}
	public EventPane(){
		eventList = new ArrayList<>();
		components = ResourceBundle.getBundle("Component");
		bundle = ResourceBundle.getBundle("resources.keys/Conditions");
		box = new VBox();
		box = buildBasicView(AuthRes.getString("EventTitle"));
		//box.setAlignment(Pos.TOP_CENTER);
		box.setSpacing(20); 
		subBox = new VBox();
		subBox.setSpacing(20);
		box.getChildren().add(subBox);
		comboBoxView = new VBox();
		comboBoxView.setSpacing(20);
		comboBoxView.setFillWidth(true);
		comboBoxView.setPrefWidth(150);
		initStart();
		initNewEvent();
		initAddCondition();
		initAddAction();
		initViewEvents();
		
	}
	public void setLevelController(LevelController controller){
		levelController = controller;
		addConditionPane.setLevelController(controller);
		addActionPane.setLevelController(controller);
	}

	private void initAddAction() {
		addActionPane = new AddActionPane(currentEvent);
		addAction = addActionPane.getView();
		Button back = ButtonFactory.makeButton(e -> {
			clearAndAdd(newEvent);
		});
		addActionPane.add(ButtonFactory.makeHBox("Back", null, back));

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
		addConditionPane = new AddConditionPane(currentEvent, levelController);
		addCondition = addConditionPane.getView();

		Button back = ButtonFactory.makeButton(e -> {
			clearAndAdd(newEvent);
		});
		addConditionPane.add(ButtonFactory.makeHBox("Back", null, back));
	}


	public void addToEntityBox(EntityWrapper wrapper){
		addConditionPane.addToEntityBox(wrapper);
		addActionPane.addToEntityBox(wrapper);
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

		Button addEvent = ButtonFactory.makeButton(e -> {
			levelController.addEvent(currentEvent);
			currentEvent = new Event();
		});
		startBox.getChildren().add(ButtonFactory.makeHBox("Add this event to the level",
				null,
				addEvent));
		
	}
	private void initNewEvent() {
		currentEvent = new Event();
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
	

}