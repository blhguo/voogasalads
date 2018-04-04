package authoring;

import authoring.right_components.EntityComponent;
import authoring.right_components.EventComponent;
import authoring.right_components.LevelComponent;
import authoring.right_components.StoryboardComponent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import observables.Listener;
import resources.keys.AuthRes;

public class AuthoringEnvironment implements GUIBuilder, Listener {

	private NavigationPane np;
	
	private EntityComponent entity;
	private EventComponent event;
	private LevelComponent level;
	private StoryboardComponent story;
	private BorderPane bp;
	
	
	public AuthoringEnvironment(){
		//instantiate leftPane, rightPane, Canvas
				
		entity = new EntityComponent();
		event = new EventComponent();
		level = new LevelComponent();
		story = new StoryboardComponent();
		
		np = new NavigationPane();
		np.addListener(this);
	}
	
	@Override
	public Scene display() {
		bp = new BorderPane();
		
		//set leftPane
		
		update(""); //calls default setting for right pane
		bp.setLeft(np);
		Scene scene = new Scene(bp, AuthRes.getInt("EnvironmentX"), AuthRes.getInt("EnvironmentY"));
		return scene;
		
	}
	
	private void dragAndDrop(){
		
	}

	@Override
	public void update(String state) { //more concise/less repetitive way to write this?
		switch(state) {
			case "Entity Creator":
			        bp.setRight(entity.getView());
			        break;
			case "Actions and Events":
					System.out.println("actions clicked");
					bp.setRight(null);
			        bp.setRight(event.getView());
			        break;
			case "Level Preferences": ;
			        bp.setRight(level.getView());
			        break;
			case "Storyboard": ;
			        bp.setRight(story.getView());
			        break;
			default: 
					break;
		}
	}

}
