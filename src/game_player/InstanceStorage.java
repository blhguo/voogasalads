package game_player;

import javafx.stage.Stage;
/**
 * 
 * @author Brandon Dalla Rosa
 * 
 * Holds the instances of the game player that need to
 * communicate in order to lessen dependency issues.
 *
 */
public class InstanceStorage {
	
	ViewManager viewManager;
	Menu menu;
	DataManager dataManager;
	DataConnect dataConnect;
	PlayerView playerView;
	ButtonMaker buttonMaker;
	Stage gameStage;
	/**
	 * Sets the game stage.
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.gameStage = stage;
	}
	/**
	 * Sets the view manager.
	 * @param vm
	 */
	public void setViewManager(ViewManager vm) {
		this.viewManager = vm;
	}
	/**
	 * Sets the menu.
	 * @param m
	 */
	public void setMenu(Menu m) {
		this.menu = m;
	}
	/**
	 * Sets the data manager.
	 * @param dm
	 */
	public void setDataManager(DataManager dm) {
		this.dataManager = dm;
	}
	/**
	 * Sets the data connect.
	 * @param dc
	 */
	public void setDataConnect(DataConnect dc) {
		this.dataConnect = dc;
	}
	/**
	 * Sets the player view.
	 * @param pv
	 */
	public void setPlayerView(PlayerView pv) {
		this.playerView = pv;
	}
	/**
	 * Sets the button maker.
	 * @param bm
	 */
	public void setButtonMaker(ButtonMaker bm) {
		this.buttonMaker = bm;
	}
	/**
	 * Gets the view manager.
	 * @return
	 */
	public ViewManager getViewManager() {
		return this.viewManager;
	}
	/**
	 * Gets the menu.
	 * @return
	 */
	public Menu getMenu() {
		return this.menu;
	}
	/**
	 * Gets the data manager.
	 * @return
	 */
	public DataManager getDataManager() {
		return this.dataManager;
	}
	/**
	 * Gets the data connect.
	 * @return
	 */
	public DataConnect getDataConnect() {
		return this.dataConnect;
	}
	/**
	 * Gets the player view.
	 * @return
	 */
	public PlayerView getPlayerView() {
		return this.playerView;
	}
	/**
	 * Gets the button maker.
	 * @return
	 */
	public ButtonMaker getButtonMaker() {
		return this.buttonMaker;
	}
	/**
	 * Gets the stage.
	 * @return
	 */
	public Stage getStage() {
		return this.gameStage;
	}

}
