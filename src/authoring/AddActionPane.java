package authoring;

import authoring.GUI_Heirarchy.GUINode;
import authoring.controllers.LevelController;
import authoring.right_components.EntityComponent.EntityWrapper;
import frontend_utilities.ComboBoxBuilder;
import frontend_utilities.ImageBuilder;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddActionPane implements GUINode {
	private static final ResourceBundle actions = ResourceBundle.getBundle("resources.keys/Actions");
	private Pane myPane;
	private VBox actionBox;
	private VBox comboBoxView;
	private LevelController levelController;
	private HBox entityBox;

	public AddActionPane() {
		myPane = new Pane();
		actionBox = new VBox();
		actionBox.setSpacing(20);
		comboBoxView = new VBox();
		comboBoxView.setSpacing(20);
		Label addComp = new Label("New Action");
		actionBox.getChildren().add(addComp);
		ComboBox<String> box = ComboBoxBuilder.getComboBox(actions.keySet().stream().collect(Collectors.toList()));
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
		comboBoxView.getChildren().clear();
		entityBox = new HBox();
	}

	@Override
	public Pane getView() {
		return myPane;
	}

	public void add(Node node){
		actionBox.getChildren().add(node);
	}

	public void addToEntityBox(EntityWrapper wrapper) {
		entityBox.getChildren().add(wrapper.getDummy());
	}
}
