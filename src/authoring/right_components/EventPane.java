package authoring.right_components;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import authoring.AddActionPane;
import authoring.AddConditionPane;
import authoring.controllers.EntityController;
import authoring.controllers.LevelController;
import authoring.right_components.EntityComponent.EntityWrapper;
import frontend_utilities.ButtonFactory;
import frontend_utilities.UserFeedback;
import game_engine.event.Action;
import game_engine.event.Condition;
import game_engine.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * @author Liam Pulsifer
 * Event menu that extends BasePane, which implements GUINode. Menu for toggling 
 * relationships between multiple entities
 */
public class EventPane extends BasePane {
	
	
	private EntityController myController;
	
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
	private Stage stage;

	public EventPane(EntityController e, Stage s){
		this(s);
		myController = e;
	}
	public EventPane(Stage stage){
		this.stage = stage;
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
		addActionPane = new AddActionPane(currentEvent, stage);
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
		events.getChildren().addAll(getPrettyList(eventList));
		System.out.println("Events + " + events.getChildren());
		Button button = ButtonFactory.makeButton(e ->{
			initStart();
		});
		HBox buttonBox = ButtonFactory.makeHBox("Back", null, button);
		events.getChildren().add(buttonBox);
		viewEvents.getChildren().add(events);
	}
	private VBox getPrettyList(List<Event> list){
		VBox labelList = new VBox();
		//box.setSpacing(10);
		for (Event element : list){
			VBox box = new VBox();
			box.setStyle("-fx-border-width: 2px; -fx-border-color: blue");
			Label eventLabel = new Label("Event:");
			eventLabel.setStyle("-fx-background-color: blue;");
			box.getChildren().add(eventLabel);

			Label conditionLabel = new Label("Conditions");
			conditionLabel.setStyle("-fx-background-color: lightblue");
			box.getChildren().add(conditionLabel);

			for (Condition condition : element.getConditions()){
				Label label = new Label(condition.toString());
				box.getChildren().add(label);
			}

			Label actionLabel = new Label("Actions:");
			actionLabel.setStyle("-fx-background-color: lightblue;");
			box.getChildren().add(actionLabel);

			for (Action action : element.getActions()){
				Label label = new Label(action.toString());
				box.getChildren().add(label);
			}
			labelList.getChildren().add(box);
		}
		return labelList;
	}
	private void initAddCondition() {
		addConditionPane = new AddConditionPane(currentEvent, levelController, stage);
		addCondition = (Pane) addConditionPane.getView();

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
		Pane start = new Pane();
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
			eventList.add(currentEvent);
			currentEvent = new Event();
			initViewEvents();
			Alert a = UserFeedback.getInfoMessage(AuthRes.getString("AddEventHeader"), AuthRes.getString("AddEventContent"), stage);
			a.showAndWait();
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
		//eventBox.getChildren().addAll(conditionBox, actionBox);
		initAddAction();
		initAddCondition();
		eventBox.getChildren().add(addCondition);
		addCondition.setOnMousePressed(e -> {
			addCondition.setStyle("-fx-border-width: 2px; -fx-border-color: lightblue");
			addConditionPane.setSelected(true);
			addActionPane.setSelected(false);
			System.out.println("Hit add condition");
		});
		eventBox.getChildren().add(addAction);
		addAction.setOnMousePressed(e -> {
			addAction.setStyle("-fx-border-width: 2px; -fx-border-color: lightblue");
			addActionPane.setSelected(true);
			addConditionPane.setSelected(false);
			System.out.println("Hit add action");
		});
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