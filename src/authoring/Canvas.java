package authoring;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Canvas implements GUIComponent{
	private int size;
	public Canvas(int size){
		this.size = size;
	}
	public Pane getView(){
		VBox box = new VBox();
		StackPane pane = new StackPane();
		//box.getChildren().add(pane);
		Rectangle rect = new Rectangle(size, size);
		rect.setStyle("-fx-background-color: rgba(0,0,0.5)");
		box.getChildren().add(rect);
		box.setPadding(new Insets(20, 20, 20, 20));
		return box;
	}
}
