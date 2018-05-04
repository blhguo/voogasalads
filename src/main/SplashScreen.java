package main;

import authoring.AuthoringEnvironment;
import authoring.GUI_Heirarchy.GUIGridPaneSuper;
import authoring.loadingviews.AuthoringLoader;
import frontend_utilities.ButtonFactory;
import game_player.PlayerMain;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
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
			AuthoringEnvironment ae = new AuthoringEnvironment(myStage);
			
			myStage.getScene().setRoot(ae.display());
			myStage.show();
		});
		Button loadButton = ButtonFactory.makeButton(e -> {
			AuthoringLoader chooseauthoring = new AuthoringLoader(myStage);
			myStage.getScene().setRoot(chooseauthoring.display());
			myStage.show();
		});
		
		Button playButton = ButtonFactory.makeButton(e -> {
			new PlayerMain().start(myStage);
			myStage.show();
		});
				
		HBox createHB = ButtonFactory.makeHBox("Create New Game", "Authoring Environment", createButton);
		HBox loadHB = ButtonFactory.makeHBox("Load Game for Editing", "Authoring Environment", loadButton);
		HBox playHB = ButtonFactory.makeHBox("Load Game for Play", "Game Player", playButton);
		myVBox.getChildren().addAll(createHB, loadHB, playHB);
		return myVBox;
	}

	@Override
	public Pane finishScene(GridPane gridpane) {

		Text title = new Text(AuthRes.getString("SplashTitle"));
		title.getStyleClass().add("title");
		VBox vb = makeVBox();
		gridpane.getChildren().addAll(title, vb);
		
		double width = Screen.getPrimary().getVisualBounds().getWidth();
		int numCols = (int) width / AuthRes.getInt("Padding");
		
		double height = Screen.getPrimary().getVisualBounds().getHeight();
		int numRows = (int) height / AuthRes.getInt("Padding");
		
		GridPane.setConstraints(title, numCols / 20, numRows * 3 / 5);
		GridPane.setConstraints(vb, numCols * 7 / 24, numRows / 5);
		return gridpane;
	}
}