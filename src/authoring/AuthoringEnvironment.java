package authoring;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import resources.keys.AuthRes;

public class AuthoringEnvironment implements GUIBuilder {

	public AuthoringEnvironment(){
		//instantiate leftPane, rightPane, Canvas,
		//EntityComponent, EventComponent, LevelComponent, StoryBoard
	}
	
	@Override
	public Scene display() {
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, AuthRes.getInt("EnvironmentX"), AuthRes.getInt("EnvironmentY"));
		//set leftPane
		//set rightPane
		
		return scene;
	}
	
	private void dragAndDrop(){
		
	}

}
