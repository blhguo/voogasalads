package authoring.right_components;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import authoring.controllers.LevelController;
import authoring.controllers.PaneController;
import frontend_utilities.ButtonFactory;
import game_engine.Component;
import game_engine.level.LevelBackgroundComponent;
import game_engine.level.LevelMusicComponent;
import game_engine.level.LevelNameComponent;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
	private Stage stage;
	private VBox box;

	public LevelPane(Stage s){
		stage = s;
	}
	
	/**
	 * GUINode method that returns the view of this Pane
	 * @return Pane
	 */
	@Override
	public Pane getView() {
		box = buildBasicView(AuthRes.getString("LevelTitle"));
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
		ArrayList<Object> levelNames = lcontroller.getSingleCompList(LevelNameComponent.class);
 		
		ComboBox activeLevels = makeActiveLevelList(levelNames);
 		list.add(ButtonFactory.makeReverseHBox("Active Level: ", null, activeLevels));
		
 		TextField name = makeNameChooser(levelNames);
		list.add(ButtonFactory.makeReverseHBox("Set Level Name: ", null, name));
		
		Button backButton = ButtonFactory.makeButton(event -> {
			File file = initFileChooser("Choose Background Image");
			String fname = file.getName();
			controller.setBackground(fname);
			// may need to add /images in front of image name
			lcontroller.addComp(new LevelBackgroundComponent(fname));
		});
		list.add(ButtonFactory.makeHBox("Select Background", null, backButton));
		
		Button musicButton = ButtonFactory.makeButton(event -> {
//			String musicFile = "resources/Gods Plan.mp3";
//			Media sound = new Media(new File(musicFile).toURI().toString());
//			MediaPlayer mediaPlayer = new MediaPlayer(sound);
//			mediaPlayer.setStopTime(new Duration(3000));
//			mediaPlayer.play();
			File file = initFileChooser("Choose Background Music");
			lcontroller.addComp(new LevelMusicComponent(file.getName()));
		});
		list.add(ButtonFactory.makeHBox("Add Music", null, musicButton));
		
		Button newLevel = ButtonFactory.makeButton(event -> {
			lcontroller.addLevel();
			//also needs to update activeLevel text
			updateLabels(activeLevels, name);
		});
		list.add(ButtonFactory.makeHBox("Add New Level", null, newLevel));
		
		return list;

	}
	
	private void updateLabels(ComboBox cb, TextField tf){
		String name = lcontroller.getEngine().getLevel().getComponent(LevelNameComponent.class).getValue();
		cb.setPromptText(name);
		tf.setText(name);
		controller.setBackground(lcontroller.getEngine().getLevel().getComponent(LevelBackgroundComponent.class).getValue());
	}
	
	private ComboBox makeActiveLevelList(ArrayList<Object> levelNames){
		ComboBox activeLevels = new ComboBox(FXCollections.observableArrayList(levelNames));
 		activeLevels.setPromptText(lcontroller.getEngine().getLevel().getComponent(LevelNameComponent.class).getValue());
 		Map<Integer, List<Component>> map = lcontroller.getEngine().getLevelPreviews(Arrays.asList(LevelNameComponent.class));
 		activeLevels.setOnAction(e -> {
 			String chosenLevel = activeLevels.getSelectionModel().getSelectedItem().toString();
 			for (Entry<Integer, List<Component>> ent: map.entrySet()){
 				if(ent.getValue().equals(chosenLevel)){
 					Integer chosenId = ent.getKey();
 					lcontroller.getEngine().setLevel(chosenId);
 				}
 			}
 		});
 		activeLevels.getStyleClass().add("combo-box-auth");
 		return activeLevels;
	}
	
	private TextField makeNameChooser(ArrayList<Object> levelNames){
		TextField name = new TextField(lcontroller.getEngine().getLevel().getComponent(LevelNameComponent.class).getValue());
		name.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER){
				String text = name.getText();
				int count = 0;
				for (Object str: levelNames){
					if (str.toString().equals(text)){
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle(AuthRes.getString("ErrorTitle"));
						a.setHeaderText(AuthRes.getString("SameLevelHeader"));
						a.setContentText(AuthRes.getString("SameLevelContent"));
						a.initOwner(stage);
						a.showAndWait();
						break;
					}
					count++;
				}
				if (count == levelNames.size()){
					lcontroller.addComp(new LevelNameComponent(text));
				}
			}
		});
		return name;
	}
	
	private File initFileChooser(String title){
		FileChooser fc = new FileChooser();
		fc.setTitle(title);
		return fc.showOpenDialog(null);
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
	
	// need a method that updates values displayed
}
