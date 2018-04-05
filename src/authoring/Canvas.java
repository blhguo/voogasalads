package authoring;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Canvas implements GUIComponent{

	public Pane getView(){
		StackPane pane = new StackPane();
		BackgroundImage back = new BackgroundImage(new Image("background1.jpg"), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		pane.setBackground(new Background(back));
		pane.setMaxWidth(500);
		return pane;
	}
}
