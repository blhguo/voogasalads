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
		Engine engine = new Engine();
		this.dataManager = new DataManager();
		this.pullDownFactory = new PulldownFactory(dataManager);
		this.menu = new Menu(dataManager, pullDownFactory);
		this.viewManager = new ViewManager(menu, stage, pullDownFactory);
		this.playerView = new PlayerView(pullDownFactory, engine, viewManager);
		
		this.pullDownFactory.setPlayerView(playerView);
		this.viewManager.getMediaPlayer().play();
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
