package authoring;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import resources.keys.AuthRes;

public abstract class GUIGridPaneSuper implements GUIBuilder {

	@Override
	public Scene display() {
		GridPane gridpane = new GridPane();
		gridpane.setHgap(AuthRes.getInt("Padding"));
		gridpane.setVgap(AuthRes.getInt("Padding"));
		gridpane.setPadding(new Insets(AuthRes.getInt("Padding")));
		
		Scene scene = new Scene(gridpane, AuthRes.getInt("EnvironmentX"), AuthRes.getInt("EnvironmentY"));
		scene.getStylesheets().add(getClass().getResource("vooga.css").toString());
		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		gridpane.setBackground(new Background(back));
		
		finishScene(gridpane);
		return scene;
	}
	
	public abstract void finishScene(GridPane gridpane);

}
