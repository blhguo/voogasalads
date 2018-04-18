package authoring.right_components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import authoring.controllers.LevelController;
import authoring.controllers.PaneController;
import frontend_utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * @author Jennifer Chin
 * Level menu that extends BasePane, which implements GUINode. Gives the user the ability
 * to create a new Level and set properties for that level such as background image. 
 */
public class LevelPane extends BasePane {
	
	private PaneController controller;
	private LevelController lcontroller;

	/**
	 * GUINode method that returns the view of this Pane
	 * @return Pane
	 */
	@Override
	public Pane getView() {
		VBox box = buildBasicView(AuthRes.getString("LevelTitle"));
		box.getChildren().addAll(getButtonArray());
		return box;
	}

	/**
	 * BasePane method that returns the buttons on this Pane
	 * @return List<Node>
	 */
	@Override
	public List<Node> getButtonArray() {
		ArrayList<Node> list = new ArrayList<>();
		Button backButton = ButtonFactory.makeButton(event -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose Background Image");
			File file = fc.showOpenDialog(null);
			controller.setBackground(file);
		});
		list.add(ButtonFactory.makeHBox("Select Background", null, backButton));
		return list;

	}
	
	/**
	 * Allows the PaneController to be set for this Pane. PaneController allows the 
	 * LevelPane to communicate with the Canvas so that the background image can be
	 * changed
	 * @param pc
	 */
	public void setController(PaneController pc){
		controller = pc;
	}
	
	/**
	 * Allows the LevelController to be set for this Pane. LevelController will allow
	 * the LevelPane to properly instantiate a new Level.
	 * @param lc
	 */
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}
	
	//eventually we will need to have some button similar to entity
	//pane where when clicked it initializes a new level
}
