package authoring;

import java.util.List;

import authoring.GUI_Heirarchy.GUINode;
import authoring.controllers.EntityController;
import authoring.right_components.EntityComponent.EntityWrapper;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
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

public class Canvas extends SubScene implements GUINode {
	private Color backgroundColor = Color.rgb(179, 179, 179, 0.7);
	private Pane myPane;
	private Group root;
	private EntityController myController;

	
	/**
	 * Constructor; establishes the canvas' root, initializes the camera, and sets the canvas
	 * to autosize.
	 * @param root
	 */
	public Canvas(Group root){	
		super(root, 1000,4000);	//arbitrary values, because overriden with autosize()?
		this.root = root;
//		autosize();
		this.setCamera(new ParallelCamera());
		this.setManaged(false);
		getView();
	}

	
	/**
	 * Method from GUINode interface. Returns a Pane that is the view of this GUINode.
	 * Canvas only requires a background color to start
	 * @return Pane
	 */
	public Pane getView(){
		myPane = new Pane();
		myPane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
		root.getChildren().add(myPane);
		return myPane;
	}

	
	/**
	 * Allows the canvas to be updated according to the current entities in the map.
	 * The map is created in EntityController and EntityController calls this method in
	 * order to update the canvas. 
	 * @param entityList
	 */
	public void update(List<EntityWrapper> entityList){
		myPane.getChildren().clear();
		entityList.stream().forEach(e -> System.out.println(e));
		entityList.stream().forEach(e -> myPane.getChildren().add(e.getImageView()));
		System.out.println("Canvas updated");
	}
	
	/**
	 * Called by LevelController in order to set the background to a specified image
	 * @param im
	 */
	public void updateBackground(Image im){
		myPane.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.REPEAT,
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
}
