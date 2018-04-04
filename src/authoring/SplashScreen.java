package authoring;

import authoring.utilities.ButtonFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class SplashScreen extends GUIGridPaneSuper{

	private Stage myStage;
	
	public SplashScreen(Stage stage){
		myStage = stage;
		Font myFont = Font.loadFont(getClass().getResourceAsStream("resources/Segoe fonts v1710/segoeuil.ttf"), 10);
	}
	
	private VBox makeVBox(){
		VBox myVBox = new VBox(AuthRes.getInt("VBPadding"));
		
		Button createButton = new Button("+");
		createButton.setOnAction(e -> {
			AuthoringEnvironment ae = new AuthoringEnvironment();
			myStage.setScene(ae.display());
			myStage.show();
		});
		Button loadButton = new Button("+");
		loadButton.setOnAction(e -> {
			GameChooserScreen gc = new GameChooserScreen(myStage);
			myStage.setScene(gc.display());
			myStage.show();
		});
		
		Button playButton = new Button("+");
		playButton.setOnAction(e -> {
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
