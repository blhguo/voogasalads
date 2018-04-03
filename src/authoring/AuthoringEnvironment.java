package authoring;

import authoring.right_components.DefaultPane;
import authoring.right_components.EntityComponent;
import authoring.right_components.EventComponent;
import authoring.right_components.LevelComponent;
import authoring.right_components.StoryboardComponent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import observables.Listener;
import resources.keys.AuthRes;

public class AuthoringEnvironment implements GUIBuilder, Listener {

	private DefaultPane dp;
	private EntityComponent entity;
	private EventComponent event;
	private LevelComponent level;
	private StoryboardComponent story;
	private BorderPane bp;
	
	
	public AuthoringEnvironment(){
		//instantiate leftPane, rightPane, Canvas
		
		dp = new DefaultPane();
		entity = new EntityComponent();
		event = new EventComponent();
		level = new LevelComponent();
		story = new StoryboardComponent();
	}
	
	@Override
	public Scene display() {
		bp = new BorderPane();
		
		//set leftPane
		
		update(""); //calls default setting for right pane
		Scene scene = new Scene(bp, AuthRes.getInt("EnvironmentX"), AuthRes.getInt("EnvironmentY"));
		return scene;
		
	}
	
	private void dragAndDrop(){
		
	}

	@Override
	public void update(String state) { //more concise/less repetitive way to write this?
		switch(state) {
			case "entity":
			        bp.setRight(entity);
			case "event": ;
			        bp.setRight(event);;
			case "level": ;
			        bp.setRight(level);;
			case "story": ;
			        bp.setRight(story);
			default: 
					bp.setRight(dp);
		}
	}

}
