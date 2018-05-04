package game_player;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Brandon Dalla Rosa, Dana Park
 *
 *
 * Creates the settings menu and all desired functions within.
 */
public class SettingsMenu {
	
	private Button settingsButton;
	private VBox settingsMenu;
	private Stage settingsStage;
	private Slider volumeSlider;
	private Slider brightnessSlider;
	private Label volumeLabel;
	private Label brightnessLabel;
	private Stage gameStage;
	/**
	 * Constructor for the settings menu.
	 * @param stage
	 */
	public SettingsMenu(Stage stage) {
		brightnessSlider = makeBrightnessSlider();
		volumeSlider = makeVolumeSlider();
		gameStage = stage;
	}
	/**
	 * Initializes the settings menu in the desired location.
	 * @param pane
	 */
	protected void makeSettingsMenu(HBox pane) {
		ImageView settingsImageView = new ImageView( getClass().getResource( "/game_player_resources/settings.png").toExternalForm());
		settingsImageView.setFitHeight(30);
		settingsImageView.setFitWidth(30);
		settingsButton = new Button("", settingsImageView);
		settingsButton.getStyleClass().add("button-nav");
		pane.getChildren().add(settingsButton);
		settingsMenu = new VBox(50);
		settingsMenu.setPrefWidth(300);
		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		settingsMenu.setBackground(new Background(back));
		settingsButton.setOnAction(click->{showSettingsMenu();});	
	}
	/**
	 * Creates the stage for the menu.
	 */
	protected void makeSettingsStage() {
		settingsStage = new Stage();
		settingsStage.initOwner(gameStage);
		Scene scene = new Scene(settingsMenu);
		scene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());
		makeBrightnessSlider();
		makeVolumeSlider();
		makeBrightnessLabel();
		makeVolumeLabel();
		settingsMenu.getChildren().add(brightnessLabel);
		settingsMenu.getChildren().add(brightnessSlider);
		settingsMenu.getChildren().add(volumeLabel);
		settingsMenu.getChildren().add(volumeSlider);
        //settingsStage.initOwner(viewManager.getGameStage());

		settingsStage.setScene(scene);
	}
	
	/**
	 * Returns the brightness slider for use.
	 * @return
	 */
	private Slider makeBrightnessSlider() {
		brightnessSlider = new Slider();
		brightnessSlider.getStyleClass().add("slider");
		brightnessSlider.setMin(0.3);
		brightnessSlider.setMax(1);
		brightnessSlider.setValue(1);
		return brightnessSlider;
		
	}
	/**
	 * Returns the volume slider for use.
	 * @return
	 */
	private Slider makeVolumeSlider() {
		volumeSlider = new Slider();
		volumeSlider.getStyleClass().add("slider");
		volumeSlider.setMin(0);
		volumeSlider.setMax(1);
		volumeSlider.setValue(1);
		return volumeSlider;
	}
	
	/**
	 * Creates the label for the brightness slider.
	 */
	private void makeBrightnessLabel() {
		brightnessLabel = new Label("Change Brightness:");
		brightnessLabel.getStyleClass().add("button-nav");
	}
	
	/**
	 * Creates the label for the volume slider.
	 */
	private void makeVolumeLabel() {
		volumeLabel = new Label("Change Volume for Default Song:");
		volumeLabel.getStyleClass().add("button-nav");
		
	}
	/**
	 * method to show settings Menu.
	 * 
	 */
	protected void showSettingsMenu() {
		settingsStage.show();
		
	}
	/**
	 * Getter method for the Brightness Slider on the Settings Stage.
	 * 
	 */
	public Slider getBrightnessSlider() {
		return brightnessSlider;
	}
	/**
	 * Getter method for the Volume Slider on the Settings Stage.
	 * 
	 */
	public Slider getVolumeSlider() {
		return volumeSlider;
	}
	

}
