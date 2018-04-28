package authoring;

import authoring.GUI_Heirarchy.GUINode;
import frontend_utilities.ComboBoxBuilder;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddActionPane implements GUINode{
	private static final ResourceBundle actions = ResourceBundle.getBundle("resources.keys/Actions");
	private Pane myPane;
	private VBox actionBox;

	public AddActionPane(){
		myPane = new Pane();
		actionBox = new VBox();
		actionBox.setSpacing(20);
		Label addComp = new Label("New Action");
		actionBox.getChildren().add(addComp);
		ComboBox<String> box = ComboBoxBuilder.getComboBox(actions.keySet().stream().collect(Collectors.toList()));
		box.valueProperty().addListener((observable, oldValue, newValue) -> {

		});
		actionBox.getChildren().add(box);
		myPane.getChildren().add(actionBox);
	}

	@Override
	public Pane getView() {
		return myPane;
	}

	public void add(Node node){
		actionBox.getChildren().add(node);
	}
}
