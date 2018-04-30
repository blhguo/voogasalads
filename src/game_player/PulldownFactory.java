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
 * Manages data.
 * 
 * @author Dana Park, Brandon Dalla Rosa
 *
 */
public class PulldownFactory implements ImportData {

	private Map<String, String> test = new HashMap<String, String>();
	private Engine gameEngine;
	private DataManager dataManager;
	private ViewManager viewManager;
	private PlayerView playerView;
	private VBox aboutGameBox;
	private Scene aboutGameScene;
	private Stage aboutGameStage;
	private File file;
	private String dataFilePathString;
	private Stage gameStage;
	/**
	 *Constructor for the pull down factory. It initializes all of the
	 * combo boxes seen in the game player.
	 */
	public PulldownFactory() {
		//TODO something
	}
	public void initialize(InstanceStorage storage) {
		dataManager = storage.getDataManager();
		viewManager = storage.getViewManager();
		playerView = storage.getPlayerView();
		gameStage = storage.getStage();
	}

	public void handleSave() {
		ManipData manipData = new ManipData();
		Stage tempStage = new Stage();
		TextField textField = new TextField();
		Scene tempScene = new Scene(textField);
		tempStage.setScene(tempScene);
		tempStage.initOwner(gameStage);
		tempStage.show();
		textField.setOnAction(click->{
			manipData.saveData(dataManager.getGameEngine(),dataManager.getGameTitle(),textField.getText(),true);
			tempStage.hide();
			});
	}
	@Override
	public void importGame() {
		ManipData manipData = new ManipData();
		File file = getFile();
//		if(file==null) {
//			return;
//		}
//		String toParse = file.getAbsolutePath();
//		int loc = toParse.indexOf("games");
//		int endLoc = 0;
//		int numberSlashes = 0;
//		for(int i=loc;i<toParse.length();i++) {
//			if(toParse.charAt(i)=='\\') {
//				numberSlashes++;
//			}
//			if(numberSlashes==2) {
//				endLoc = i;
//				numberSlashes++;
//			}
//		}
//		dataFilePathString = toParse.substring(loc+6,endLoc);
//		System.out.println(dataFilePathString);
		
		viewManager.changeBackground();
		gameEngine = manipData.loadData(file.getAbsolutePath());
		playerView.setEngine(gameEngine);
		dataManager.setGameEngine(gameEngine);
		playerView.instantiate();
	}
	
	public void aboutGame() {
		test.put("Hello", "World");
		test.put("Homework", "Much");
		test.put("author", "Dana");
		TextArea text = new TextArea();
		String string = new String();
		aboutGameBox = new VBox(text);
		aboutGameStage = new Stage();
		aboutGameScene = new Scene(aboutGameBox);

		aboutGameScene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());

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
     * Method to return the list of levels loaded from data.
     */ 
	public Engine getGameEngine() {
		return gameEngine;
	}
    
	private File getFile() {
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

	@Override
	public void importPreferences() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importGameState() {
		// TODO Auto-generated method stub
		
	}

}
