package authoring;

import game_engine.Entity;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Map;

public class Canvas implements GUIComponent{
	private int size;
	private Pane pane;
	private VBox box;
	private EntityController controller;
	public Canvas(int size){
		this.size = size;
	}
	public Pane getView(){
		pane = new Pane();
		//StackPane pane = new StackPane();
		//box.getChildren().add(pane);
//		Rectangle rect = new Rectangle(size, size);
//		rect.setStyle("-fx-background-color: rgba(0,0,0.5)");
		//box.getChildren().add(rect);
		//pane.setPadding(new Insets(20, 20, 20, 20));
		pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;

	}
	public void update(Map<ImageView, Entity> map){
		pane.getChildren().clear();
		for (ImageView view : map.keySet()){
			pane.getChildren().add(view);
			view.toFront();
		}
	}

	public void setController(EntityController controller) {
		this.controller = controller;
	}
}
