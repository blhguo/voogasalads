package authoring;

import java.util.List;
import java.util.stream.Collectors;

import authoring.GUI_Heirarchy.GUINode;
import authoring.controllers.EntityController;
import authoring.right_components.EntityComponent.EntityWrapper;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
 * @author elizabethshulman
 * @author Jennifer Chin
 * @author Liam Pulsifer
 * Canvas class for the middle portion of the Authoring Environment. Displays the current
 * level and all Entities in that level. Allows the user to drag and drop images around
 * the canvas.
 */

public class Canvas implements GUINode {
private Pane myInfinitePane;
	private ScrollPane myNode;
	private EntityController myController;
	private Color backgroundColor = Color.rgb(179, 179, 179, 0.6);

	/**
	 * Constructor that creates new internal canvas
	 * and sets up ScrollPane
	 */
	public Canvas() {
		myInfinitePane = initializeInfinitePane();
		myNode = initializeScrollingPane();
	}
	
	public void setDefaultBackground(Pane pane){
		pane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	/**
	 * Allows the canvas to be updated according to the current entities in the map.
	 * The map is created in EntityController and EntityController calls this method in
	 * order to update the canvas. 
	 * @param entityList
	 */
	public void update(List<EntityWrapper> entityList){
		myInfinitePane.getChildren().clear();
		System.out.println("-----Updating Canvas------");
		entityList.stream().forEach(e -> System.out.println("Entity " + e));
		for (ImageView view : entityList.stream().map(e -> e.getImageView()).collect(Collectors.toList())){
			if (!myInfinitePane.getChildren().contains(view))
				myInfinitePane.getChildren().add(view);
		}
		//entityList.stream().forEach(e -> {pane.getChildren().add(e.getImageView());});
		System.out.println("Canvas updated");
	}
	
	/**
	 * Sets the background to a specified image
	 * @param im
	 */
	public void updateBackground(Image im){
		myInfinitePane.setBackground(new Background(new BackgroundImage(im, BackgroundRepeat.REPEAT,
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
	
	public void listen() {
		System.out.println("Listening");
		myInfinitePane.setOnMousePressed(e -> {
			myController.alertEntityPane(e.getX(), e.getY());
			System.out.println("Clicked -- Canvas line 100");
		});
	}
	
	public void stopListen() {
//		System.out.println("Stopped listening");
//		pane.setOnMouseClicked(e -> {});
	}
	
	private Pane initializeInfinitePane() {
		myInfinitePane = new Pane();
		myInfinitePane.setPrefSize(4000,4000);
		myInfinitePane.setMaxSize(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		setDefaultBackground(myInfinitePane);
		return myInfinitePane;
	}
	
	/**
	 * Method to initialize scrolling window
	 * Always show scrolling bars.
	 * Sets view to the center of internal canvas,
	 * as turtle is initialized in center
	 * 
	 */
	private ScrollPane initializeScrollingPane() {
		myNode = new ScrollPane();
		myNode.setContent(myInfinitePane);
		myNode.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myNode.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		myInfinitePane.setManaged(false);
		myNode.setHvalue(0);
		myNode.setVvalue(0);
//		myNode.setPrefSize(400, 800);
		return myNode;
	}

	@Override
	public Node getView() {
		return myNode;
	}
}
