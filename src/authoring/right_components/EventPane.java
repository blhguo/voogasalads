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
import frontend_utilities.ImageBuilder;
import frontend_utilities.UserFeedback;
import game_engine.event.Action;
import game_engine.event.Condition;
import game_engine.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		currentEvent = new Event();
		initStart();

		initAddCondition();
		initAddAction();
		initNewEvent();
		initViewEvents();
		
	}
	public void setLevelController(LevelController controller){
		levelController = controller;
		addConditionPane.setLevelController(controller);
		addActionPane.setLevelController(controller);
	}

	private void initAddAction() {
		addActionPane = new AddActionPane(currentEvent, stage, this);
		addAction = addActionPane.getView();
		ImageView iv = ImageBuilder.resize(new ImageView(new Image("back.png")), 20);
		Button buttonBox = ButtonFactory.makeIconButton("Back", iv, e -> {
			clearAndAdd(newEvent);
		});
	}

	private void initViewEvents() {
		viewEvents = new Pane();
		VBox events = new VBox();
		events.setSpacing(20);

		VBox box = new VBox();
		box.getChildren().addAll(getPrettyList(eventList));
		ScrollPane pane = new ScrollPane(box);
		events.getChildren().add(pane);
		System.out.println("Events + " + events.getChildren());
		ImageView iv = ImageBuilder.resize(new ImageView(new Image("back.png")), 20);
		Button buttonBox = ButtonFactory.makeIconButton("Back", iv, e -> {
			initStart();
		});
		events.getChildren().add(buttonBox);

		Button button2 = ButtonFactory.makeButton(e -> {
			levelController.getEngine().getLevel().removeLastEvent();
			eventList.remove(eventList.size() - 1);
			initViewEvents();
		});
		events.getChildren().add(button2);
		viewEvents.getChildren().add(events);
	}
	private VBox getPrettyList(List<Event> list){
		VBox labelList = new VBox();
		for (Event element : list){
			VBox box = new VBox(AuthRes.getInt("Padding"));
			box.setPrefWidth(300);
			Label conditionLabel = new Label("Conditions:");
			conditionLabel.getStyleClass().add("event-label");
			box.getChildren().add(conditionLabel);

			for (Condition condition : element.getConditions()){
				Label label = new Label(condition.toString());
				label.getStyleClass().add("event-label2");
				box.getChildren().add(label);
			}

			Label actionLabel = new Label("Actions:");
			actionLabel.getStyleClass().add("event-label");
			box.getChildren().add(actionLabel);

			for (Action action : element.getActions()){
				Label label = new Label(action.toString());
				label.getStyleClass().add("event-label2");
				box.getChildren().add(label);
			}
			labelList.getChildren().add(box);
		}
		return labelList;
	}
	private void initAddCondition() {
		addConditionPane = new AddConditionPane(currentEvent, levelController, stage, this);
		addCondition = (Pane) addConditionPane.getView();		
		ImageView iv = ImageBuilder.resize(new ImageView(new Image("back.png")), 20);
		Button buttonBox = ButtonFactory.makeIconButton("Back", iv, e -> {
			clearAndAdd(newEvent);
		});
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
			System.out.println("Current Event: " + currentEvent);
			System.out.println("Current Event, actions size: " + currentEvent.getActions().size());
			System.out.println("Current Event, conditions size: " + currentEvent.getConditions().size());
			levelController.addEvent(currentEvent);
			eventList.add(currentEvent);
			eventList.stream().forEach(b -> System.out.println(b));
			initViewEvents();
			currentEvent = new Event();
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
		addConditionPane.setLevelController(levelController);
		addActionPane.setLevelController(levelController);
		eventBox.getChildren().add(addCondition);
		addCondition.setOnMousePressed(e -> {
			addConditionPane.setStyle("-fx-border-width: 2px; -fx-border-color: lightblue");
			addConditionPane.setSelected(true);
			addActionPane.setSelected(false);
			System.out.println("Hit add condition");
		});
		eventBox.getChildren().add(addAction);
		addAction.setOnMousePressed(e -> {
			addActionPane.setStyle("-fx-border-width: 2px; -fx-border-color: lightblue");
			addActionPane.setSelected(true);
			addConditionPane.setSelected(false);
			System.out.println("Hit add action");
		});
	
		ImageView iv = ImageBuilder.resize(new ImageView(new Image("back.png")), 20);
		Button backBox = ButtonFactory.makeIconButton("Back", iv, e -> {
			initStart();
		});
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


	public Event getCurrentEvent() {
		return currentEvent;
	}
}