package game_player;

import java.io.File;

import gameData.ManipData;
import game_engine.Engine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Brandon Dalla Rosa
 *
 * Upper level class which creates the other classes found in the
 * game player.
 */
public class PlayerMain extends Application{
	ViewManager viewManager;
	Menu menu;
	DataManager dataManager;
	DataConnect dataConnect;
	PlayerView playerView;
	Engine engine;
	
	
	
	/**
	 * Method called to initialize the Game Player.
	 */
	public void start(Stage stage) {
		InstanceStorage dataStorage = new InstanceStorage();
		this.dataManager = new DataManager();
		this.dataConnect = new DataConnect();
		this.menu = new Menu();
		this.viewManager = new ViewManager();
		this.playerView = new PlayerView();
		dataStorage.setDataManager(dataManager);
		dataStorage.setDataConnect(dataConnect);
		dataStorage.setMenu(menu);
		dataStorage.setViewManager(viewManager);
		dataStorage.setPlayerView(playerView);
		dataStorage.setStage(stage);
		dataManager.initialize(dataStorage);
		dataConnect.initialize(dataStorage);
		menu.initialize(dataStorage);
		viewManager.initialize(dataStorage);
		playerView.initialize(dataStorage);
	}
	
	public void launchFromAuthoring(Stage stage, File file) {
		start(stage);
		ManipData manipData = new ManipData();
		file = dataConnect.getFile();
		engine=dataConnect.getGameEngine();
		engine = manipData.loadData(file.getAbsolutePath());
		playerView.setEngine(engine);
		dataManager.setGameEngine(engine);
		playerView.instantiate();
	}
	
	/**
	 * Method to return the current scene of the game player.
	 * @return
	 */
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
