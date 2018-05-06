package authoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring.GUI_Heirarchy.GUINode;
import authoring.component_menus.DoubleMenuElement;
import authoring.component_menus.StringMenuElement;
import authoring.controllers.LevelController;
import authoring.right_components.EntityComponent.EntityWrapper;
import authoring.right_components.EventPane;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ComboBoxBuilder;
import frontend_utilities.ImageBuilder;
import frontend_utilities.UserFeedback;
import game_engine.Component;
import game_engine.ComponentFactory;
import game_engine.Entity;
import game_engine.components.NullComponent;
import game_engine.components.sprite.ZHeightComponent;
import game_engine.event.Event;
import game_engine.event.actions.macro.AddEntityAction;
import game_engine.event.actions.macro.LevelChangeAction;
import game_engine.event.actions.macro.PlayMusicAction;
import game_engine.event.actions.macro.RemoveEntityAction;
import game_engine.event.actions.micro.AddComponentAction;
import game_engine.event.actions.micro.DataChangeAction;
import game_engine.event.actions.micro.DataSetAction;
import game_engine.event.actions.micro.DataToggleAction;
import game_engine.event.actions.micro.RemoveComponentAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * A pane for adding actions to an event
 *
 */
public class AddActionPane extends Pane implements GUINode {
	private static final ResourceBundle actions = ResourceBundle.getBundle("resources.keys/Actions");
	private static final ResourceBundle components = ResourceBundle.getBundle("Component");
	private static final ResourceBundle componentargs = ResourceBundle.getBundle("CompArguments");
	private Pane myPane;
	private VBox actionBox;
	private VBox comboBoxView;
	private LevelController levelController;
	private HBox entityBox;
	private int numEntities;
	private Entity[] entityArray;
	private ComboBox<String> compBox;
	private int numValue;
	private int numExpressions;
	private List<DoubleMenuElement> numberElements;
	private List<StringMenuElement> stringElements;
	private ComboBox<String> expressionBox;
	private Stage stage;
	private boolean selected;
	private EventPane eventPane;

	public AddActionPane(Event current, Stage s, EventPane ep) {
		eventPane = ep;
		stage = s;
		numEntities = 0;
		entityBox = new HBox();
		numberElements = new ArrayList<>();
		stringElements = new ArrayList<>();
		myPane = new Pane();
		actionBox = new VBox();
		actionBox.setSpacing(20);
		comboBoxView = new VBox();
		comboBoxView.setSpacing(AuthRes.getInt("Margin"));
		Label addComp = new Label("New Action");
		addComp.getStyleClass().add("event-label");
		actionBox.getChildren().add(addComp);
		ComboBox<String> box = ComboBoxBuilder.getComboBox(actions.keySet().stream().collect(Collectors.toList()));
		box.setPromptText("Select Action");
		box.valueProperty().addListener((observable, oldValue, newValue) -> {
			updateComboBoxView(newValue);
		});
		box.getStyleClass().add("combo-box-auth");
		actionBox.getChildren().add(box);
		actionBox.getChildren().add(comboBoxView);
		myPane.getChildren().add(actionBox);	
	}

	/**
	 * Sets the level controller
	 * @param controller -- Level Controller
	 */
	public void setLevelController(LevelController controller){
		this.levelController = controller;
	}

