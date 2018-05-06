package authoring;

import authoring.GUI_Heirarchy.GUIBuilder;
import authoring.controllers.EntityController;
import authoring.controllers.LevelController;
import authoring.controllers.Loader;
import authoring.controllers.MetaController;
import authoring.controllers.PaneController;
import authoring.right_components.BasePane;
import authoring.right_components.EventPane;
import authoring.right_components.LevelPane;
import authoring.right_components.StoryBoardPane;
import authoring.right_components.EntityComponent.EntityPane;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import observables.Listener;
import resources.keys.AuthRes;

/**
 * @author Liam Pulsifer
 * @author Jennifer Chin
 * @author Elizabeth Shulman
 *
 * Main authoring environment class. Initializes all parts of the AuthoringEnvironment
 * and allows for communication between the different parts.
 */

public class AuthoringEnvironment extends GUIBuilder implements Listener {

	private Stage stage;

	private NavigationPane np;
	private EntityController controller;
	private PaneController pcontroller;
	private LevelController lcontroller;
	private MetaController mcontroller;
	private Loader loader;
	private BasePane base;
	private EntityPane entity;
	private EventPane event;
	private LevelPane level;
	private StoryBoardPane story;
	private BorderPane bp;
	private Canvas canvas;	
	
	/**
	 * Constructor for the Authoring Environment. Takes in a stage and a splash screen
	 * in order to change the root of the scene of the stage as the view changes.
	 * Constructor initializes various parts of the Authoring Environment GUI - all the
	 * different menus, canvas, and controllers necessary for the Authoring Environment
	 * to run
	 * @param stage
	 */
	
	public AuthoringEnvironment(Stage stage){
		this.stage = stage;
		base = new BasePane();
		entity = new EntityPane(stage);
		event = new EventPane(stage);
		level = new LevelPane(stage);
		story = new StoryBoardPane();
		np = new NavigationPane(stage);
		
		bp = new BorderPane();
		canvas = new Canvas();

		controller = new EntityController(entity, canvas, event);
		pcontroller = new PaneController(canvas);
		lcontroller = new LevelController();
		mcontroller = new MetaController(lcontroller);
		loader = new Loader(level, story, entity);
		
		canvas.setController(controller);
		event.setController(controller);
		event.setLevelController(lcontroller);
		entity.setController(controller);
		level.setController(pcontroller);
		level.setLevelController(lcontroller);
		controller.setLevelController(lcontroller);
		story.setLevelController(lcontroller);
		story.setMetaController(mcontroller);
		story.setPaneController(pcontroller);
		pcontroller.setEntityController(controller);
		loader.setLevelController(lcontroller);
		loader.setMetaController(mcontroller);
		np.addListener(this);
		np.addMetaController(mcontroller);
		np.setLoader(loader);
	}

	/**
	 * Abstract method inherited from the GUIBuilder super class. Returns a Pane that
	 * becomes the root of the scene. 
	 * @return Pane
	 */
	@Override
	public Pane display() {

		//Build BorderPane by setting right, center, and left
		update(""); //calls default setting for right pane
		bp.setLeft(np.getView());
		bp.setCenter(canvas.getView());
		BorderPane.setMargin(canvas.getView(), new Insets(AuthRes.getInt("Margin")));
		
		//Build StackPane to integrate toolbar
		StackPane sp = new Toolbar(stage).integrateToolbar(bp);
		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		sp.setBackground(new Background(back));
//		sp.getChildren().add(canvas.getView());		
		return sp;

	}

	/**
	 * Updates the right panel according the user selection. Called by Navigation Pane
	 * to determine which right panel to display
	 * @param state
	 */
	@Override
	public void update(String state) { //cleaner way to write this?
		switch(state) {
			case "Entity Creator":
				canvas.listen();
				controller.updateCanvas();
				bp.setRight(entity.getView());
				break;
			case "Events":
				canvas.stopListen();
				controller.updateDummies();
				bp.setRight(event.getView());
				break;
			case "Level Preferences": ;
				controller.updateDummies();
				bp.setRight(level.getView());
				break;
			case "Storyboard": ;
				controller.updateDummies();
				bp.setRight(story.getView());
				break;
			default:
				bp.setRight(base.getView());
				break;

		}
	}
	
	/**
	 * Returns loader of the AuthoringEnvironment. Used by AuthoringLoader to load game and create appropriate thumbnail buttons
	 * @return
	 */
	public Loader getLoader(){
		return loader;
	}

}
