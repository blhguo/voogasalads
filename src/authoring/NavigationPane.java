package authoring;

import java.util.ArrayList;
import java.util.Arrays;

import authoring.controllers.LevelController;
import authoring.controllers.PaneController;
import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import game_engine.Level;
import game_player.PlayerMain;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import observables.Listener;
import observables.Subject;
import resources.keys.AuthRes;

//Left Pane
public class NavigationPane implements Subject, GUINode {

	private ArrayList<String> menuTitles = new ArrayList<String>(Arrays.asList("Entity Creator", "Actions and Events", "Level Preferences", "Storyboard"));
	private ArrayList<String> compIcons = new ArrayList<String>(Arrays.asList("entity", "event", "level", "story"));
	private ArrayList<String> prefTitles = new ArrayList<String>(Arrays.asList("Play Game", "Save Game"));
	private ArrayList<String> prefIcons = new ArrayList<String>(Arrays.asList("play", "save"));
	private LevelController lcontroller;
	private Pane pane;
	private Stage stage;
	
	public NavigationPane(Stage s) {
		stage = s;
		pane = new Pane();
		pane.getStyleClass().add("pane-back");

		pane.setPadding(new Insets(AuthRes.getInt("Padding")));
		initializeButtons();
	}

	@Override
	public void addListener(Listener l) {
		myListeners.add(l);
	}

	@Override
	public void removeListener(Listener l) {
		myListeners.remove(l);
	}

	@Override
	public void notifyListeners(String key) {
		myListeners.stream()
			.forEach(l -> l.update(key));
	}

	private void initializeButtons() {
		VBox navOptions = new VBox(AuthRes.getInt("NavPadding"));
		Subject np = this;
		for(int i = 0; i < menuTitles.size(); i++) {
			String s = menuTitles.get(i);
			ImageView iv = ImageBuilder.resize(new ImageView(new Image(AuthRes.getString(compIcons.get(i)))), 20, 20);
			Button b = ButtonFactory.makeButton(s, iv, e -> np.notifyListeners(s),
					"button-nav");
			navOptions.getChildren().add(b);
		}
		navOptions.setLayoutY(AuthRes.getInt("EnvironmentY")/10);
		
		VBox prefButtons = new VBox(AuthRes.getInt("NavPadding"));
		for (int i = 0; i < prefTitles.size(); i++){
			ImageView iv = new ImageView(new Image(AuthRes.getString(prefIcons.get(i))));
			iv = ImageBuilder.resize(iv, 20);
			Button b;
			if (i == 1){
				b = ButtonFactory.makeButton(prefTitles.get(i), iv, e -> {
					lcontroller.saveGame();
				}, "button-nav");
			}
			else{
				b = ButtonFactory.makeButton(prefTitles.get(i), iv, e -> {
					new PlayerMain().start(stage);
				}, "button-nav");
				
			}
			prefButtons.getChildren().add(b);
			
		}
		prefButtons.setLayoutY(AuthRes.getInt("EnvironmentY")*4/5);
		pane.getChildren().addAll(navOptions, prefButtons);
	}

	@Override
	public Pane getView() {
		return pane;
	}
	
	public void setController(LevelController lc){
		lcontroller = lc;
	}
		
}
