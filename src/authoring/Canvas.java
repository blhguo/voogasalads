package authoring;

import game_engine.Entity;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.Map;

public class Canvas implements GUINode {
	private int size;
	private StackPane pane;
	private VBox box;
	private EntityController controller;
	public Canvas(int size){
		this.size = size;
	}
	public VBox getView(){
		box = new VBox();
		StackPane pane = new StackPane();
		//box.getChildren().add(pane);
		Rectangle rect = new Rectangle(size, size);
		rect.setStyle("-fx-background-color: rgba(0,0,0.5)");
		box.getChildren().add(rect);
		//pane.setPadding(new Insets(20, 20, 20, 20));
		box.setAlignment(Pos.CENTER);
		return box;
	}
	public void update(Map<ImageView, Entity> map){
		box.getChildren().clear();
		for (ImageView view : map.keySet()){
			box.getChildren().add(view);
			view.toFront();
		}
	}

	public void setController(EntityController controller) {
		this.controller = controller;
	}
}
