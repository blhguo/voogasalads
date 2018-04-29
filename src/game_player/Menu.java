package game_player;

import java.util.ArrayList;

import authoring.loadingviews.PlayerLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author Brandon Dalla Rosa, Dana Park
 *Class that represents all Menu items contained in HBox on top of the screen
 */

public class Menu {
	
	private HBox pane;
	private PulldownFactory pullDownFactory;
	private ButtonMaker buttonMaker;
	private DataManager dataManager;
	private SettingsMenu settings;
	private GameSelectionMenu gameMenu;
	private KeyPrefMenu kpm;
	
	public Menu(DataManager data, PulldownFactory pdf) {
		pane = new HBox(35);
		pane.setAlignment(Pos.TOP_CENTER);
		dataManager = data;
		pullDownFactory = pdf;
		makeButtons();
		settings = new SettingsMenu();
		settings.makeSettingsMenu(pane);
		settings.makeSettingsStage();
		gameMenu = new GameSelectionMenu();
		gameMenu.makeGameSelectionMenu(pane,pdf);
		kpm = new KeyPrefMenu(dataManager,pane);
		

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

	private void makeButtons() {
		buttonMaker = new ButtonMaker();
//		pane.getChildren().add(buttonMaker.pausePlayButton());
//		pane.getChildren().add(buttonMaker.replayButton());
//		pane.getChildren().add(buttonMaker.saveButton());
//		pane.getChildren().add(buttonMaker.slowControlButton());
//		pane.getChildren().add(buttonMaker.speedControlButton());
		for (int i = 0; i < buttonMaker.makeMenuButton().size(); i++) {
			pane.getChildren().add(buttonMaker.makeMenuButton().get(i));
		}

	}
	public void setPlayerView(PlayerView pv) {
		buttonMaker.setPlayerView(pv);
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
		return settings.getVolumeSlider();
	}
	

}
