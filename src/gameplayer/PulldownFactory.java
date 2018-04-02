package gameplayer;

import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;

/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class PulldownFactory {

	public PulldownFactory() {
		// TODO Auto-generated constructor stub
	}
	
	protected ComboBox<String> SpeedBox (ComboBox<String>speedBox , ResourceBundle bundle) {
		speedBox = new ComboBox<String>();
		speedBox.setValue(getResources(bundle, "InitialCommand"));
		speedBox.getItems().addAll(getResources(bundle, "SpeedUpCommand"),
				getResources(bundle, "SlowDownCommand"));
		return speedBox;
	}
	
	protected ComboBox<String> StatusBox (ComboBox<String>statusBox , ResourceBundle bundle) {
		statusBox = new ComboBox<String>();
		statusBox.setValue(getResources(bundle, "InitialCommand"));
		statusBox.getItems().addAll(getResources(bundle, "PauseGameCommand"), getResources(bundle, "PlayGameCommand"),
				getResources(bundle, "ReplayGameCommand"));
		return statusBox;
	}
	
	protected ComboBox<String> SaveLoadBox (ComboBox<String>saveLoadBox , ResourceBundle bundle) {
		saveLoadBox = new ComboBox<String>();
		saveLoadBox.setValue(getResources(bundle, "InitialCommand"));
		saveLoadBox.getItems().addAll(getResources(bundle, "SaveCommand"), getResources(bundle, "LoadCommand"));
		return saveLoadBox;
	}
	
	
	protected String getResources(ResourceBundle bundle, String string) {
		return bundle.getString(string);
	}

}
