package authoring.right_components;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import authoring.controllers.LevelController;
import authoring.controllers.MetaController;
import authoring.controllers.PaneController;
import frontend_utilities.ButtonFactory;
import game_engine.Component;
import game_engine.level.LevelBackgroundComponent;
import game_engine.level.LevelNameComponent;
import game_engine.level.LevelThumbComponent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import resources.keys.AuthRes;

/**
 * @author Jennifer Chin
 * StoryboardPane that extends BasePane, which implements GUINode. This class allows 
 * users to rearrange levels and toggle overall game preferences
 */
public class StoryBoardPane extends BasePane {
	
	private LevelController lcontroller;
	private MetaController mcontroller;
	private PaneController pcontroller;
	private Text activeLevel = new Text();
	private TextField gameName = new TextField();
	private TextField author = new TextField();
	private TextArea rules = new TextArea();

	/**
	 * Constructor that uses the BasePane constructor
	 */
	public StoryBoardPane() {
		super();
	}
	
	/**
	 * GUINode method that returns the view of this Pane
	 * @return Pane
	 */
	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("StoryTitle"));
		activeLevel.setText(lcontroller.getEngine().getLevel().getComponent(LevelNameComponent.class).getValue());
		activeLevel.getStyleClass().add("button-label");
		box.getChildren().addAll(ButtonFactory.makeReverseHBox("Active Level: ", null, activeLevel), LevelOrderer(), newSeparator());
		getButtonArray().stream().map((button) -> box.getChildren().add(button)).collect(Collectors.toList());
		return box;
	}

	/**
	 * BasePane method that returns the buttons on this pane
	 */
	@Override 
	public List<Node> getButtonArray(){
		ArrayList<Node> list = new ArrayList<>();
		gameName.setPromptText(mcontroller.getGameName());
		gameName.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER){
				mcontroller.setGameName(gameName.getText());
			}
		});
		list.add(ButtonFactory.makeReverseHBox("Set Game Name: ", null, gameName));
		
		author.setPromptText(mcontroller.getPrintMap().get(AuthRes.getString("Author")));
		author.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER){
				mcontroller.getPrintMap().put(AuthRes.getString("Author"), author.getText());
				mcontroller.getConfigMap().put(AuthRes.getStringKeys("key2"), author.getText());
			}
		});
		list.add(ButtonFactory.makeReverseHBox("Set Author: ", null, author));
		
		rules.setPromptText(mcontroller.getPrintMap().get(AuthRes.getString("Rules")));
		makeText(AuthRes.getString("Rules"), rules);
		list.add(ButtonFactory.makeReverseHBox("Set Rules: ", null, rules));
		
		Button thumbButton = ButtonFactory.makeButton(event -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose Thumbnail Image");
			File file = fc.showOpenDialog(null);
			mcontroller.getConfigMap().put(AuthRes.getStringKeys("key1"), file.getName());
		});
		list.add(ButtonFactory.makeHBox("Select Game Thumbnail", null, thumbButton));
		return list;
	}
	
	/**
	 * Update method called by Loader to update visual aspects of Pane when a new game is loaded
	 */
	public void update(){
		gameName.setText(mcontroller.getGameName());
		author.setText(mcontroller.getPrintMap().get(AuthRes.getString("Author")));
		rules.setText(mcontroller.getPrintMap().get(AuthRes.getString("Rules")));
	}
	
	private void makeText(String key, TextInputControl text){
		text.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER){
				mcontroller.getPrintMap().put(key, text.getText());
			}
		});
	}
	
	private TitledPane LevelOrderer() {
		TitledPane tp = new TitledPane();
		tp.setExpanded(false);
		tp.setText("View Current Levels");
		
		VBox levels = new VBox(AuthRes.getInt("Padding"));
		List<Class<? extends Component<String>>> backNamesThumbs = Arrays.asList(LevelBackgroundComponent.class, LevelNameComponent.class, LevelThumbComponent.class);
		Map<Integer, List<Component<String>>> map = lcontroller.getEngine().getLevelPreviews(backNamesThumbs);
		int levelCount = 0;
		HBox row;
		for (Entry<Integer, List<Component<String>>> ent: map.entrySet()){
			if (levelCount % 2 == 1){
				row = (HBox) levels.getChildren().get(levels.getChildren().size() - 1);
				levels.getChildren().remove(levels.getChildren().size() - 1);
			}
			else{
				row = new HBox(AuthRes.getInt("Padding"));
			}
			List<Component<String>> l = ent.getValue();
			String backPath =  l.get(2).getValue();
			if (backPath.equals(AuthRes.getString("ThumbDefault"))){
				backPath = l.get(0).getValue();
				if (backPath.equals(AuthRes.getString("BackgroundDefault"))){
					backPath = "mountain.png"; // hardcoded default
				}
			}
			String name = l.get(1).getValue();
			Button b = ButtonFactory.makeThumbnail(backPath, name, e -> {
				lcontroller.getEngine().setLevel(ent.getKey());
				activeLevel.setText(lcontroller.getEngine().getLevel().getComponent(LevelNameComponent.class).getValue());
				pcontroller.setBackground(l.get(0).getValue());
				pcontroller.updateCanvas(lcontroller.getEngine().getLevel().getId());
			}, 	AuthRes.getInt("StoryboardThumbWidth"), 
				AuthRes.getInt("StoryboardThumbHeight"));
			b.getStyleClass().add("button-story");
			row.getChildren().add(b);
			levels.getChildren().add(row);
			levelCount++;
		}
		ScrollPane sp = new ScrollPane(levels);
		tp.setContent(sp);
		return tp;
	}
	
	/**
	 * Sets LevelController of the class
	 * @param lc
	 */
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}
	
	/**
	 * Sets MetaController of the class
	 * @param mc
	 */
	public void setMetaController(MetaController mc){
		mcontroller = mc;
	}
	 
	/**
	 * Sets PaneController of the class
	 * @param pc
	 */
	public void setPaneController(PaneController pc){
		pcontroller = pc;
	}
	
}