	/**
	 * Updates all of the input fields for the chosen new type of action to be added
	 * @param newValue -- The new value for the desired type of action to be added
	 */
	private void updateComboBoxView(String newValue) {
		comboBoxView.getChildren().clear();
		numberElements.clear();
		stringElements.clear();


		Button createButton;
		switch(newValue){

			case "AddEntityAction":
				comboBoxView.getChildren().add(getEntityInput());
				createButton = ButtonFactory.makeIconButton(AuthRes.getString("AddAction"), null, e -> {
					eventPane.getCurrentEvent().addAction(new AddEntityAction(entityArray[0], levelController.getEngine()));
					createAlert();
				});
				comboBoxView.getChildren().add(createButton);
				break;
			case "PlayMusicAction":
				comboBoxView.getChildren().add(getStringInput());
				comboBoxView.getChildren().add(getDoubleInput());
				createButton = ButtonFactory.makeIconButton(AuthRes.getString("AddAction") , null, e -> {
					eventPane.getCurrentEvent().addAction(new PlayMusicAction(stringElements.get(0).getValue(),
							Double.parseDouble(numberElements.get(0).getValue())));
					createAlert();
				});
				comboBoxView.getChildren().add(createButton);
				break;
			case "LevelChangeAction":
				comboBoxView.getChildren().add(getDoubleInput());
				createButton = ButtonFactory.makeIconButton(AuthRes.getString("AddAction"), null, e -> {
					eventPane.getCurrentEvent().addAction(new LevelChangeAction(levelController.getEngine(),
							Double.parseDouble(((numberElements.get(0).getValue())))));
					createAlert();
				});
				comboBoxView.getChildren().add(createButton);
				break;
			case "RemoveEntityAction":
				comboBoxView.getChildren().add(getEntityInput());
				createButton = ButtonFactory.makeIconButton(AuthRes.getString("AddAction"), null, e -> {
					eventPane.getCurrentEvent().addAction(new RemoveEntityAction(entityArray[0], levelController.getEngine()));
					createAlert();
				});
				comboBoxView.getChildren().add(createButton);
				break;
			case "AddComponentAction":
				comboBoxView.getChildren().add(getEntityInput());
				comboBoxView.getChildren().add(getClassInput());
				comboBoxView.getChildren().add(getDoubleInput());
				createButton = ButtonFactory.makeIconButton(AuthRes.getString("AddAction"), null, e -> {
					eventPane.getCurrentEvent().addAction(new AddComponentAction(entityArray[0],
							new ComponentFactory().createComponent(compBox.getValue(),
									numberElements.get(0).getValue())));
					createAlert();
				});
				comboBoxView.getChildren().add(createButton);
				break;
			case "RemoveComponentAction":
				comboBoxView.getChildren().add(getEntityInput());
				comboBoxView.getChildren().add(getClassInput());
				createButton = ButtonFactory.makeIconButton(AuthRes.getString("AddAction"), null, e -> {
					try {
						eventPane.getCurrentEvent().addAction(new RemoveComponentAction(entityArray[0],
								(Class<? extends Component<?>>)	Class.forName(components.getString(compBox.getValue()))));
						createAlert();
					}
					catch (Exception a) {
						createError();
						System.out.println(a.getCause());
						System.out.println("Sorry, Class machine broke");
					}
				});
				comboBoxView.getChildren().add(createButton);
				break;
			case "DataChangeAction":
				comboBoxView.getChildren().add(getEntityInput());
				comboBoxView.getChildren().add(getClassInput());
				comboBoxView.getChildren().add(getExpressionInput());
				comboBoxView.getChildren().add(getDoubleInput());
				createButton = ButtonFactory.makeIconButton(AuthRes.getString("AddAction"), null, e -> {
					try {
						eventPane.getCurrentEvent().addAction(new DataChangeAction(
										entityArray[0],
										(Class<? extends Component<Double>>)
												Class.forName(components.getString(compBox.getValue())),
									expressionBox.getValue(),
									Double.parseDouble(numberElements.get(0).getValue())
						));
						createAlert();
					} catch (Exception a){
						createError();
						System.out.println("Sorry, class machine broke");
					}
				});
				comboBoxView.getChildren().add(createButton);
				break;
			case "DataToggleAction":
				comboBoxView.getChildren().add(getEntityInput());
				comboBoxView.getChildren().add(getClassInput());
				createButton = ButtonFactory.makeIconButton(AuthRes.getString("AddAction"), null, e -> {
					try{
						eventPane.getCurrentEvent().addAction(new DataToggleAction(
								entityArray[0],
								(Class<? extends Component<Boolean>>) 
								Class.forName(components.getString(compBox.getValue()))));
						createAlert();	
					} catch (Exception a){
						createError();
//						a.printStackTrace();
						System.out.println("Sorry, class machine broke");
					}
				});
				comboBoxView.getChildren().add(createButton);
				break;
			case "DataSetAction":
				comboBoxView.getChildren().add(getEntityInput());
				comboBoxView.getChildren().add(getClassInput());
				comboBoxView.getChildren().add(getDoubleInput());
				createButton = ButtonFactory.makeIconButton(AuthRes.getString("AddAction"), null, e -> {
					try{
						eventPane.getCurrentEvent().addAction(new DataSetAction(
								entityArray[0],
								(Class<? extends Component<Double>>) 
								Class.forName(components.getString(compBox.getValue())),
								Double.parseDouble(numberElements.get(0).getValue())));
						createAlert();	
					} catch (Exception a){
						createError();
						System.out.println("Sorry, class machine broke");

					}
				});
				comboBoxView.getChildren().add(createButton);
				break;
		}
		ImageView iv = ImageBuilder.resize(new ImageView(new Image("game_player_resources/replay.png")), 20);
		Button reset = ButtonFactory.makeIconButton("Reset", iv, e -> updateComboBoxView(newValue));
		comboBoxView.getChildren().add(reset);

	}

