package authoring.right_components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import authoring.controllers.LevelController;
import frontend_utilities.ButtonFactory;
import game_engine.level.LevelNameComponent;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * StoryboardPane that extends BasePane, which implements GUINode. This class allows 
 * users to rearrange levels and toggle overall game preferences
 */
public class StoryBoardPane extends BasePane {
	
	private LevelController lcontroller;

	/**
	 * GUINode method that returns the view of this Pane
	 * @return Pane
	 */
	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("StoryTitle"));
		box.getChildren().addAll(LevelOrderer(), newSeparator());
		getButtonArray().stream().map((button) -> box.getChildren().add(button)).collect(Collectors.toList());
		return box;
	}

	/**
	 * BasePane method that returns the buttons on this pane
	 */
	@Override 
	public List<Node> getButtonArray(){
		ArrayList<Node> list = new ArrayList<>();
		list.add(ButtonFactory.makeHBox("Change Game Name", null));
		return list;
	}
	
	private TitledPane LevelOrderer() {
		TitledPane tp = new TitledPane();
		tp.setExpanded(false);
		tp.setText("View Current Levels");
		//tp.setContent(new ListView<String>());
		tp.getStyleClass().add("titled-pane > .title");
		
		VBox levels = new VBox(AuthRes.getInt("Padding"));
		ArrayList<Object> levelNames = lcontroller.getSingleCompList(LevelNameComponent.class);
		
		return tp;
	}
	
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}
	
}
