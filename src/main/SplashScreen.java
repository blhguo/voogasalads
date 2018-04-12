package main;

import authoring.AuthoringEnvironment;
import authoring.GUIGridPaneSuper;
import authoring.GameChooserScreen;
import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
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
	
	private VBox makeVBox(){
		VBox myVBox = new VBox(AuthRes.getInt("VBPadding"));		
		Button createButton = ButtonFactory.makeButton(e -> {
			AuthoringEnvironment ae = new AuthoringEnvironment(myStage);
			myStage.getScene().setRoot(ae.test());
			myStage.show();
		});
		Button loadButton = ButtonFactory.makeButton(e -> {
			GameChooserScreen gc = new GameChooserScreen(myStage);
			myStage.setScene(gc.display());
			myStage.show();
		});
		
		Button playButton = ButtonFactory.makeButton(e -> {
			GameChooserScreen gc = new GameChooserScreen(myStage);
			myStage.setScene(gc.display());
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
