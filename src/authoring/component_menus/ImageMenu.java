package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import resources.keys.AuthRes;

@Deprecated
public class ImageMenu  {


	public Component makeComponent() {
		return null;
	}


	public Node getNode() {
		return ButtonFactory.makeHBox("Select an Image", "Sprite", new Circle(
				AuthRes.getInt("DefaultImageSize"), 
				AuthRes.getInt("DefaultImageSize"), 
				AuthRes.getInt("DefaultImageSize"), Color.BLUE));
	}


	public TitledPane getTitledPane() {
		return new TitledPane("Choose an Image", this.getNode());
	}
}
