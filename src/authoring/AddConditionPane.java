package authoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring.GUI_Heirarchy.GUINode;
import authoring.component_menus.KeyMenuElement;
import authoring.component_menus.MenuElement;
import authoring.component_menus.StringMenuElement;
import authoring.controllers.LevelController;
import authoring.right_components.EntityComponent.EntityWrapper;
import authoring.right_components.EventPane;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ComboBoxBuilder;
import frontend_utilities.ImageBuilder;
import frontend_utilities.UserFeedback;
import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.NullComponent;
import game_engine.components.keyboard.KeyboardJumpInputComponent;
import game_engine.event.Condition;
import game_engine.event.ConditionFactory;
import game_engine.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import resources.keys.AuthRes;

/**
 * @author Liam Pulsifer
 * A pane for adding conditions to an event
 */

public class AddConditionPane extends Pane implements GUINode {
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
	private List<MenuElement<?>> menuElements;
	private boolean selected;
	private ComboBox<String> conditionBox;
	private Stage stage;
	private EventPane eventPane;

	public AddConditionPane(Event current, LevelController levelController, Stage s, EventPane ep) {
		eventPane = ep;
		numEntities = 0;
		stage = s;
		this.levelController = levelController;
		entityBox = new HBox();
		menuElements = new ArrayList<>();
		myPane = new Pane();
		actionBox = new VBox();
		actionBox.setSpacing(20);
		comboBoxView = new VBox();
		comboBoxView.setSpacing(20);
		Label addComp = new Label("New Condition:");
		addComp.getStyleClass().add("event-label");
		actionBox.getChildren().add(addComp);
		conditionBox = ComboBoxBuilder.getComboBox(conditions.keySet().stream()
				.filter(e -> !e.contains("String")).collect(Collectors.toList()));
		conditionBox.setPromptText("Select Condition");
		conditionBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			updateComboBoxView(newValue);
		});
		conditionBox.getStyleClass().add("combo-box-auth");
		actionBox.getChildren().add(conditionBox);
		actionBox.getChildren().add(comboBoxView);
		myPane.getChildren().add(actionBox);
	}

	/**
	 * Sets the level controller
	 * @param controller - level controller
	 */
	public void setLevelController(LevelController controller){
		this.levelController = controller;
	}

	/**
	 * Updates the comboBox indicating which condition to select with all the
	 * new input fields required
	 * @param newValue
	 */
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
			Label l = new Label("Entity " + (i + 1));
			l.getStyleClass().add("event-label2");
			entityBox.getChildren().add(l);
			Rectangle rect = new Rectangle(50, 50, Color.BLACK);
			Tooltip tip = new Tooltip("Click an entity to add to this Event");
			Tooltip.install(rect, tip);
			entityBox.getChildren().add(rect);
			entityArray[i] = null;
		}
		comboBoxView.getChildren().add(entityBox);
		for (int i = 0; i < Integer.parseInt(array[1]); i++){
			Label l = new Label("Choose component to assign condition to: ");
			l.getStyleClass().add("event-label2");
			comboBoxView.getChildren().add(l);
			ComboBox<String> componentBox = ComboBoxBuilder.getComboBox(components.keySet()
					.stream()
					.filter(e -> Boolean.parseBoolean(ResourceBundle.getBundle("Dateable").getString(e)))
					//.map(a -> ResourceBundle.getBundle("UserFriendlyNames").getString(a))
					.collect(Collectors.toList()));
			componentBox.valueProperty().addListener(((observable, oldValue, newValue1) -> {
				tryAdd(newValue1);
			}));
			componentBox.getStyleClass().add("combo-box-auth");
			comboBoxView.getChildren().add(componentBox);
		}
		for (int i = 0; i < Integer.parseInt(array[2]); i++){
			Label l = new Label(conditions.getString(newValue + "Strings").split(",")[i]);
			l.getStyleClass().add("event-label2");
			comboBoxView.getChildren().add(l);
			if(newValue.equals("KeyboardInput")){
				// negative chance that this instantiation of the KeyMenuElement is right...revisit and fix later
				KeyMenuElement element = new KeyMenuElement("Key", new KeyboardJumpInputComponent("W"));
				menuElements.add(element);
				comboBoxView.getChildren().add(element.getView());
			}
			else {
				MenuElement<String> element = new StringMenuElement(newValue,
						new NullComponent(""));
				menuElements.add(element);
				comboBoxView.getChildren().add((element).getView());
			}
		}
		ImageView iv = ImageBuilder.resize(new ImageView(new Image("game_player_resources/replay.png")), 20);
		Button reset = ButtonFactory.makeIconButton("Reset", iv, e -> updateComboBoxView(newValue));
		comboBoxView.getChildren().add(reset);
		System.out.println("Level Controller is " + levelController);
		Button addComponent = ButtonFactory.makeIconButton("+ Add Condition to Event", null, e -> {
			eventPane.getCurrentEvent().addCondition(newCondition(
				newValue, Arrays.asList(entityArray),
				compList,
				menuElements.stream().map(c -> c.getValue()).distinct().collect(Collectors.toList()),
				levelController.getEngine()));
			Alert a = UserFeedback.getInfoMessage(AuthRes.getString("AddCondHeader"), AuthRes.getString("AddCondContent"), stage);
			a.showAndWait();
		});
		comboBoxView.getChildren().add(addComponent);
	}

	/**
	 * Adds an entity icon to the entityBox to display which entities are selected to add conditions to
	 * @param wrapper
	 */
	public void addToEntityBox(EntityWrapper wrapper){
		if (selected) {
			entityBox.getChildren().stream().forEach(e -> System.out.println(e));
			for (int i = 0; i < numEntities; i++) {
				if (entityArray[i] == null) {
					entityArray[i] = wrapper.getEntity();
					if (!entityBox.getChildren().contains(wrapper.getDummy())) {
						entityBox.getChildren().set(2 * i + 1, ImageBuilder.resize(new ImageView(wrapper.getDummy().
								getImage()), 50));
					}
					entityBox.getChildren().get(2 * i + 1).resize(50, 50);
					break;
				}
			}
		}
		else {
			System.out.println("No focus -- Add Condition Pane");
		}
	}

	/**
	 * attempts to add a component class to the class list
	 * Prints the string it tried if it fails
	 * @param s
	 */
	private void tryAdd(String s){
		String actual = translateFriendly(s);
		if (s != null) {
			try {
				compList.add((Class<Component<?>>) Class.forName(components.getString(actual)));
			} catch (Exception e) {
				System.out.println("Sorry b I didn't find that");
				try {
					System.out.println("Heres the string I tried" + actual);
				} catch (NullPointerException a) {

				}
			}
		}
	}

	/**
	 *
	 * @param s a user-friendly component string
	 * @return the original string
	 */
	private String translateFriendly(String s) {
		ResourceBundle friendly = ResourceBundle.getBundle("UserFriendlyNames");
		for (String current : friendly.keySet()) {
			if (friendly.getString(current).equals(s)) {
				return current;
			}
		}
		return "";
	}

	/**
	 * Gets a new condition from the condition factory
	 * @param s -- name of the condition
	 * @param entities -- list of entities to be used
	 * @param components -- list of component classes to be used
	 * @param args -- list of string arguments
	 * @param engine -- Engine
	 * @return
	 */
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

	/**
	 * Adds a node to the main VBox of this class
	 * @param node
	 */
	public void add(Node node){
		actionBox.getChildren().add(node);
	}

	/**
	 * Sets the selected variable (which decides if this pane is selected for the purpose
	 * of user clicks on the Event Pane
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}