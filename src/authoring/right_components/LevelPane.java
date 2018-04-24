package authoring.right_components;

import java.awt.event.KeyEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import authoring.controllers.LevelController;
import authoring.controllers.PaneController;
import frontend_utilities.ButtonFactory;
import game_engine.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
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
		
//		ArrayList<String> levels = lcontroller.getLevels().stream().map(l -> l.getName()).collect(Collectors.toList());
//		ComboBox activeLevels = new ComboBox(FXCollections.observableArrayList(levels));
//		activeLevels.setPromptText(lcontroller.getActiveLevel().getName());
//		activeLevels.setOnAction(e -> {
//			String chosenLevel = activeLevels.getSelectionModel().getSelectedItem().toString();
//			//get Level object from chosenLevel name
//			lcontroller.setActiveLevel(Level from name);
//		});
//		activeLevels.getStyleClass().add("combo-box-auth");
//		list.add(ButtonFactory.makeReverseHBox("Active Level: ", null, activeLevels));
		
 		ComboBox tempLevels = new ComboBox();
 		tempLevels.getItems().addAll("Level 1", "Level 2");
 		tempLevels.setPromptText("Active Level Name");
 		tempLevels.getStyleClass().add("combo-box-auth");
 		list.add(ButtonFactory.makeReverseHBox("Active Level: ", null, tempLevels));
		
		TextField name = new TextField(AuthRes.getString("LevelNameDefault"));
		name.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER){
				String text = name.getText();
				//use level manager class to actually save this text as name
			}
		});
		list.add(ButtonFactory.makeReverseHBox("Set Level Name: ", null, name));
		
		Button backButton = ButtonFactory.makeButton(event -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose Background Image");
			File file = fc.showOpenDialog(null);
			controller.setBackground(file);
		});
		list.add(ButtonFactory.makeHBox("Select Background", null, backButton));
		
		Button musicButton = ButtonFactory.makeButton(event -> {
			String musicFile = "resources/Gods Plan.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setStopTime(new Duration(3000));
			mediaPlayer.play();
		});
		list.add(ButtonFactory.makeHBox("Add Music", null, musicButton));
		
		Button newLevel = ButtonFactory.makeButton(event -> {
			lcontroller.addLevel(new Level());
			//also needs to update activeLevel text
		});
		list.add(ButtonFactory.makeHBox("Add New Level", null, newLevel));
		
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
