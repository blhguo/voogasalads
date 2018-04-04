package authoring;

import java.io.File;
import java.net.URL;

import authoring.utilities.ButtonFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class SplashScreen implements GUIBuilder{

	private Stage myStage;
	
	public SplashScreen(Stage stage){
		myStage = stage;
	}
	
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

		Text title = new Text(AuthRes.getString("SplashTitle"));
		title.getStyleClass().add("title");
		gridpane.add(title, 10, 80);
		
		gridpane.add(makeVBox(), 50, 20);
		return scene;
	}
	
	private VBox makeVBox(){
		VBox myVBox = new VBox(AuthRes.getInt("VBPadding"));
		
		//need to change scene on stage, need stage
		Button createButton = new Button("+");
		createButton.setOnAction(e -> {
			AuthoringEnvironment ae = new AuthoringEnvironment();
			myStage.setScene(ae.display());
			myStage.show();
		});
		
		HBox createHB = ButtonFactory.makeHBox("Create New Level", "Authoring Environment", createButton);
		HBox loadHB = ButtonFactory.makeHBox("Load Game for Editing", "Authoring Environment");
		HBox playHB = ButtonFactory.makeHBox("Load Game for Play", "Game Player");
		myVBox.getChildren().addAll(createHB, loadHB, playHB);
		return myVBox;
	}
}
