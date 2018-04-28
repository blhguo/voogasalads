package authoring;

import authoring.GUI_Heirarchy.GUIBuilder;
import authoring.controllers.EntityController;
import authoring.controllers.LevelController;
import authoring.controllers.PaneController;
import authoring.right_components.BasePane;
import authoring.right_components.EntityComponent.EntityPane;
import authoring.right_components.EventPane;
import authoring.right_components.LevelPane;
import authoring.right_components.StoryBoardPane;
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
import main.SplashScreen;
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

	private BasePane base;
	private EntityPane entity;
	private EventPane event;
	private LevelPane level;
	private StoryBoardPane story;
	private BorderPane bp;
	private Canvas canvas;
	private SplashScreen splash;

	/**
	 * Constructor for the Authoring Environment. Takes in a stage and a splash screen
	 * in order to change the root of the scene of the stage as the view changes.
	 * Constructor initializes various parts of the Authoring Environment GUI - all the
	 * different menus, canvas, and controllers necessary for the Authoring Environment
	 * to run
	 * @param stage
	 * @param ss
	 */

	public AuthoringEnvironment(Stage stage, SplashScreen ss){
		this.stage = stage;
		splash = ss;
		base = new BasePane();
		entity = new EntityPane();
		event = new EventPane();
		level = new LevelPane(stage);
		story = new StoryBoardPane();
		np = new NavigationPane(stage);

		canvas = new Canvas();

		EntityController controller = new EntityController(entity, canvas);
		PaneController pcontroller = new PaneController(level, canvas);
		LevelController lcontroller = new LevelController(pcontroller);
		
		canvas.setController(controller);
		entity.setController(controller);
		level.setController(pcontroller);
		level.setLevelController(lcontroller);
		controller.setLevelController(lcontroller);
		story.setLevelController(lcontroller);
		np.addListener(this);
		np.addLevelController(lcontroller);
	}

	/**
	 * Abstract method inherited from the GUIBuilder super class. Returns a Pane that
	 * becomes the root of the scene. 
	 * @return Pane
	 */

	@Override
	public Pane display() {

		//Build BorderPane by setting right, center, and left
		bp = new BorderPane();
		update(""); //calls default setting for right pane
		bp.setLeft(np.getView());
		Pane canvasView = canvas.getView();
		bp.setCenter(canvasView);
		BorderPane.setMargin(canvasView, new Insets(AuthRes.getInt("Margin")));

		//Build StackPane to overlay ToolBar on top
		Pane t = new Toolbar(stage, splash).getView();
		t.setPickOnBounds(false);
		bp.setPickOnBounds(false);
		StackPane sp = new StackPane(t, bp);
		//StackPane sp = new StackPane(bp); // t);
		sp.setPickOnBounds(false);

		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		sp.setBackground(new Background(back));
		//Build scene from StackPane
		//Scene scene = initScene(sp);
		//scene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());

		return sp;

	}

	/**
	 * Updates the right panel according the user selection. Called by Navigation Pane
	 * to determine which right panel to display
	 * @param state
	 */
	@Override
	public void update(String state) { //more concise/less repetitive way to write this?
		switch(state) {
			case "Entity Creator":
				bp.setRight(entity.getView());
				break;
			case "Actions and Events":
				bp.setRight(event.getView());
				break;
			case "Level Preferences": ;
				bp.setRight(level.getView());
				break;
			case "Storyboard": ;
				bp.setRight(story.getView());
				break;
			default:
				bp.setRight(base.getView());
				break;
		}
	}

}
