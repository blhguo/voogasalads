package authoring;

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
		
		HBox createHB = makeHBox("Create New Level", "Authoring Environment", createButton);
		HBox loadHB = makeHBox("Load Game for Editing", "Authoring Environment", loadButton);
		HBox playHB = makeHBox("Load Game for Play", "Game Player", playButton);
		myVBox.getChildren().addAll(createHB, loadHB, playHB);
		return myVBox;
	}
	
	private HBox makeHBox(String title, String subtitle, Button button){
		button.getStyleClass().add("button-splash");
		
		VBox vb = new VBox(AuthRes.getInt("Padding"));
		vb.setMaxHeight(30);
		Text label = new Text(title);
		label.getStyleClass().add("button-label");
		Text subLabel = new Text(subtitle);
		subLabel.getStyleClass().add("button-sublabel");
		vb.getChildren().addAll(label, subLabel);
		
		HBox hb = new HBox(AuthRes.getInt("HBPadding"));
		hb.setAlignment(Pos.CENTER_LEFT);
		hb.getChildren().addAll(button, vb);
		return hb;
	}

	@Override
	public void finishScene(GridPane gridpane) {
		Text title = new Text(AuthRes.getString("SplashTitle"));
		title.getStyleClass().add("title");
		gridpane.add(title, 10, 80);
		
		gridpane.add(makeVBox(), 50, 20);
	}

}
