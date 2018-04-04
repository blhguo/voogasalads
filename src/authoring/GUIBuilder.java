package authoring;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import resources.keys.AuthRes;

public abstract class GUIBuilder {
	
	public abstract Scene display();
	
	public Scene initScene(Pane pane){
		Scene scene = new Scene(pane, AuthRes.getInt("EnvironmentX"), AuthRes.getInt("EnvironmentY"));
		scene.getStylesheets().add(getClass().getResource("vooga.css").toString());
		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		pane.setBackground(new Background(back));
		return scene;
	}
}
