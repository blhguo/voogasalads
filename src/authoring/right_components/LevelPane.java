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
import frontend_utilities.UserFeedback;
import game_engine.Component;
import game_engine.level.LevelBackgroundComponent;
import game_engine.level.LevelHScrollComponent;
import game_engine.level.LevelMusicComponent;
import game_engine.level.LevelNameComponent;
import game_engine.level.LevelThumbComponent;
import game_engine.level.LevelVScrollComponent;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import resources.keys.AuthRes;

/**
 * @author Jennifer Chin
 * Level menu that extends BasePane, which implements GUINode. Gives the user the ability
 * to create a new Level and set properties for that level such as background image. 
 */
public class LevelPane extends BasePane {
	
	private PaneController controller;
	private LevelController lcontroller;
	private Stage stage;
	private ComboBox activeLevels = new ComboBox();
	private TextField textName = new TextField();
	private CheckBox hscroll = new CheckBox();
	private CheckBox vscroll = new CheckBox();
	
	/**
	 * Constructor that takes in a stage to allow for Alerts to let the user remain in full screen
	 * @param s
	 */
	public LevelPane(Stage s){
		stage = s;
	}
	
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
	 * BasePane method that returns the buttons on this Pane. Calls controllers to update the proper panes accordingly
	 * @return List<Node>
	 */
	@Override
	public List<Node> getButtonArray() {
		ArrayList<Node> list = new ArrayList<>();
		List<Object> levelNames = lcontroller.getSingleCompList(LevelNameComponent.class);
 		
		activeLevels = makeActiveLevelList(levelNames);
 		list.add(ButtonFactory.makeReverseHBox("Active Level: ", null, activeLevels));
		
 		textName = makeNameChooser();
		list.add(ButtonFactory.makeReverseHBox("Set Level Name: ", null, textName));
		
		Button backButton = ButtonFactory.makeButton(event -> {
			File file = initFileChooser("Choose Background Image");
			String fname = file.getName();
			controller.setBackground(fname);
			lcontroller.addComp(new LevelBackgroundComponent(fname));
		});
		list.add(ButtonFactory.makeHBox("Select Background", null, backButton));
		
		Button thumbButton = ButtonFactory.makeButton(event -> {
			File file = initFileChooser("Choose Thumbnail Image");
			lcontroller.addComp(new LevelThumbComponent(file.getName()));
		});
		list.add(ButtonFactory.makeHBox("Select Thumbnail", null, thumbButton));
		
		Button musicButton = ButtonFactory.makeButton(event -> {
			File file = initFileChooser("Choose Background Music");
			lcontroller.addComp(new LevelMusicComponent(file.getName()));
		});
		list.add(ButtonFactory.makeHBox("Add Music", null, musicButton));
		
		Button newLevel = ButtonFactory.makeButton(event -> {
			lcontroller.addLevel();
			update();
		});
		list.add(ButtonFactory.makeHBox("Add New Level", null, newLevel));
		
		list.add(makeScrollBoxes());
		return list;

	}
	
	/**
	 * Update method called by buttons on LevelPane so that the other visual aspects of the Pane update in response. Also called by Loader to update the
	 * LevelPane once a new game is chosen.
	 */
	@SuppressWarnings("unchecked")
	public void update(){
		String name = lcontroller.getEngine().getLevel().getComponent(LevelNameComponent.class).getValue();
		activeLevels.setValue(name);
		List<Object> newLevels = lcontroller.getSingleCompList(LevelNameComponent.class);
		activeLevels.setItems(FXCollections.observableArrayList(newLevels));
		textName.setText(activeLevels.getValue().toString());
		String background = lcontroller.getEngine().getLevel().getComponent(LevelBackgroundComponent.class).getValue();
		controller.setBackground(background);
		controller.updateCanvas(lcontroller.getEngine().getLevel().getId());
		hscroll.setSelected(lcontroller.getEngine().getLevel().getComponent(LevelHScrollComponent.class).getValue());
		vscroll.setSelected(lcontroller.getEngine().getLevel().getComponent(LevelVScrollComponent.class).getValue());
	}
	
	@SuppressWarnings("unchecked")
	private ComboBox makeActiveLevelList(List<Object> levelNames){
		activeLevels = new ComboBox(FXCollections.observableArrayList(levelNames));
		//System.out.println(lcontroller.getEngine().getLevel());
		activeLevels.setPromptText(lcontroller.getEngine().getLevel().getComponent(LevelNameComponent.class).getValue());
 		activeLevels.setOnAction(e -> {
 			String chosenLevel = activeLevels.getSelectionModel().getSelectedItem().toString();
 			Map<Integer, List<Component<String>>> map = lcontroller.getEngine().getLevelPreviews(Arrays.asList(LevelNameComponent.class));
 			for (Entry<Integer, List<Component<String>>> ent: map.entrySet()){
 				for (Component c: ent.getValue()){
 					if (c.getValue().equals(chosenLevel)){
 						Integer chosenId = ent.getKey();
 	 					lcontroller.getEngine().setLevel(chosenId);
 					}
 				}
 			}
 			update();
 		});
 		activeLevels.getStyleClass().add("combo-box-auth");
 		return activeLevels;
	}
	
	private TextField makeNameChooser(){
		textName = new TextField(lcontroller.getEngine().getLevel().getComponent(LevelNameComponent.class).getValue());
		textName.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER){
				List<Object> levelNames = lcontroller.getSingleCompList(LevelNameComponent.class);
				String text = textName.getText();
				int count = 0;
				for (Object str: levelNames){
					if (str.toString().equals(text)){
						Alert a = UserFeedback.getErrorMessage(AuthRes.getString("SameLevelHeader"), AuthRes.getString("SameLevelContent"), stage);
						a.showAndWait();
						break;
					}
					count++;
				}
				if (count == levelNames.size()){
					lcontroller.addComp(new LevelNameComponent(text));
				}
				update();
			}
		});
		return textName;
	}
	
	private TitledPane makeScrollBoxes(){
		TitledPane tp = new TitledPane();
		tp.setExpanded(false);
		tp.setText("Choose Scrolling Direction");
		
		VBox checkBoxes = new VBox(AuthRes.getInt("Padding"));
		hscroll.setSelected(lcontroller.getEngine().getLevel().getComponent(LevelHScrollComponent.class).getValue());
		scrollHelper(hscroll, "Toggle Horizontal Scrolling");
		vscroll.setSelected(lcontroller.getEngine().getLevel().getComponent(LevelVScrollComponent.class).getValue());
		scrollHelper(vscroll, "Toggle Vertical Scrolling");
		hscroll.setOnMouseClicked(event -> {
			lcontroller.addComp(new LevelHScrollComponent(hscroll.isSelected()));
			controller.changeScrolling(hscroll.isSelected(), vscroll.isSelected());
			controller.updateCanvas();
		});
		vscroll.setOnMouseClicked(event -> {
			lcontroller.addComp(new LevelVScrollComponent(vscroll.isSelected()));
			controller.changeScrolling(hscroll.isSelected(), vscroll.isSelected());
			//controller.updateCanvas();
		});
		checkBoxes.getChildren().addAll(hscroll, vscroll);
		tp.setContent(checkBoxes);
		return tp;
	}
	
	private CheckBox scrollHelper(CheckBox scroll, String title){
		scroll.setText(title);
		scroll.setAllowIndeterminate(false);
		return scroll;
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
	
}
