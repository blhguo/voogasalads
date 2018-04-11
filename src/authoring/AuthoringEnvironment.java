package authoring;

import authoring.controllers.EntityController;
import authoring.controllers.LevelController;
import authoring.controllers.PaneController;
import authoring.right_components.BasePane;
import authoring.right_components.EventPane;
import authoring.right_components.LevelPane;
import authoring.right_components.StoryBoardPane;
import authoring.right_components.EntityComponent.EntityPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import observables.Listener;
import resources.keys.AuthRes;

/**
 * @author Liam Pulsifer
 * @author Jenny Chin
 * @author Elizabeth Shulman
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
	
	
	public AuthoringEnvironment(Stage stage){
		this.stage = stage;
		
		base = new BasePane();
		entity = new EntityPane();
		event = new EventPane();
		level = new LevelPane();
		story = new StoryBoardPane();
		np = new NavigationPane(stage);
		
		canvas = new Canvas();
		
		EntityController controller = new EntityController(entity, canvas);
		PaneController pcontroller = new PaneController(level, canvas);
		LevelController lcontroller = new LevelController();
		
		canvas.setController(controller);
		entity.setController(controller);
		level.setController(pcontroller);
		level.setLevelController(lcontroller);
		controller.setLevelController(lcontroller);
		np.addListener(this);
	}
	
	@Override
	public Scene display() {
		
		//Build BorderPane by setting right, center, and left
		bp = new BorderPane();
		update(""); //calls default setting for right pane
		bp.setLeft(np.getView());
		Pane canvasView = canvas.getView();
		bp.setCenter(canvasView);
		BorderPane.setMargin(canvasView, new Insets(AuthRes.getInt("Margin")));

		//Build StackPane to overlay ToolBar on top
		Pane t = new Toolbar(stage).getView();
		t.setPickOnBounds(false);
		bp.setPickOnBounds(false);
//		StackPane sp = new StackPane(t, bp);
		StackPane sp = new StackPane(bp, t);
		sp.setPickOnBounds(false);

		//Build scene from StackPane
		Scene scene = initScene(sp);
		scene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());
		return scene;
		
	}

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
