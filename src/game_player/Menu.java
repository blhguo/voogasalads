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
	private VBox settingsMenu;
	private Button gameSelectionButton;
	private Button settingsButton;
	private Stage keyPrefStage;
	private Stage gameSelectionStage;
	private Stage settingsStage;
	private Slider volumeSlider;
	private Slider brightnessSlider;
	private Label volumeLabel;
	private Label brightnessLabel;
	private DataManager dataManager;
	
	public Menu(DataManager data, PulldownFactory pdf) {
		pane = new HBox(20);
		pane.setAlignment(Pos.CENTER);
		dataManager = data;
		pullDownFactory = pdf;
		makePullDownMenus();
		makeGameSelectionMenu();
		makeSettingsMenu();
		makeSettingsStage();
		KeyPrefMenu kpm = new KeyPrefMenu(dataManager,pane);
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
	/**
	 * Method to make settings button that when clicked calls the showSettingsMenu method
	 * 
	 */
	private void makeSettingsMenu() {
		settingsButton = new Button("Settings");
		settingsButton.getStyleClass().add("button-nav");
		settingsButton.setOnAction(click->{showSettingsMenu();});
		pane.getChildren().add(settingsButton);
		settingsMenu = new VBox(50);
		settingsMenu.setPrefWidth(300);

		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		settingsMenu.setBackground(new Background(back));
		settingsButton.setOnAction(click->{showSettingsMenu();});	
	}
	private void makeSettingsStage() {
		settingsStage = new Stage();
		Scene scene = new Scene(settingsMenu);
		scene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());
		brightnessSlider = new Slider();
		brightnessSlider.getStyleClass().add("slider");
		brightnessSlider.setMin(-1);
		brightnessSlider.setMax(1);
		

		volumeSlider = new Slider();
		volumeSlider.getStyleClass().add("slider");
		volumeSlider.setMin(0);
		volumeSlider.setMax(1);
		
		volumeLabel = new Label("Change Volume:");
		brightnessLabel = new Label("Change Brightness:");
		volumeLabel.getStyleClass().add("button-nav");
		brightnessLabel.getStyleClass().add("button-nav");

		settingsMenu.getChildren().add(brightnessLabel);
		settingsMenu.getChildren().add(brightnessSlider);
		settingsMenu.getChildren().add(volumeLabel);
		settingsMenu.getChildren().add(volumeSlider);
		settingsStage.setScene(scene);
	}
	
	
	/**
	 * getter method for the Brightness Slider on the Settings Stage
	 * 
	 */
	public Slider getBrightnessSlider() {
		return brightnessSlider;
	}
	/**
	 * getter method for the Volume Slider on the Settings Stage
	 * 
	 */
	public Slider getVolumeSlider() {
		return volumeSlider;
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
	 * method to show settings Menu
	 * 
	 */
	public void showSettingsMenu() {
		settingsStage.show();
		
	}	

}
