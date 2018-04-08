package authoring;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import resources.keys.AuthRes;

public abstract class GUIGridPaneSuper extends GUIBuilder {

	@Override
	public Scene display() {
		GridPane gridpane = new GridPane();
		gridpane.setHgap(AuthRes.getInt("Padding"));
		gridpane.setVgap(AuthRes.getInt("Padding"));
		gridpane.setPadding(new Insets(AuthRes.getInt("Padding")));
		
		Scene myScene = initScene(gridpane);
		
		finishScene(gridpane);
		return myScene;
	}
	
	public abstract void finishScene(GridPane gridpane);

}
