package authoring.right_components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring.AddActionPane;
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
	private HBox entityBox;
	private int numEntities;
	private Entity[] entityArray;
	private List<Event> eventList;
	private Event currentEvent;
	private ComboBox<String> componentBox;
	private List<MenuElement> menuElements;
	private LevelController levelController;
	private ArrayList<Class<Component<?>>>  compList;

	public EventPane(EntityController e){
		this();
		myController = e;
	}
	public EventPane(){
		menuElements = new ArrayList<>();
		componentBox = new ComboBox<>();
		eventList = new ArrayList<>();
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
		initAddAction();
		initViewEvents();
		
	}

	private void initAddAction() {
		AddActionPane pane = new AddActionPane();
		addAction = pane.getView();
		Button back = ButtonFactory.makeButton(e -> {
			clearAndAdd(newEvent);
		});
		pane.add(ButtonFactory.makeHBox("Back", null, back));

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
		ComboBox<String> box = ComboBoxBuilder.getComboBox(bundle.keySet().stream().filter(e -> !e.contains("Strings")).collect(Collectors.toList()));
		box.valueProperty().addListener((observable, oldValue, newValue) -> {
			updateComboBoxView(newValue);
		});
		//comboBoxView.getChildren().add(new ImageView(new Image("default.jpg")));
		conditionBox.getChildren().add(box);
		conditionBox.getChildren().add(comboBoxView);
		addCondition.getChildren().add(conditionBox);
	}

	private void updateComboBoxView(String newValue) {
		compList = new ArrayList<>();
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
			comboBoxView.getChildren().add(new Label("Choose component to assign this condition to: " + (i + 1)));
			componentBox = ComboBoxBuilder.getComboBox(components.keySet().stream().collect(Collectors.toList()));
			componentBox.valueProperty().addListener(((observable, oldValue, newValue1) -> {
				tryAdd(newValue1);
			}));
			comboBoxView.getChildren().add(componentBox);
		}
		for (int i = 0; i < Integer.parseInt(array[2]); i++){
			comboBoxView.getChildren().add(new Label(bundle.getString(newValue + "Strings").split(",")[i]));
			if(newValue.equals("KeyboardInput")){
				KeyMenuElement element = new KeyMenuElement("Key", new NullComponent(0.0));
				menuElements.add(element);
				comboBoxView.getChildren().add(element.getView());
			}
			else {
				MenuElement element = new StringMenuElement(newValue,
						new NullComponent(0.0));
				menuElements.add(element);
				comboBoxView.getChildren().add((element).getView());
			}
		}
		Button reset = ButtonFactory.makeButton(e -> updateComboBoxView(newValue));	
		reset.setText("Reset");
		reset.setAlignment(Pos.CENTER);
		comboBoxView.getChildren().add(reset);
		Button addComponent = ButtonFactory.makeButton(e -> currentEvent.addCondition(newCondition(
				newValue, Arrays.asList(entityArray),
				compList,
				menuElements.stream().map(c -> c.getValue()).distinct().collect(Collectors.toList()),
				levelController.getEngine()
		)));
		HBox addCompBox = ButtonFactory.makeHBox("Add this component to the current Event",
				null,
				addComponent);
		comboBoxView.getChildren().add(addCompBox);
	}
	private void tryAdd(String s){
		if (s != null) {
			try {
				compList.add((Class<Component<?>>) Class.forName(components.getString(s)));
			} catch (Exception e) {
				System.out.println("Sorry b I didn't find that");
				try {
					System.out.println("Heres the string I tried" + s);
				} catch (NullPointerException a) {

				}
			} 
		}
	}

	private Condition newCondition(String s, List<Entity> entities, List<Class<Component<?>>> components,
	                              List<String> args, Engine engine ) {
		System.out.println("String: " + s);
		System.out.println("Entities " + entities);
		entities.stream().forEach(e -> System.out.println(e));
		System.out.println("Component Classes " + components);
		components.stream().forEach(b -> System.out.println(b));
		System.out.println("Args" + args);
		args.stream().forEach(c -> System.out.println(c));
		System.out.println("Engine");
		System.out.println(engine);
		//return ConditionFactory.newInstance(s, entities, components, args, engine);
		return new Condition() {
			@Override
			public boolean evaluate() {
				return false;
			}
		};
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

	public void setLevelController(LevelController levelController) {
		this.levelController = levelController;
	}
}