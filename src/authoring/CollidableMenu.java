package authoring;

import authoring.utilities.ButtonFactory;
import game_engine.Component;
import game_engine.components.Collidable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;

public class CollidableMenu extends CheckBox implements ComponentMenu {
	public CollidableMenu() {
		this.setIndeterminate(false);
		this.setSelected(true);

	}
	public Component makeComponent() {
		return new Collidable(this.isSelected());
	}
	public Node getNode(){
		return ButtonFactory.makeHBox("Check to make collidable","", this);
	}
	public TitledPane getTitledPane(){
		return new TitledPane("Collidable", this.getNode());
	}
}
