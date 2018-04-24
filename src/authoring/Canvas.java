package authoring;

import authoring.GUI_Heirarchy.GUINode;

import java.util.List;
import java.util.Map;

import authoring.controllers.EntityController;

import authoring.right_components.EntityComponent.EntityWrapper;
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
import javafx.scene.paint.Color;


/**
 * @author Jennifer Chin
 * @author Liam Pulsifer
 * Canvas class for the middle portion of the Authoring Environment. Displays the current
 * level and all Entities in that level. Allows the user to drag and drop images around
 * the canvas.
 */

public class Canvas implements GUINode {
	private Color backgroundColor = Color.rgb(179, 179, 179, 0.7);

	private Pane pane;
	private EntityController controller;
	
	/**
	 * Constructor, no parameters
	 */
	public Canvas(){
		
	}
	
	/**
	 * Method from GUINode interface. Returns a Pane that is the view of this GUINode.
	 * Canvas only requires a background color to start
	 * @return Pane
	 */
	public Pane getView(){
		pane = new Pane();
		pane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
		return pane;

	}
	
	/**
	 * Allows the canvas to be updated according to the current entities in the map.
	 * The map is created in EntityController and EntityController calls this method in
	 * order to update the canvas. 
	 * @param entityList
	 */
	public void update(List<EntityWrapper> entityList){
		pane.getChildren().clear();
		entityList.stream().forEach(e -> System.out.println(e));
		entityList.stream().forEach(e -> {pane.getChildren().add(e.getImageView());});
		System.out.println("Canvas updated");
	}
	
	/**
	 * Called by LevelController in order to set the background to a specified image
	 * @param im
	 */
	public void updateBackground(Image im){
		pane.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
	}

	/**
	 * Sets the EntityController of the Canvas. Called by AuthoringEnvironment to give 
	 * the correct EntityController object to the Canvas.
	 * @param controller
	 */
	public void setController(EntityController controller) {
		this.controller = controller;
	}

	public void listen() {
//		System.out.println("Listening");
//		pane.setOnMousePressed(e -> {
//			controller.alertEntityPane(e.getSceneX(), e.getSceneY());
//			System.out.println("Clicked");
//		});
	}

	public void stopListen() {
//		System.out.println("Stopped listening");
//		pane.setOnMouseClicked(e -> {});
	}
}
