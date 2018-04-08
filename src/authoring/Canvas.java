package authoring;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
