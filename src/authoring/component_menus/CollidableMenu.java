package authoring.component_menus;

import java.util.ArrayList;
import java.util.List;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import game_engine.components.collision.CollidableComponent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

@Deprecated
public class CollidableMenu extends VBox  {
	private CheckBox intersectable;
	private CheckBox passable;
	private CheckBox pushable;
	public static final String TITLE = "Collidable";
	public CollidableMenu() {
		intersectable = new CheckBox();
		passable = new CheckBox();
		pushable = new CheckBox();
		intersectable.setIndeterminate(false);
		passable.setIndeterminate(false);
		pushable.setIndeterminate(false);
		this.getChildren().add(ButtonFactory.makeHBox("Check to make intersectable", null, intersectable));
		this.getChildren().add(ButtonFactory.makeHBox("Check to make passable", null, passable));
		this.getChildren().add(ButtonFactory.makeHBox("Check to make pushable", null, pushable));


	}

	public Component makeComponent() {
		List<String> list = new ArrayList<>();
		list.add(Boolean.toString(intersectable.isSelected()));
		list.add(Boolean.toString(passable.isSelected()));
		list.add(Boolean.toString(pushable.isSelected()));
		return new CollidableComponent("");
	}

	public Node getNode(){
		return this;
	}

	public TitledPane getTitledPane(){
		return new TitledPane(TITLE, this.getNode());
	}
}
