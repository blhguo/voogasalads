package authoring;

import authoring.right_components.BaseComponent;
import authoring.right_components.EntityComponent.EntityComponent;
import authoring.right_components.EventComponent;
import authoring.right_components.LevelComponent;
import authoring.right_components.StoryboardComponent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import observables.Listener;
import resources.keys.AuthRes;

/**
 * @author Liam Pulsifer
 * @author Jenny Chin
 * @author Elizabeth Schulman
 */

public class AuthoringEnvironment extends GUIBuilder implements Listener {

	private NavigationPane np;
	
	private BaseComponent base;
	private EntityComponent entity;
	private EventComponent event;
	private LevelComponent level;
	private StoryboardComponent story;
	private BorderPane bp;
	private Canvas canvas;
	
	
	public AuthoringEnvironment(){
		//instantiate leftPane, rightPane, Canvas
			
		base = new BaseComponent();
		entity = new EntityComponent();
		event = new EventComponent();
		level = new LevelComponent();
		story = new StoryboardComponent();
		canvas = new Canvas(AuthRes.getInt("canvassize"));
		np = new NavigationPane();
		np.addListener(this);
	}
	
	@Override
	public Scene display() {
		bp = new BorderPane();
		Scene scene = initScene(bp);
		
		//set leftPane
		
		update(""); //calls default setting for right pane
		bp.setLeft(np);
		bp.setCenter(canvas.getView());
		bp.setTop(new Rectangle(1200, 50, Color.GRAY));
		//Scene scene = new Scene(bp, AuthRes.getInt("EnvironmentX"), AuthRes.getInt("EnvironmentY"));
		scene.getStylesheets().add(getClass().getResource("vooga.css").toString());
		//Scene scene = new Scene(bp, AuthRes.getInt("EnvironmentX"), AuthRes.getInt("EnvironmentY"));
		return scene;
		
	}
//	
//	private void dragAndDrop(){
//		
//	}

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
