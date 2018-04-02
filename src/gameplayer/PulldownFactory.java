package gameplayer;

import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;

/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class PulldownFactory {
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "gamePlayerResources/";

	private ResourceBundle speedProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "speed");
	private ResourceBundle statusProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "status");
	private ResourceBundle saveLoadProperties = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "save_load");
	
	private ComboBox<String> speedBox;
	private ComboBox<String> statusBox;
	private ComboBox<String> saveLoadBox;

	public PulldownFactory() {
		// TODO Auto-generated constructor stub
	}
	
	protected ComboBox<String> SpeedBox () {
		speedBox = new ComboBox<String>();
		speedBox.setValue(getResources(speedProperties, "InitialCommand"));
		speedBox.getItems().addAll(getResources(speedProperties, "SpeedUpCommand"),
				getResources(speedProperties, "SlowDownCommand"));
		return speedBox;
	}
	
	protected ComboBox<String> StatusBox () {
		statusBox = new ComboBox<String>();
		statusBox.setValue(getResources(statusProperties, "InitialCommand"));
		statusBox.getItems().addAll(getResources(statusProperties, "PauseGameCommand"), getResources(statusProperties, "PlayGameCommand"),
				getResources(statusProperties, "ReplayGameCommand"));
		return statusBox;
	}
	
	protected ComboBox<String> SaveLoadBox () {
		saveLoadBox = new ComboBox<String>();
		saveLoadBox.setValue(getResources(saveLoadProperties, "InitialCommand"));
		saveLoadBox.getItems().addAll(getResources(saveLoadProperties, "SaveCommand"), getResources(saveLoadProperties, "LoadCommand"));
		return saveLoadBox;
	}
	
	
	protected String getResources(ResourceBundle bundle, String string) {
		return bundle.getString(string);
	}

}