	/**
	 * Alerts the user that they've successfully added an action
	 */
	private void createAlert(){
		Alert a = UserFeedback.getInfoMessage(AuthRes.getString("AddActionHeader"), AuthRes.getString("AddActionContent"), stage);
		a.showAndWait();
	}

	/**
	 * Creates a user error message
	 */
	private void createError(){
		Alert a = UserFeedback.getErrorMessage(AuthRes.getString("ActionErrorHeader"), AuthRes.getString("ActionErrorContent"), stage);
		a.showAndWait();
	}
	@Deprecated
	public void newAction(){

	}

	/**
	 * Allows the user to click on an entity and select it for use in the action
	 * @return an HBox with an entity input field
	 */
	public Node getEntityInput(){
		System.out.println("Got entity input");
		numEntities = 1;
		entityArray = new Entity[1];
		entityBox = new HBox();
		entityBox.setSpacing(25);
		for (int i = 0; i < numEntities; i++) {
			Label l = new Label("Entity " + (i + 1));
			l.getStyleClass().add("event-label2");
			entityBox.getChildren().add(l);
			Rectangle rect = new Rectangle(50, 50, Color.BLACK);
			Tooltip tip = new Tooltip("Click an entity to add to this Event");
			Tooltip.install(rect, tip);
			entityBox.getChildren().add(rect);
			entityArray[i] = null;
		}
		return entityBox;
	}

	/**
	 * Allows a user to select a class name for use in the action
	 * @return a new VBox with a class input comboBox
	 */
	public Node getClassInput(){
		int numComponents = 1;
		VBox box = new VBox();
		for (int i = 0; i < numComponents; i++) {
			Label l = new Label("Enter Component to act on: ");
			l.getStyleClass().add("event-label2");
			box.getChildren().add(l);
			compBox = ComboBoxBuilder.getComboBox(components.keySet().stream().collect(Collectors.toList()));
			compBox.getStyleClass().add("combo-box-auth");
			box.getChildren().add(compBox);
		}
		return box;
	}

	/**
	 * Gets user expression input (i.e. +, -, *, etc)
	 * @return a ComboBox with the desired expressions
	 */
	public Node getExpressionInput(){
		VBox box = new VBox();
		ObservableList<String> data = FXCollections.observableArrayList("+", "-", "*", "/", "%");
		expressionBox  = new ComboBox<>(data);
		expressionBox.getStyleClass().add("combo-box-auth");
		Label label = new Label("Enter expression to evaluate on component");
		label.getStyleClass().add("event-label2");
		box.getChildren().add(label);
		box.getChildren().add(expressionBox);
		
		return box;
	}

	/**
	 * Gets user string input
	 * @return a VBox with a string menu element
	 */
	public Node getStringInput(){
		numExpressions = 1;
		VBox box = new VBox();
		for (int i = 0; i < numExpressions; i++){
			StringMenuElement element = new StringMenuElement("String Input: ", new NullComponent(""));
			stringElements.add(element);
			box.getChildren().add(element.getView());
		}
		return box;
	}

	/**
	 * Gets user Double input
	 * @return - a VBox with a double input field
	 */
	public Node getDoubleInput(){
		numExpressions = 1;
		VBox box = new VBox();
		for (int i = 0; i < numExpressions; i++){
			DoubleMenuElement element = new DoubleMenuElement("Value: ", new ZHeightComponent("-1"));
			numberElements.add(element);
			box.getChildren().add(element.getView());
		}
		return box;
	}
	public void addToEntityBox(EntityWrapper wrapper){
		if (selected) {
			if (entityBox == null) {
				entityBox = new HBox();
			}
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
			System.out.println("No focus -- Add Action Pane");
		}
	}

	/**
	 * @return the pane view
	 */
	@Override
	public Pane getView() {
		return myPane;
	}

	/**
	 * @param node -- a node to be added to this pane's main VBox
	 */
	public void add(Node node){
		actionBox.getChildren().add(node);
	}

	/**
	 * Sets selected (for use in the addEvent pane)
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
