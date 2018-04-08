package authoring;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Canvas implements GUIComponent{

	public Pane getView(){
		VBox box = new VBox();
		StackPane pane = new StackPane();
		box.getChildren().add(pane);
		Rectangle rect = new Rectangle(pane.getWidth(), pane.getHeight());
		rect.setStyle("-fx-background-color: rgba(0,0,0.5)");
		box.setPadding(new Insets(20, 20, 20, 20));
		return box;
	}
}
