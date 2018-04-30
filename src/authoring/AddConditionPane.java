package authoring;

import authoring.GUI_Heirarchy.GUINode;
import authoring.component_menus.KeyMenuElement;
import authoring.component_menus.MenuElement;
import authoring.component_menus.StringMenuElement;
import authoring.controllers.LevelController;
import authoring.right_components.EntityComponent.EntityWrapper;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ComboBoxBuilder;
import frontend_utilities.ImageBuilder;
import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.NullComponent;
import game_engine.event.AuthorableEvent;
import game_engine.event.Condition;
import game_engine.event.ConditionFactory;
import game_engine.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddConditionPane implements GUINode {
	private static final ResourceBundle conditions = ResourceBundle.getBundle("resources.keys/Conditions");
	private LevelController levelController;
	private ResourceBundle components = ResourceBundle.getBundle("Component");
	private Pane myPane;
	private VBox actionBox;
	private VBox comboBoxView;
	private ArrayList<Class<Component<?>>>  compList;
	private HBox entityBox;
	private int numEntities;
	private Entity[] entityArray;
	private ComboBox<String> componentBox;
	private List<MenuElement> menuElements;
	private Event currentEvent;


	public AddConditionPane(Event current, LevelController levelController) {
		this.levelController = levelController;
		entityBox = new HBox();
		currentEvent = current;
		menuElements = new ArrayList<>();
		myPane = new Pane();
		actionBox = new VBox();
		actionBox.setSpacing(20);
		comboBoxView = new VBox();
		comboBoxView.setSpacing(20);
		Label addComp = new Label("New Condition");
		actionBox.getChildren().add(addComp);
		ComboBox<String> box = ComboBoxBuilder.getComboBox(conditions.keySet().stream()
				.filter(e -> !e.contains("String")).collect(Collectors.toList()));
		box.valueProperty().addListener((observable, oldValue, newValue) -> {
			updateComboBoxView(newValue);
		});
		actionBox.getChildren().add(box);
		actionBox.getChildren().add(comboBoxView);
		myPane.getChildren().add(actionBox);
	}
	public void setLevelController(LevelController controller){
		this.levelController = controller;
	}
	private void updateComboBoxView(String newValue) {
		compList = new ArrayList<>();
		comboBoxView.getChildren().clear();
		entityBox.getChildren().clear();

		System.out.println(newValue);
		System.out.println(conditions.getString(newValue));
		String[] array = conditions.getString(newValue).split(",");
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
			componentBox = ComboBoxBuilder.getComboBox(components.keySet()
					.stream()
					.filter(e -> Boolean.parseBoolean(ResourceBundle.getBundle("Dateable").getString(e)))
					.map(a -> ResourceBundle.getBundle("UserFriendlyNames").getString(a))
					.collect(Collectors.toList()));
			componentBox.valueProperty().addListener(((observable, oldValue, newValue1) -> {
				tryAdd(newValue1);
			}));
			comboBoxView.getChildren().add(componentBox);
		}
		for (int i = 0; i < Integer.parseInt(array[2]); i++){
			comboBoxView.getChildren().add(new Label(conditions.getString(newValue + "Strings").split(",")[i]));
			if(newValue.equals("KeyboardInput")){
				KeyMenuElement element = new KeyMenuElement("Key", new NullComponent(""));
				menuElements.add(element);
				comboBoxView.getChildren().add(element.getView());
			}
			else {
				MenuElement element = new StringMenuElement(newValue,
						new NullComponent(""));
				menuElements.add(element);
				comboBoxView.getChildren().add((element).getView());
			}
		}
		Button reset = ButtonFactory.makeButton(e -> updateComboBoxView(newValue));
		reset.setText("Reset");
		reset.setAlignment(Pos.CENTER);
		comboBoxView.getChildren().add(reset);
		System.out.println("Level Controller is " + levelController);
		Button addComponent = ButtonFactory.makeButton(e -> {currentEvent.addCondition(newCondition(
				newValue, Arrays.asList(entityArray),
				compList,
				menuElements.stream().map(c -> c.getValue()).distinct().collect(Collectors.toList()),
				levelController.getEngine()));
			currentEvent.getConditions().stream().forEach(a -> System.out.println(a));
		});
		HBox addCompBox = ButtonFactory.makeHBox("Add this component to the current Event",
				null,
				addComponent);
		comboBoxView.getChildren().add(addCompBox);
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
		return ConditionFactory.createCondition(s + "Condition", entities, components, args, engine);
	}
	@Override
	public Pane getView() {
		return myPane;
	}

	public void add(Node node){
		actionBox.getChildren().add(node);
	}
}