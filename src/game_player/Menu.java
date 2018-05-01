package game_player;

import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
	private PlayerView playerView;

	public Menu() {
		//TODO something
	}

	public void initialize(InstanceStorage storage) {
		pane = new HBox(35);
		pane.setAlignment(Pos.TOP_CENTER);
		dataManager = storage.getDataManager();
		playerView = storage.getPlayerView();
		pullDownFactory = storage.getPullDownFactory();
		makeButtons();
		settings = new SettingsMenu(storage.getStage());
		settings.makeSettingsMenu(pane);
		settings.makeSettingsStage();
		gameMenu = new GameSelectionMenu();
		gameMenu.makeGameSelectionMenu(pane,pullDownFactory);
		new KeyPrefMenu(dataManager,pane);
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
		buttonMaker.setPlayerView(playerView);
		for (int i = 0; i < buttonMaker.makeMenuButton().size(); i++) {
			pane.getChildren().add(buttonMaker.makeMenuButton().get(i));
		}

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
