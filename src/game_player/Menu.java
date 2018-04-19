package game_player;

import java.util.ArrayList;
import authoring.GameChooserScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
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

/**
 * 
 * @author Brandon Dalla Rosa, Dana Park
 *Class that represents all Menu items contained in HBox on top of the screen
 */

public class Menu {
	
	private HBox pane;
	private PulldownFactory pullDownFactory;
	private VBox keyPrefMenu;
	private Button keyPrefButton;
	private Button gameSelectionButton;
	private Stage keyPrefStage;
	private Stage gameSelectionStage;
	private DataManager dataManager;
	private KeyCode currentKey;
	private Button currentPrefButton;	
	private String currentPrefString;
	private SettingsMenu settings;
	public Menu(DataManager data, PulldownFactory pdf) {
		pane = new HBox(20);
		pane.setAlignment(Pos.CENTER);
		dataManager = data;
		pullDownFactory = pdf;
		makePullDownMenus();
		makeKeyPrefMenu();
		settings = new SettingsMenu();
		makeGameSelectionMenu();
		settings.makeSettingsMenu(pane);
		settings.makeSettingsStage();
	}
	/**
	 * Method to add the menu into the VBox for the View Manager
	 * 
	 * @param root
	 */
	public void addMenu(Pane root) {
		root.getChildren().add(pane);
	}
	
    /**
	 * Method to add ComboBoxes from PulldownFactory to Menu
	 * 
	 */
	private void makePullDownMenus() {
		
		pane.getChildren().add(pullDownFactory.getSpeedBox());
		pane.getChildren().add(pullDownFactory.getStatusBox());
		pane.getChildren().add(pullDownFactory.getSaveLoadBox());
		
	}
	
	/**
	 * Method to make gameSelectionButton that when clicked calls the showGameSelectionMenu method
	 * 
	 */
	private void makeGameSelectionMenu() {
		gameSelectionButton = new Button("Game Selection");
		gameSelectionButton.getStyleClass().add("button-nav");
		gameSelectionButton.setOnAction(click->{showGameSelectionMenu();});
		pane.getChildren().add(gameSelectionButton);
		
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
	
	private void setPref(Button button,  String string) {
		currentPrefButton = button;
		currentPrefString = string;
	}
	private void showPrefMenu() {
		keyPrefStage.show();
	}
	/**
	 * getter method for the Brightness Slider on the Settings Stage
	 * 
	 */
	public Slider getBrightnessSlider() {
		return settings.getBrightnessSlider();
	}
	/**
	 * getter method for the Volume Slider on the Settings Stage
	 * 
	 */
	public Slider getVolumeSlider() {
		return settings.getBrightnessSlider();
	}
	
	/**
	 * method to show new Stage when gameSelectionButton is pressed
	 * 
	 */
	public void showGameSelectionMenu() {
		//TODO Make this choose game to play, not edit
		gameSelectionStage = new Stage();
		GameChooserScreen gc = new GameChooserScreen(gameSelectionStage);
		//gameSelectionStage.setScene(gc.display());
		gameSelectionStage.show();
	}
	

   /**
	 * method to set keys to their preferences in the keyPrefStage
	 * 
	 */
	public void checkForInput(KeyCode code) {
		currentKey = code;
		currentPrefButton.getStyleClass().add("button-keypref");

		currentPrefButton.setText(""+currentKey);
		dataManager.setKey(currentPrefString, code);
	}
	

}
