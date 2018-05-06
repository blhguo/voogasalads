package game_player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import gameData.ManipData;
import game_engine.Engine;
import game_player_interfaces.ImportData;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Class which handles the connections to the game data, including saving 
 * and loading the game engine for use.
 *
 * @author Dana Park, Brandon Dalla Rosa
 *
 */
public class DataConnect implements ImportData {

	private static final String INPUT_TITLE = "Enter save file name";
	private Map<String, String> test = new HashMap<String, String>();
	private Engine gameEngine;
	private DataManager dataManager;
	private ViewManager viewManager;
	private PlayerView playerView;
	private VBox aboutGameBox;
	private Scene aboutGameScene;
	private Stage aboutGameStage;
	private File file;
	private Stage gameStage;
	private File replayPath;
	/**
	 * Constructor for the data connector. It creates the instance prior to initialization.
	 */
	public DataConnect() {
		//TODO something
	}
	/**
	 * Function called to initialize the class after creation to reduce dependency issues.
	 * 
	 * @param storage
	 */
	public void initialize(InstanceStorage storage) {
		dataManager = storage.getDataManager();
		viewManager = storage.getViewManager();
		playerView = storage.getPlayerView();
		gameStage = storage.getStage();
	}
	
	/**
	 * Method called to save the current game state. It gives the user
	 * an opportunity to name the file.
	 */
	public void handleSave() {
		if(dataManager.getGameEngine()==null) {
			return;
		}
		ManipData manipData = new ManipData();
		Stage tempStage = new Stage();
		TextField textField = new TextField();
		Scene tempScene = new Scene(textField);
		tempStage.setScene(tempScene);
		tempStage.initOwner(gameStage);
		tempStage.setTitle(INPUT_TITLE);
		tempStage.show();
		textField.setOnAction(click->{
			manipData.saveData(dataManager.getGameEngine(),dataManager.getGameTitle(),textField.getText(),true);
			tempStage.hide();
			});

	}
	/**
	 * Method called to load a game from a saved file. The user is able 
	 * to select the file.
	 */
	@Override
	public void importGame() {
		ManipData manipData = new ManipData();
		File file = getFile();

		if(file==null) {
			return;
		}
		replayPath = file;		
		viewManager.changeBackground();

		gameEngine = manipData.loadData(file.getAbsolutePath());
		playerView.setEngine(gameEngine);
		dataManager.setGameEngine(gameEngine);
		playerView.instantiate();
	}

	/**
	 * Method called to set the about game information provided by the
	 * authoring environment.
	 */
	public void aboutGame() {
		test.put("Vooga", "Salad");
		test.put("Year", "2018");
		test.put("Call Us", "Salad");
		TextArea text = new TextArea();
		String string = new String();
		aboutGameBox = new VBox(text);
		aboutGameStage = new Stage();
		aboutGameScene = new Scene(aboutGameBox);


		aboutGameStage.setScene(aboutGameScene);
		aboutGameStage.setTitle("About Game");
		aboutGameStage.initOwner(gameStage);

		for (String key:test.keySet()) {
			string=string+key+" "+test.get(key)+"\n";

		}
		text.setText(string);

		//manipData.openMeta(file);
		aboutGameStage.show();

	}

    
    /**
     * Method to return the desired game engine loaded from data.
     */ 
	public Engine getGameEngine() {
		return gameEngine;
	}
    
	/**
	 * Method to create the file chooser for the user to interact with.
	 * @return: User desired file.
	 */
	protected File getFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose Game File");
		Stage fileChooserStage = new Stage();
		fileChooserStage.setTitle("Choose Game");
		fileChooserStage.initOwner(gameStage);
		file = fileChooser.showOpenDialog(fileChooserStage);
		return file;
	}

	protected String getResources(ResourceBundle bundle, String string) {
		return bundle.getString(string);
	}
	
	/**
	 * Method called to replay the game from the most recently loaded file.
	 */
	public void handleReplay() {
		if(replayPath==null) {
			return;
		}
		ManipData manipData = new ManipData();
		gameEngine = manipData.loadData(replayPath.getAbsolutePath());
		playerView.setEngine(gameEngine);
		dataManager.setGameEngine(gameEngine);
		playerView.instantiate();
	}
	

	@Override
	public void importPreferences() {
		// TODO Auto-generated method stub

	}

	@Override
	public void importGameState() {
		// TODO Auto-generated method stub

	}

}
