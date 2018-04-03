package authoring;

import authoring.right_components.DefaultPane;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import observables.Listener;
import resources.keys.AuthRes;

public class AuthoringEnvironment implements GUIBuilder, Listener {

	private DefaultPane dp;
	private BorderPane bp;
	
	public AuthoringEnvironment(){
		//instantiate leftPane, rightPane, Canvas,
		//EntityComponent, EventComponent, LevelComponent, StoryBoard
		dp = new DefaultPane();
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
	public void update(String state) {
		switch(state) {
			case "entity":
			        break;
			case "event": ;
			        break;
			case "level": ;
			        break;
			case "story": ;
			        break;
			default: bp.setRight(dp);
					break;
		}
	}

}
