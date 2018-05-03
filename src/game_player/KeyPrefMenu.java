package game_player;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
/**
 * 
 * @author Brandon Dalla Rosa
 *
 * Manages the user key preferences through various maps and listeners.
 */
public class KeyPrefMenu {
	private VBox keyPrefMenu;
	private Button keyPrefButton;
	private DataManager dataManager;
	private Stage keyPrefStage;
	private Button currentPrefButton;
	private String currentPrefString;
	
	/**
	 * Method to make the keyPref Stage with keyPref options.
	 * 
	 */
	public KeyPrefMenu(DataManager data, Pane root) {
		ImageView keyboardImageView = new ImageView( getClass().getResource( "/game_player_resources/keyboard.png").toExternalForm());
		keyboardImageView.setFitHeight(30);
		keyboardImageView.setFitWidth(30);
		currentPrefButton = new Button();
		dataManager = data;
		keyPrefMenu = new VBox(25);
		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		keyPrefMenu.setBackground(new Background(back));
		keyPrefButton = new Button("", keyboardImageView);
		keyPrefButton.setOnAction(click->{showPrefMenu();});
		keyPrefButton.getStyleClass().add("button-nav");
		root.getChildren().add(keyPrefButton);
		initKeyPrefMenu();
		keyPrefStage = new Stage();
		Scene scene = new Scene(keyPrefMenu);
		scene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());
		scene.setOnKeyPressed(click->checkForInput(click.getCode()));
		keyPrefStage.setScene(scene);
		keyPrefStage.initOwner(dataManager.getStage());
	}
	/**
	 * Method to populate the keypref stage.
	 */
	private void initKeyPrefMenu() {
		ArrayList<String> inputs = (ArrayList<String>) dataManager.getInputCommands();
		for(String s : inputs) {
			HBox toAdd = new HBox(10);
			toAdd.setAlignment(Pos.CENTER);
			Label label = new Label(s);
			label.getStyleClass().add("text-keypref");
			Button button = new Button(dataManager.getKeyCode(s).toString());
			button.getStyleClass().add("button-keypref");
			button.setOnAction(click->{setPref(button,s);});
			toAdd.getChildren().add(label);
			toAdd.getChildren().add(button);
			keyPrefMenu.getChildren().add(toAdd);
		}
	}
	/**
	 * Method called to check for the current key input.
	 * @param code
	 */
	private void checkForInput(KeyCode code) {
		currentPrefButton.getStyleClass().add("button-keypref");

		dataManager.setKey(currentPrefString, code);
		currentPrefButton.setText(""+dataManager.getKeyCode(currentPrefString));
		setPref(new Button(),"");
	}
	/**
	 * Method to set the current button and input.
	 * @param button
	 * @param string
	 */
	private void setPref(Button button,  String string) {
		currentPrefButton = button;
		currentPrefString = string;
	}
	/**
	 * Method to show the preference menu.
	 */
	private void showPrefMenu() {
		keyPrefStage.show();
	}
}
