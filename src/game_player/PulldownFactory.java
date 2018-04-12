package game_player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gameData.ManipData;
import game_engine.Level;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * @author Dana Park, Brandon Dalla Rosa
 *
 */
public class PulldownFactory {

	private static final String DEFAULT_RESOURCE_PACKAGE = "game_player_resources/";

	private ResourceBundle speedProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "speed");
	private ResourceBundle statusProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "status");
	private ResourceBundle saveLoadProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "save_load");

	private ComboBox<String> speedBox;
	private ComboBox<String> statusBox;
	private ComboBox<String> saveLoadBox;
	private List<Level> levels = new ArrayList<Level>();
	private DataManager dataManager;
	private ViewManager viewManager;
	private PlayerView playerView;

	public PulldownFactory(DataManager dat) {
		dataManager = dat;
	}

	protected ComboBox<String> SpeedBox() {
		speedBox = new ComboBox<String>();
		speedBox.setValue(getResources(speedProperties, "InitialCommand"));
		speedBox.getItems().addAll(getResources(speedProperties, "SpeedUpCommand"),
				getResources(speedProperties, "SlowDownCommand"));
		speedBox.setPrefSize(160, 20);
		return speedBox;
	}

	protected ComboBox<String> StatusBox() {
		statusBox = new ComboBox<String>();
		statusBox.setValue(getResources(statusProperties, "InitialCommand"));
		statusBox.getItems().addAll(getResources(statusProperties, "PauseGameCommand"),
				getResources(statusProperties, "PlayGameCommand"), getResources(statusProperties, "ReplayGameCommand"));
		statusBox.setPrefSize(160, 20);
		return statusBox;
	}

	protected ComboBox<String> SaveLoadBox() {
		saveLoadBox = new ComboBox<String>();
		saveLoadBox.setValue(getResources(saveLoadProperties, "InitialCommand"));
		saveLoadBox.getItems().addAll(getResources(saveLoadProperties, "SaveCommand"),
				getResources(saveLoadProperties, "LoadCommand"));
		saveLoadBox.setPrefSize(160, 20);
		saveLoadBox.setOnAction(click -> {
			checkSomething();
		});
		return saveLoadBox;
	}

	protected ComboBox<String> getSaveLoadBox() {
		return saveLoadBox;
	}

	private void checkSomething() {
		if (saveLoadBox.getValue().equals("Save")) {
			handleSave();
		} else if (saveLoadBox.getValue().equals("Load")) {
			handleLoad();
		}
	}

	private void handleSave() {
		ManipData turd = new ManipData();
		turd.saveData(dataManager.getGameLevels());
	}

	protected void handleLoad() {
		ManipData turd = new ManipData();
		File file = getFile();
		System.out.println(file.getAbsolutePath());
		viewManager.changeBackground();
		levels = turd.loadData(file);
		System.out.println("LEVELS: " + levels);
		dataManager.setGameLevels(levels);
		playerView.instantiate();
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setViewManager(ViewManager vm) {
		viewManager = vm;
	}

	private File getFile() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(new Stage());
		return file;
	}

	protected String getResources(ResourceBundle bundle, String string) {
		return bundle.getString(string);
	}

	public void setPlayerView(PlayerView playerView) {
		this.playerView = playerView;
	}

}
