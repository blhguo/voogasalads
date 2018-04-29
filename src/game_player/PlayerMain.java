package game_player;

import game_engine.Engine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class PlayerMain extends Application{
	ViewManager viewManager;
	Menu menu;
	DataManager dataManager;
	PulldownFactory pullDownFactory;
	PlayerView playerView;
	
	
	
	/**
	 * Method called to initialize the Game Player
	 */
	public void start(Stage stage) {
		
		this.dataManager = new DataManager();
		this.pullDownFactory = new PulldownFactory();
		this.menu = new Menu();
		this.viewManager = new ViewManager();
		this.playerView = new PlayerView();
		InstanceStorage dataStorage = new InstanceStorage();
		dataStorage.setDataManager(dataManager);
		dataStorage.setPullDownFactory(pullDownFactory);
		dataStorage.setMenu(menu);
		dataStorage.setViewManager(viewManager);
		dataStorage.setPlayerView(playerView);
	}
	
	public Scene getScene() {
		return viewManager.getScene();
	}
	/**
	 * Method called by JavaFX to launch the program.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
