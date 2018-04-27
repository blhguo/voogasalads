package authoring;

import java.util.List;

import authoring.GUI_Heirarchy.GUINode;
import authoring.controllers.EntityController;
import authoring.right_components.EntityComponent.EntityWrapper;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
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
	private SubScene mySubscene;
	private EntityController myController;
	private Pane myRootPane;
	private BorderPane parent;

	
	/**
	 * Constructor; establishes the canvas' root, initializes the camera, and sets the canvas
	 * to autosize.
	 * @param root
	 */
	public Canvas(BorderPane parent){	
		myRootPane = new Pane();
		this.parent = parent;
		myRootPane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
		
		mySubscene = new SubScene(myRootPane,
//				stage.getWidth()-AuthRes.getInt("PrefBaseSize")-AuthRes.getInt("PrefNavPaneSize"), 100);
//				myRootPane.getBoundsInParent().getWidth(),
//				myRootPane.getBoundsInParent().getHeight());
				600, 600);
				
				
//		mySubscene.autosize;
		mySubscene.setCamera(new ParallelCamera());
//		mySubscene.setManaged(false);
	}
	
	/**
	 * Allows the canvas to be updated according to the current entities in the map.
	 * The map is created in EntityController and EntityController calls this method in
	 * order to update the canvas. 
	 * @param entityList
	 */
	public void update(List<EntityWrapper> entityList){
		myRootPane.getChildren().clear();
		entityList.stream().forEach(e -> System.out.println(e));
		entityList.stream().forEach(e -> myRootPane.getChildren().add(e.getImageView()));
		System.out.println("Canvas updated");
	}
	
	/**
	 * Sets the background to a specified image
	 * @param im
	 */
	public void updateBackground(Image im){
		myRootPane.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
	}

	/**
	 * Sets the EntityController of the Canvas. Called by AuthoringEnvironment to give 
	 * the correct EntityController object to the Canvas.
	 * @param controller
	 */
	public void setController(EntityController controller) {
		myController = controller;
	}
	
	@Override
	public Node getView() {
		return mySubscene;
	}



}
