package authoring.component_menus;

import authoring.utilities.ButtonFactory;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ImageMenu implements ComponentMenu {

	@Override
	public Component makeComponent() {
		return null;
	}

	@Override
	public Node getNode() {
		return ButtonFactory.makeHBox("Select an Image", "Sprite", new Circle(10, 10, 10, Color.BLUE));
	}

	@Override
	public TitledPane getTitledPane() {
		return new TitledPane("Choose an Image", this.getNode());
	}
}
