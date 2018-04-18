package main;

import authoring.AuthoringEnvironment;
import authoring.GUIGridPaneSuper;
import authoring.GameChooserScreen;
import authoring.utilities.ButtonFactory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class SplashScreen extends GUIGridPaneSuper{

	private Stage myStage;
	
	public SplashScreen(Stage stage){
		myStage = stage;
	}
	
	public Scene getScene(){
		GridPane gridpane = new GridPane();
		gridpane.setHgap(AuthRes.getInt("Padding"));
		gridpane.setVgap(AuthRes.getInt("Padding"));
		gridpane.setPadding(new Insets(AuthRes.getInt("Padding")));
		Scene myScene = initScene(gridpane);
		
		finishScene(gridpane);
		return myScene;
	}
	
	private VBox makeVBox(){
		VBox myVBox = new VBox(AuthRes.getInt("VBPadding"));		
		Button createButton = ButtonFactory.makeButton(e -> {
			AuthoringEnvironment ae = new AuthoringEnvironment(myStage, this);
			
			myStage.getScene().setRoot(ae.display());
			myStage.show();
		});
		Button loadButton = ButtonFactory.makeButton(e -> {
			GameChooserScreen gc = new GameChooserScreen(myStage);
			myStage.getScene().setRoot(gc.display());
			myStage.show();
		});
		
		Button playButton = ButtonFactory.makeButton(e -> {
			GameChooserScreen gc = new GameChooserScreen(myStage);
			myStage.getScene().setRoot(gc.display());
			myStage.show();
		});
				
		HBox createHB = ButtonFactory.makeHBox("Create New Level", "Authoring Environment", createButton);
		HBox loadHB = ButtonFactory.makeHBox("Load Game for Editing", "Authoring Environment", loadButton);
		HBox playHB = ButtonFactory.makeHBox("Load Game for Play", "Game Player", playButton);
		myVBox.getChildren().addAll(createHB, loadHB, playHB);
		return myVBox;
	}
	
	@Override
	public void finishScene(GridPane gridpane) {
		Text title = new Text(AuthRes.getString("SplashTitle"));
		title.getStyleClass().add("title");
		gridpane.add(title, 10, 80);
		gridpane.add(makeVBox(), 50, 20);
	}

}
