package game_player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import gameData.ManipData;
import game_engine.Engine;
import game_player_interfaces.ImportData;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This is the class in which the pulldown menus are created and accessed.
 * It deals with the methods required by the pulldown menu, in addition to their
 * setup and format.
 * 
 * @author Dana Park, Brandon Dalla Rosa
 *
 */
public class PulldownFactory implements ImportData {

	private static final String DEFAULT_RESOURCE_PACKAGE = "game_player_resources/";

	private ResourceBundle speedProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "speed");
	private ResourceBundle statusProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "status");
	private ResourceBundle saveLoadProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "save_load");
	private Map<String, String> test = new HashMap<String, String>();
	private ComboBox<String> saveLoadBox;
	private Engine gameEngine;
	private DataManager dataManager;
	private ViewManager viewManager;
	private PlayerView playerView;
	private VBox aboutGameBox;
	private Scene aboutGameScene;
	private Stage aboutGameStage;
	private File file;
	private String dataFilePathString;
	/**
	 *Constructor for the pull down factory. It initializes all of the
	 * combo boxes seen in the game player.
	 */
	public PulldownFactory(DataManager dat) {
		dataManager = dat;
		//speedBox =  SpeedBox();
		//statusBox =  StatusBox();
		saveLoadBox =  SaveLoadBox();


	}

	protected ComboBox<String> SaveLoadBox() {
		saveLoadBox =  new ComboBox<String>();

		saveLoadBox.setValue(getResources(saveLoadProperties, "InitialCommand"));
		saveLoadBox.getItems().addAll(getResources(saveLoadProperties, "SaveCommand"),
				getResources(saveLoadProperties, "LoadCommand"));
		saveLoadBox.setPrefSize(160, 20);
		saveLoadBox.setOnKeyPressed(click->{});
		saveLoadBox.setOnAction(click -> {
			checkSaveOrLoad();
		});
		return saveLoadBox;
	}

	protected ComboBox<String> getSaveLoadBox() {
		return saveLoadBox;
	}

	private void checkSaveOrLoad() {
		if (saveLoadBox.getValue().equals("Save")) {
			handleSave();
		} else if (saveLoadBox.getValue().equals("Load")) {
			importGame();
		}
	}

	public void handleSave() {
		ManipData manipData = new ManipData();
		Stage tempStage = new Stage();
		TextField textField = new TextField();
		Scene tempScene = new Scene(textField);
		tempStage.setScene(tempScene);
		tempStage.initOwner(viewManager.getGameStage());
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
		if(file==null) {
			return;
		}
		String toParse = file.getAbsolutePath();
		int loc = toParse.indexOf("games");
		int endLoc = 0;
		int numberSlashes = 0;
		for(int i=loc;i<toParse.length();i++) {
			if(toParse.charAt(i)=='\\') {
				numberSlashes++;
			}
			if(numberSlashes==2) {
				endLoc = i;
				numberSlashes++;
			}
		}
		dataFilePathString = toParse.substring(loc+6,endLoc);
		System.out.println(dataFilePathString);
		
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
        aboutGameStage.initOwner(viewManager.getGameStage());

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
    
    /**
     * Method to pass the view manager into the pull down factory for access.
     */ 
	public void setViewManager(ViewManager vm) {
		viewManager = vm;
	}
    
	private File getFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose Game File");
		file = fileChooser.showOpenDialog(new Stage());
		return file;
	}

	protected String getResources(ResourceBundle bundle, String string) {
		return bundle.getString(string);
	}
    
    /**
     * Method called to pass the playerview into the pull down factory for
     * ease of access.
     */ 
	public void setPlayerView(PlayerView playerView) {
		this.playerView = playerView;
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
