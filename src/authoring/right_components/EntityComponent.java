package authoring.right_components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

public class EntityComponent extends BaseComponent {


	@Override
	public Pane getView() {
        VBox box = buildBasicView(AuthRes.getString("EntityTitle"));        
		box.getChildren().add(getStack()); //change when we add image addition functionality
		//What's the purpose of the .collect function?
		getButtonArray().stream().map((button) -> box.getChildren().add(button)).collect(Collectors.toList());
	    return box;
	}
	

	public Node getStack() {
		StackPane pane = new StackPane();
		pane.getChildren().add(ImageBuilder.getImageView("jen.png", 200, 200));
		return pane;
	}

	@Override
	public List<HBox> getButtonArray() {
		ArrayList<HBox> list = new ArrayList<>();
		list.add(ButtonFactory.makeHBox("New Sprite", "Upload a New Image"));
		list.add(ButtonFactory.makeHBox("Add Behavior", "Attach an Event to this Entity"));

		return list;
	}

}
