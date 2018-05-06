package authoring;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import authoring.GUI_Heirarchy.GUINode;
import authoring.controllers.Loader;
import authoring.controllers.MetaController;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ImageBuilder;
import frontend_utilities.UserFeedback;
import game_player.PlayerMain;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import observables.Listener;
import observables.Subject;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * @author Jennifer Chin
 * Left menu of the authoring environment. Allows the user to change the right menu
 * and play and save the current game. Observable by the right pane so that when 
 * one of the buttons is pressed to change the right menu, the right pane will change
 * accordingly.
 */

//Left Pane
public class NavigationPane implements Subject, GUINode {

	private ArrayList<String> menuTitles = new ArrayList<String>(Arrays.asList("Entity Creator", "Events", "Level Preferences", "Storyboard"));
	private ArrayList<String> compIcons = new ArrayList<String>(Arrays.asList("entity", "event", "level", "story"));
	private ArrayList<String> prefTitles = new ArrayList<String>(Arrays.asList("Play Game", "Save Game", "Load Game"));
	private ArrayList<String> prefIcons = new ArrayList<String>(Arrays.asList("play", "save", "load"));
	private MetaController mcontroller;
	private Pane pane;
	private Stage stage;
	private Loader loader;
	
	/**
	 * Constructor. Takes in a stage so that the play button can launch Player Main 
	 * and change the scene on the stage. Initializes buttons and styles pane.
	 * @param s
	 */
	public NavigationPane(Stage s) {
		stage = s;
		pane = new Pane();
		pane.getStyleClass().add("pane-back");
		pane.setPadding(new Insets(AuthRes.getInt("Padding")));
		initializeButtons();
	}
	
	public void setLoader(Loader l){
		loader = l;
	}
	
	/**
	 * Method to add meta controller so that the game can be saved from this pane
	 * @param mc Metacontroller
	 */
	public void addMetaController(MetaController mc){
		mcontroller = mc;
	}
	
	/**
	 * Part of Subject interface which allows this class to be observable
	 */
	@Override
	public void addListener(Listener l) {
		myListeners.add(l);
	}

	/**
	 * Part of Subject interface which allows this class to be observable
	 */
	@Override
	public void removeListener(Listener l) {
		myListeners.remove(l);
	}

	/**
	 * Part of Subject interface which allows this class to be observable
	 */
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
			Button b = ButtonFactory.makeButton(s, iv, e -> np.notifyListeners(s), "button-nav");
			navOptions.getChildren().add(b);
		}
		navOptions.setLayoutY(stage.getHeight()/10);
		
		VBox prefButtons = new VBox(AuthRes.getInt("NavPadding"));
		for (int i = 0; i < prefTitles.size(); i++){
			ImageView iv = ImageBuilder.resize(new ImageView(new Image(AuthRes.getString(prefIcons.get(i)))), 20);
			Button b;
			if (prefTitles.get(i).equals("Save Game")){
				b = ButtonFactory.makeButton(prefTitles.get(i), iv, e -> {
					mcontroller.saveGame();
					String content = AuthRes.getString("SaveContent") + " " + mcontroller.getGameName() + ".xml";
					Alert a = UserFeedback.getInfoMessage(AuthRes.getString("SaveHeader"), content, stage);
					a.showAndWait();
				}, "button-nav");
			}
			else if (prefTitles.get(i).equals("Load Game")){
				b = ButtonFactory.makeButton(prefTitles.get(i), iv, e -> {
					DirectoryChooser dc = new DirectoryChooser();
					dc.setTitle("Choose Game to Load");
					File file = dc.showDialog(null);
					File gameFile = new File("games");
					File metaFile = new File("games");
						for (File f : file.listFiles()) {
							if (!f.getName().contains("config.properties") && !f.getName().contains("metaData")) {
								gameFile = f;
							} else if (f.getName().equals("metaData.xml")) {
								metaFile = f;
							}
						}
					loader.loadGame(gameFile.getPath(), metaFile.getPath());
				}, "button-nav");
			}
			else{
				b = ButtonFactory.makeButton(prefTitles.get(i), iv, e -> {
					Alert a = UserFeedback.getWarningMessage(AuthRes.getString("PlayHeader"), AuthRes.getString("PlayContent"), stage);
					Optional<ButtonType> result = a.showAndWait();
					if (result.get() == ButtonType.OK){
						new PlayerMain().launchFromAuthoring(stage, new File(mcontroller.getGameName()+".xml"));
					}
				}, "button-nav");
			}
			prefButtons.getChildren().add(b);
		}
		prefButtons.setLayoutY(stage.getHeight()*4/5);
		pane.getChildren().addAll(navOptions, prefButtons);
	}

	/**
	 * Method from GUINode. NavigationPane implements GUINode because it is a sub-part
	 * of a larger scene. This method returns the visual Pane of this class.
	 */
	@Override
	public Pane getView() {
		return pane;
	}
		
}
