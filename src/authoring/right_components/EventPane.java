package authoring.right_components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring.controllers.EntityController;
import authoring.right_components.EntityComponent.EntityWrapper;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ImageBuilder;
import game_engine.Entity;
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
	private HBox entityBox;
	private int numEntities;
	private Entity[] entityArray;

	public EventPane(EntityController e){
		this();
		myController = e;
	}
	public EventPane(){
		numEntities = 0;
		components = ResourceBundle.getBundle("Component");
		bundle = ResourceBundle.getBundle("resources.keys/Conditions");
		box = new VBox();
		entityBox = new HBox();
		box = buildBasicView(AuthRes.getString("EventTitle"));
		box.setAlignment(Pos.TOP_CENTER);
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
		ComboBox<String> box = getComboBox(bundle.keySet().stream().filter(e -> !e.contains("Strings")).collect(Collectors.toList()));
		box.valueProperty().addListener((observable, oldValue, newValue) -> {
			updateComboBoxView(newValue);
		});
		//comboBoxView.getChildren().add(new ImageView(new Image("default.jpg")));
		conditionBox.getChildren().add(box);
		conditionBox.getChildren().add(comboBoxView);
		addCondition.getChildren().add(conditionBox);
	}

	private void updateComboBoxView(String newValue) {
		comboBoxView.getChildren().clear();
		entityBox.getChildren().clear();

		System.out.println(newValue);
		System.out.println(bundle.getString(newValue));
		String[] array = bundle.getString(newValue).split(",");
		numEntities = Integer.parseInt(array[0]);
		entityArray = new Entity[numEntities];
		entityBox = new HBox();
		entityBox.setSpacing(25);
		for (int i = 0; i < numEntities; i++){
			entityBox.getChildren().add(new Label("Entity " + (i + 1)));
			Rectangle rect = new Rectangle(50, 50, Color.BLACK);
			Tooltip tip = new Tooltip("Click an entity to add to this Event");
			Tooltip.install(rect, tip);
			entityBox.getChildren().add(rect);
			entityArray[i] = null;
		}
		comboBoxView.getChildren().add(entityBox);
		for (int i = 0; i < Integer.parseInt(array[1]); i++){
			comboBoxView.getChildren().add(new Label("Component class" + (i + 1)));
			comboBoxView.getChildren().add(getComboBox(components.keySet().stream().
					collect(Collectors.toList())));
		}
		for (int i = 0; i < Integer.parseInt(array[2]); i++){
			comboBoxView.getChildren().add(new Label(bundle.getString(newValue + "Strings").split(",")[i]));
			comboBoxView.getChildren().add(new TextField());
		}
		Button reset = ButtonFactory.makeButton(e -> updateComboBoxView(newValue));	
		reset.setText("Reset");
		comboBoxView.getChildren().add(reset);
	}
	public void addToEntityBox(EntityWrapper wrapper){
		entityBox.getChildren().stream().forEach(e -> System.out.println(e));
		for (int i = 0; i < numEntities; i++){
			if (entityArray[i] == null){
				entityArray[i] = wrapper.getEntity();
				if(!entityBox.getChildren().contains(wrapper.getDummy())){
					entityBox.getChildren().set(2 * i + 1, ImageBuilder.resizeReturn(new ImageView(wrapper.getDummy().
					getImage()), 50));
				}
				entityBox.getChildren().get(2 * i + 1).resize(50, 50);
				break;
			}
		}
	}

	private ComboBox<String> getComboBox(List<String> list) {
		ComboBox<String> box = new ComboBox<>();
		box.setItems(FXCollections.observableArrayList(list));
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