package authoring;

import java.util.List;

import authoring.GUI_Heirarchy.GUINode;
import authoring.controllers.EntityController;
import authoring.right_components.EntityComponent.EntityWrapper;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
 * @author elizabethshulman
 * @author Jennifer Chin
 * @author Liam Pulsifer
 * Canvas class for the middle portion of the Authoring Environment. Displays the current
 * level and all Entities in that level. Allows the user to drag and drop images around
 * the view.
 */
public class Canvas implements GUINode {
	private Pane myInfinitePane;
	private ScrollPane myNode;
	private BorderPane bp;
	private EntityController myController;
	private Color backgroundColor = Color.rgb(179, 179, 179, 0.6);
	private int currLevel;

	/**
	 * Constructor that creates new internal canvas and initializes outer ScrollPane
	 */
	public Canvas() {
		myInfinitePane = initializeInfinitePane(4000, 4000);
		myNode = initializeScrollingPane();
		currLevel = 0;
	}
	
	/**
	 * Sets a default background to a plain color
	 */
	public void setDefaultBackground(){
		myInfinitePane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	/**
	 * Allows the canvas to be updated according to the current EntityWrappers in the list.
	 * @param entityList
	 */
	public void update(List<EntityWrapper> entityList){
		myInfinitePane.getChildren().clear();
		for (EntityWrapper e: entityList){
			if (e.getLevel() == currLevel && !myInfinitePane.getChildren().contains(e.getImageView())){
				myInfinitePane.getChildren().add(e.getImageView());
			}
		}
	}
	
	/**
	 * Allows the canvas the be updated when entities cannot be edited (ie. the user is not on EntityPane, but one of the other right components)
	 * @param entityList
	 */
	public void updateDummies(List<EntityWrapper> entityList){
		myInfinitePane.getChildren().clear();
		entityList.stream().forEach(e -> System.out.println(e.getDummy()));
		for (EntityWrapper e: entityList){
			if (e.getLevel() == currLevel && !myInfinitePane.getChildren().contains(e.getDummy())){
				myInfinitePane.getChildren().add(e.getDummy());
				//System.out.println("ADDED TO CANVAS: " + e.getImageView().toString());
				e.getDummy().setOnMousePressed(a -> {
			         myController.addToEventPaneBox(e);
		        });
			}
		}
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
		myInfinitePane.setOnMousePressed(e -> {
			myController.alertEntityPane(e.getX(), e.getY());
		});
	}
	
	public void stopListen() {
		myInfinitePane.setOnMousePressed(e -> {});
	}
	
	private Pane initializeInfinitePane(double prefX, double prefY) {
		myInfinitePane = new Pane();
		myInfinitePane.setPrefSize(prefX,prefY);
		myInfinitePane.setMaxSize(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
		setDefaultBackground();
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

	/**
	 * GUINode method 
	 */
	@Override
	public Node getView() {
		return myNode;
	}
	
	/**
	 * Sets the current Level of the canvas so that the correct entities are displayed according to level
	 * @param id
	 */
	public void setLevel(int id){
		currLevel = id;
	}
	
	/**
	 * Meant to restrict canvas the horizontal or vertical scrolling (or both). Doesn't work
	 * @param hscroll
	 * @param vscroll
	 */
	public void changeScrolling(boolean hscroll, boolean vscroll){
		if (! hscroll){
			myInfinitePane.setPrefWidth(myNode.getWidth());
			myNode.setHbarPolicy(ScrollBarPolicy.NEVER);
			myNode.setContent(myInfinitePane);
		}
		else {
			myInfinitePane.setPrefWidth(Double.POSITIVE_INFINITY);
			myNode.setHbarPolicy(ScrollBarPolicy.ALWAYS);
			System.out.println();
		}
		if (! vscroll){
			myInfinitePane.setPrefHeight(myNode.getHeight());
		}
		else {
			myInfinitePane.setPrefHeight(Double.POSITIVE_INFINITY);
		}
	}
}
