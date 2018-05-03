package authoring.loadingviews;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.AuthoringEnvironment;
import authoring.Toolbar;
import authoring.GUI_Heirarchy.GUIGridPaneSuper;
import gameData.ManipData;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * @author Jennifer Chin
 * Extends GUIGridPaneSuper. This class is a GUIBuilder object because it has its own
 * Scene, and it extends GUIGridPaneSuper because it uses a gridpane as the root of that
 * Scene. 
 */
public abstract class BaseLoader extends GUIGridPaneSuper {

	protected Stage myStage;
	protected AuthoringEnvironment ae;
	private ManipData data;
	
	/**
	 * Constructor that takes in a Stage in order to change the scene of the stage to 
	 * the GameChooserScreen
	 * @param stage
	 */
	public BaseLoader(Stage stage){
		//uses stage to switch scene once game is chosen
		myStage = stage;
		ae = new AuthoringEnvironment(stage);
		data = new ManipData();
	}

	/**
	 * Builds the basic view for the GameChooser screen, factoring out all the common elements
	 * between the Load Game for Play view and the Load Game for Edit view
	 * @param gridpane
	 */
	public Pane addCoreFinishingElements(GridPane gridpane) {
		double chooserWidth = AuthRes.getInt("EnvironmentX") 
				- (AuthRes.getInt("Margin") * AuthRes.getInt("FieldMultiplier"));
		double chooserHeight = AuthRes.getInt("EnvironmentY") 
				- (AuthRes.getInt("Margin") * AuthRes.getInt("FieldMultiplier"));
		VBox vbox = new VBox();
		vbox.setPrefWidth(chooserWidth);
		vbox.setPrefHeight(chooserHeight);
		vbox.getStyleClass().add("chooser-back");
		gridpane.add(vbox, AuthRes.getInt("BaseLoadCol"), AuthRes.getInt("BaseLoadRow"));
		File folder = new File("games");
		File[] games = folder.listFiles();
		ArrayList<Map<String, String>> gameInfo = new ArrayList<Map<String, String>>();
		for (File game: games){
			if (! game.getPath().equals("games/.DS_Store")){
				Map<String, String> map = new HashMap<String, String>();
				String filePath = game.getName() + "/" + game.getName() + "config";
				Map<String, String> configMap = data.openConfig(filePath);
				String name = configMap.get(AuthRes.getStringKeys("key0"));
				String thumbPath = configMap.get(AuthRes.getStringKeys("key1"));
				map.put(AuthRes.getString("ThumbName"), name);
				map.put(AuthRes.getString("ThumbImage"), thumbPath);
				File gameFile = new File("games");
				File metaFile = new File("games");
				for (File f: game.listFiles()){
					if (!f.getName().contains("config.properties") && !f.getName().contains("metaData")){
						gameFile = f;
					}
					else if (f.getName().equals("metaData.xml")){
						metaFile = f;
					}
				}
				map.put(AuthRes.getString("ThumbGame"), gameFile.getPath());
				map.put(AuthRes.getString("ThumbMeta"), metaFile.getPath());
				gameInfo.add(map);
			}

		}
		buildThumbnails(vbox, gameInfo);
		return new Toolbar(myStage).integrateToolbar(gridpane);
	}
	
	/**
	 * Adds a title specific to the type of view (e.g. "Load Game for Edit" vs. "Load Game
	 * for Play"
	 * @param gridpane	current pane set as root in scene
	 * @param type		which specific GameChooser view is needed; determines end of title string
	 */
	public void addTitle(GridPane gridpane, String type) {
		try {
			Text title = new Text(AuthRes.getString("ChooserTitle") + AuthRes.getString(type));
			title.getStyleClass().add("title2");
			gridpane.add(title, AuthRes.getInt("BaseLoadCol"), AuthRes.getInt("DefaultSpacing"));
		} catch (NullPointerException e) {
			Alert noType = new Alert(AlertType.ERROR);
			noType.setContentText(AuthRes.getString("NoChooserType"));
			noType.showAndWait();
		}
	}
	
	public abstract void buildThumbnails(VBox vb, List<Map<String, String>> gameInfo);

}
