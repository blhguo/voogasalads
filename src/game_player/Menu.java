package game_player;

import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author Brandon Dalla Rosa, Dana Park
 *Class that represents all Menu items contained in HBox on bottom of the screen
 */

public class Menu {

	private HBox pane;
	private DataConnect dataConnect;
	private ButtonMaker buttonMaker;
	private DataManager dataManager;
	private SettingsMenu settings;
	private GameSelectionMenu gameMenu;
	private PlayerView playerView;
	
	/**
	 * Constructor to create instance prior to initialization.
	 */
	public Menu() {
		//TODO something
	}
	/**
	 * Method called to initialize class after creation.
	 * 
	 * @param storage
	 */
	public void initialize(InstanceStorage storage) {
		pane = new HBox(35);
		pane.setAlignment(Pos.TOP_CENTER);
		dataManager = storage.getDataManager();
		playerView = storage.getPlayerView();
		dataConnect = storage.getDataConnect();
		makeButtons();
		settings = new SettingsMenu(storage.getStage());
		settings.makeSettingsMenu(pane);
		settings.makeSettingsStage();
		gameMenu = new GameSelectionMenu();
		gameMenu.makeGameSelectionMenu(pane,dataConnect);
		new KeyPrefMenu(dataManager,pane);
	}
	/**
<<<<<<< HEAD
	 * Method to add the menu into the VBox for the View Manager.
	 * 
=======
	 * Method to add the menu into the VBox for the View Manager
	 *
>>>>>>> jenny
	 * @param root
	 */
	public void addMenu(Pane root) {
		root.getChildren().add(pane);
	}
	
    /**
	 * Method to add buttons from the button maker into Menu.
	 *
	 */

	private void makeButtons() {
		buttonMaker = new ButtonMaker();
		buttonMaker.setPlayerView(playerView);
		for (int i = 0; i < buttonMaker.makeMenuButton().size(); i++) {
			pane.getChildren().add(buttonMaker.makeMenuButton().get(i));
		}

	}

	/**
	 * Getter method for the Brightness Slider on the Settings Stage.
	 *
	 */
	public Slider getBrightnessSlider() {
		return settings.getBrightnessSlider();
	}
	/**
	 * Getter method for the Volume Slider on the Settings Stage.
	 *
	 */
	public Slider getVolumeSlider() {
		return settings.getVolumeSlider();
	}

}
