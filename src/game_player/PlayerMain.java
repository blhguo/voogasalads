package game_player;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class PlayerMain extends Application{
	ViewManager viewManager;
	Menu menu;
	DataManager dataManager;
	Initializer initializer;
	PulldownFactory pullDownFactory;
	
	
	
	/**
	 * Method called to initialize the Game Player
	 */
	
	public void start(Stage stage) {
		this.dataManager = new DataManager();
		this.pullDownFactory = new PulldownFactory(dataManager);
		this.menu = new Menu(dataManager, pullDownFactory);
		this.viewManager = new ViewManager(menu,stage,pullDownFactory);
		this.initializer = new Initializer(this.viewManager);
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
