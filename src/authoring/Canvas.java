package authoring;

import game_engine.Entity;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Map;


import authoring.controllers.EntityController;

public class Canvas implements GUINode {
	private Color backgroundColor = Color.rgb(179, 179, 179, 0.7);

	private Pane pane;
	private EntityController controller;
	
	
	public Canvas(){
		
	}
	
	public Pane getView(){
		pane = new Pane();
		pane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;

	}
	public void update(Map<ImageView, Entity> map){
		pane.getChildren().clear();
		for (ImageView view : map.keySet()){
			System.out.println("Current view");
			pane.getChildren().add(view);
			view.toFront();
		}
		System.out.println("Canvas updated");
	}
	
	public void updateBackground(Image im){
		pane.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
	}

	public void setController(EntityController controller) {
		this.controller = controller;
	}
}
