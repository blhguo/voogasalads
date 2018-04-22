package game_player;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KeyPrefMenu {
	private VBox keyPrefMenu;
	private Button keyPrefButton;
	private DataManager dataManager;
	private Stage keyPrefStage;
	private Button currentPrefButton;
	private String currentPrefString;
	private KeyCode currentKey;
	
	/**
	 * Method to make the keyPref Stage with keyPref options
	 * 
	 */
	public KeyPrefMenu(DataManager data, Pane root) {
		currentKey = KeyCode.ENTER;
		currentPrefButton = new Button();
		dataManager = data;
		keyPrefMenu = new VBox(25);
		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		keyPrefMenu.setBackground(new Background(back));
		keyPrefButton = new Button("Key Prefs");
		keyPrefButton.setOnAction(click->{showPrefMenu();});
		keyPrefButton.setPrefSize(160, 20);
		keyPrefButton.getStyleClass().add("button-nav");
		root.getChildren().add(keyPrefButton);
		initKeyPrefMenu();
		keyPrefStage = new Stage();
		Scene scene = new Scene(keyPrefMenu);
		scene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());
		scene.setOnKeyPressed(click->checkForInput(click.getCode()));
		keyPrefStage.setScene(scene);
	}
	
	private void initKeyPrefMenu() {
		ArrayList<String> inputs = (ArrayList<String>) dataManager.getInputCommands();
		for(String s : inputs) {
			HBox toAdd = new HBox(10);
			toAdd.setAlignment(Pos.CENTER);
			Label label = new Label(s);
			label.getStyleClass().add("text-keypref");
			Button button = new Button("ENTER");
			button.getStyleClass().add("button-keypref");
			button.setOnAction(click->{setPref(button,s);});
			toAdd.getChildren().add(label);
			toAdd.getChildren().add(button);
			keyPrefMenu.getChildren().add(toAdd);
		}
	}
	
	private void checkForInput(KeyCode code) {
		currentKey = code;
		currentPrefButton.getStyleClass().add("button-keypref");

		currentPrefButton.setText(""+currentKey);
		dataManager.setKey(currentPrefString, code);
	}
	
	private void setPref(Button button,  String string) {
		currentPrefButton = button;
		currentPrefString = string;
	}
	
	private void showPrefMenu() {
		keyPrefStage.show();
	}
}
