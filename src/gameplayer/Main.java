package gameplayer;

import javafx.stage.Stage;
import javafx.application.Application;
/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class Main extends Application{
	ViewManager viewManager;
	Menu menu;
	DataManager dataManager;
	Initializer initializer;
	/**
	 * Method called to initialize the Game Player
	 */
	public void start(Stage stage) {
		this.dataManager = new DataManager();
		this.menu = new Menu(dataManager);
		this.viewManager = new ViewManager(menu,stage);
		this.initializer = new Initializer();
	}
	/**
	 * Method called by JavaFX to launch the program.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
