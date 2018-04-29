package game_player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import game_player_interfaces.ImportData;
import gameData.ManipData;
import game_engine.Engine;
import game_engine.level.Level;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	
	
	private ComboBox<String> speedBox;
	private ComboBox<String> statusBox;
	private ComboBox<String> saveLoadBox;
	private Engine gameEngine;
	private DataManager dataManager;
	private ViewManager viewManager;
	private PlayerView playerView;
	private ManipData manipData;
	private VBox aboutGameBox;
	private Scene aboutGameScene;
	private Stage aboutGameStage;
	private File file;
	private ButtonMaker buttonMaker = new ButtonMaker();
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

//	protected ComboBox<String> SpeedBox() {
//		speedBox =  new ComboBox<String>();
//
//		speedBox.setValue(getResources(speedProperties, "InitialCommand"));
//		speedBox.getItems().addAll(getResources(speedProperties, "SpeedUpCommand"),
//				getResources(speedProperties, "SlowDownCommand"));
//		speedBox.setPrefSize(160, 20);
//		speedBox.setOnAction(click -> {
//			playerView.handleUI();
//		});
//		return speedBox;
//	}
//
//	protected ComboBox<String> StatusBox() {
//		statusBox =  new ComboBox<String>();
//
//		statusBox.setValue(getResources(statusProperties, "InitialCommand"));
//		statusBox.getItems().addAll(getResources(statusProperties, "PauseGameCommand"),
//				getResources(statusProperties, "PlayGameCommand"), getResources(statusProperties, "ReplayGameCommand"));
//		statusBox.setPrefSize(160, 20);
//		statusBox.setOnAction(click -> {
//			playerView.handleUI();
//		});
//		return statusBox;
//	}

	protected ComboBox<String> SaveLoadBox() {
		saveLoadBox =  new ComboBox<String>();

		saveLoadBox.setValue(getResources(saveLoadProperties, "InitialCommand"));
		saveLoadBox.getItems().addAll(getResources(saveLoadProperties, "SaveCommand"),
				getResources(saveLoadProperties, "LoadCommand"));
		saveLoadBox.setPrefSize(160, 20);
		saveLoadBox.setOnAction(click -> {
			checkSaveOrLoad();
		});
		return saveLoadBox;
	}

	protected ComboBox<String> getSaveLoadBox() {
		return saveLoadBox;
	}
	
	protected ComboBox<String>getStatusBox(){
		return statusBox;
		
	}
	
	protected ComboBox<String>getSpeedBox(){
		return speedBox;
		
	}

	private void checkSaveOrLoad() {
		if (saveLoadBox.getValue().equals("Save")) {
			handleSave();
		} else if (saveLoadBox.getValue().equals("Load")) {
			importGame();
		}
	}

	private void handleSave() {
		ManipData turd = new ManipData();
		turd.saveData(dataManager.getGameEngine(),dataManager.getGameTitle(),dataManager.getGameMetadata());
	}

	@Override
	public void importGame() {
		ManipData turd = new ManipData();
		File file = getFile();
		viewManager.changeBackground();
		gameEngine = turd.loadData(file.getAbsolutePath(),"ExampleGame");
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
