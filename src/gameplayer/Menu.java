package gameplayer;

import java.util.ArrayList;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class Menu {
	private HBox pane;
	private ComboBox<HBox> keyPrefMenu;
	private DataManager dataManager;
	private boolean isReading;
	private KeyCode currentKey;
	private Button currentPrefButton;
	private String currentPrefString;
	
	public Menu(DataManager data) {
		pane = new HBox(20);
		pane.setAlignment(Pos.CENTER);
		dataManager = data;
		isReading = false;
		currentKey = KeyCode.ENTER;
		currentPrefButton = new Button();
		
		//Remove this later, just for example
		for(int i=0;i<5;i++) {
			pane.getChildren().add(new Button("button "+i));
		}
		keyPrefMenu = new ComboBox<HBox>();
		pane.getChildren().add(keyPrefMenu);
		
		initKeyPrefMenu();
	}
	/**
	 * Method to add the menu into the VBox for the View Manager
	 * 
	 * @param root
	 */
	public void addMenu(Pane root) {
		root.getChildren().add(pane);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initKeyPrefMenu() {
		keyPrefMenu.setPromptText("Key Prefs");
		keyPrefMenu.setOnHidden(click->{
			currentPrefButton = new Button();
			});		
		ArrayList<String> inputs = (ArrayList<String>) dataManager.getInputCommands();
		for(String s : inputs) {
			HBox toAdd = new HBox(10);
			toAdd.setAlignment(Pos.CENTER);
			Label label = new Label(s);
			Button button = new Button("ENTER");
			button.setOnAction(click->{setPref(button,s);});
			toAdd.getChildren().add(label);
			toAdd.getChildren().add(button);
			keyPrefMenu.getItems().add(toAdd);
		}
		
		keyPrefMenu.setSkin( new ComboBoxListViewSkin( keyPrefMenu )
		{
		    @Override
		    protected boolean isHideOnClickEnabled()
		    {
		        return false;
		    }
		} );
	}
	private void setPref(Button button,  String string) {
		currentPrefButton = button;
		currentPrefString = string;
	}
	
	public void checkForInput(KeyCode code) {
		currentKey = code;
		currentPrefButton.setText(""+currentKey);
		dataManager.setKey(currentPrefString, code);
	}
	

}
