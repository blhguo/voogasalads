package authoring.right_components;

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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * StoryboardPane that extends BasePane, which implements GUINode. This class allows 
 * users to rearrange levels and toggle overall game preferences
 */
public class StoryBoardPane extends BasePane {
	
	private LevelController lcontroller;
	private MetaController mcontroller;
	private PaneController pcontroller;
	private Text activeLevel = new Text();

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
		TextField gameName = new TextField(mcontroller.getGameName());
		gameName.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER){
				mcontroller.setGameName(gameName.getText());
			}
		});
		list.add(ButtonFactory.makeReverseHBox("Set Game Name: ", null, gameName));
		
		TextField author = new TextField(mcontroller.getMap().get(AuthRes.getString("author")));
		makeText(AuthRes.getString("author"), author);
		list.add(ButtonFactory.makeReverseHBox("Set Author: ", null, author));
		
		TextArea rules = new TextArea(mcontroller.getMap().get(AuthRes.getString("rules")));
		makeText(AuthRes.getString("rules"), rules);
		list.add(ButtonFactory.makeReverseHBox("Set Rules: ", null, rules));
		return list;
	}
	
	private void makeText(String key, TextInputControl text){
		text.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER){
				mcontroller.getMap().put(key, text.getText());
			}
		});
	}
	
	private TitledPane LevelOrderer() {
		TitledPane tp = new TitledPane();
		tp.setExpanded(false);
		tp.setText("View Current Levels");
		
		VBox levels = new VBox(AuthRes.getInt("Padding"));
		List<Class<? extends Component<?>>> backAndNames = Arrays.asList(LevelBackgroundComponent.class, LevelNameComponent.class);
		Map<Integer, List<Component>> map = lcontroller.getEngine().getLevelPreviews(backAndNames);
		int levelCount = 0;
		HBox row;
		for (Entry<Integer, List<Component>> ent: map.entrySet()){
			if (levelCount % 2 == 1){
				row = (HBox) levels.getChildren().get(levels.getChildren().size() - 1);
				levels.getChildren().remove(levels.getChildren().size() - 1);
			}
			else{
				row = new HBox(AuthRes.getInt("Padding"));
			}
			List<Component> l = ent.getValue();
			String backPath = (String) l.get(0).getValue();
			if (backPath.equals(AuthRes.getString("BackgroundDefault"))){
				backPath = "mountain.png"; // hardcoded default
			}
			String name = (String) l.get(1).getValue();
			Button b = ButtonFactory.makeLevelThumbnail(backPath, name, e -> {
				lcontroller.getEngine().setLevel(ent.getKey());
				activeLevel.setText(lcontroller.getEngine().getLevel().getComponent(LevelNameComponent.class).getValue());
				pcontroller.setBackground((String) l.get(0).getValue());
			});
			b.getStyleClass().add("button-story");
			row.getChildren().add(b);
			levels.getChildren().add(row);
			levelCount++;
		}
		ScrollPane sp = new ScrollPane(levels);
		tp.setContent(sp);
		return tp;
	}
	
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}
	
	public void setMetaController(MetaController mc){
		mcontroller = mc;
	}
	
	public void setPaneController(PaneController pc){
		pcontroller = pc;
	}
	
}
