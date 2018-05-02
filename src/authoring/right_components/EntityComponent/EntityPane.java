package authoring.right_components.EntityComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoring.controllers.EntityController;
import authoring.right_components.BasePane;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ImageBuilder;
import game_engine.Entity;
import game_engine.components.sprite.FilenameComponent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class EntityPane extends BasePane{

	private EntityWrapper current;
	private VBox box;
	private VBox menuBox;
	private ImageView sprite;
	private List<HBox> createButtonArray;
	private List<HBox> editButtonArray;
	private Stage stage;

	public void setController(EntityController controller) {
		this.controller = controller;
	}

	private EntityController controller;

	public EntityPane(Stage s){
		current = new EntityWrapper(new Entity(), this);
		stage = s;
	}

	public Pane getView(){
		createButtonArray = instantiateCreateButtonArray();
		editButtonArray = instantiateEditButtonArray();
		box = buildBasicView(AuthRes.getString("EntityTitle"));
		box.getChildren().add(getSprite());
		VBox defaultBox = buildDefaultBox();
		box.getChildren().add(defaultBox);
		menuBox = getMenuBox();
		box.getChildren().add(menuBox);
		box.getChildren().addAll(createButtonArray);
		
		//TODO: Add buttons here for default configs
		
		
		return box;
	}

	private VBox buildDefaultBox() {
		ResourceBundle bundle = ResourceBundle.getBundle("Defaults");
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.getChildren().add(new Label("Default Entities"));
		HBox box = new HBox();
		box.setSpacing(10);
		for (String key : bundle.keySet()){
			Button def = ButtonFactory.makeButton(e -> {
				newWrapper();
				includeAll(Arrays.asList(bundle.getString(key).split(",")));
				current.getEntity().getComponent(FilenameComponent.class).setValue(key + ".png");
//				for (ComponentMenu menu : current.getMenuList()){
//					for (MenuElement element : menu.getElements()){
//						if (element.getTitle().equals("Filename")){
//							element.setValue(key + ".png");
//						}
//					}
//				}
				
				//current.updateImage();
				updateSprite();
				refresh();
			});
			def.setText(key);
			box.getChildren().add(def);
			box.getChildren().add(new Separator(Orientation.VERTICAL));
		}
		vbox.getChildren().add(box);
		return vbox;
	}


	private void includeAll(List<String> list){
		current.getMenuList().stream().forEach(e -> e.unInclude());
		current.getMenuList().stream().filter(e -> list.contains(e.getType()
				.replaceAll(" ", "")))
				.forEach(a -> a.Include());
	}
	private List<HBox> instantiateCreateButtonArray() {
		List<HBox> list = new ArrayList<>();
		list.add(ButtonFactory.makeHBox("Create Entity", null,
				controller.getButton()));
		return list;
	}
	private List<HBox> instantiateEditButtonArray() {
		List<HBox> list = new ArrayList<>();
		//list.add(ButtonFactory.makeHBox("Create Entity", null, controller.getButton()));
		list.add(ButtonFactory.makeHBox("Delete Entity", null, controller.getRemoveButton()));
		list.add(ButtonFactory.makeHBox("Return to new entity creation", null,
				ButtonFactory.makeButton(e -> newWrapper())));

		return list;
	}
	public VBox getMenuBox(){
		VBox newBox = new VBox();
		newBox.setAlignment(Pos.CENTER);
		newBox.setSpacing(10);
		//TODO: Here is where you could filter the ComponentMenus
		newBox.getChildren().addAll(current.getView());
		HBox addBox = ButtonFactory.makeHBox("Add Component", null,
				ButtonFactory.makeButton(e -> newComponent()));
		addBox.setMaxHeight(20);
		addBox.setMaxWidth(20);
		Button addComponentButton = ButtonFactory.makeButton(e -> newComponent());
		newBox.getChildren().add(ButtonFactory.makeHBox("Add New Component", null, addComponentButton));
		return newBox;
	}

	private void newComponent() {
		ComponentSelectionWindow window = new ComponentSelectionWindow(this.getPureCurrent(), this, stage);
		window.display();
	}

	public void updateSprite() {
		sprite.setImage(new Image(current.getEntity().getComponent(FilenameComponent.class).getValue(), 130, 130, true, true));
		ImageBuilder.resize(sprite, 130);
	}

	public Pane getSprite(){
		StackPane pane = new StackPane();
		sprite = ImageBuilder.getImageView(current.getEntity().getComponent(FilenameComponent.class).getValue(),
				130, 130);
		pane.getChildren().add(sprite);
		return pane;
	}

	public EntityWrapper getCurrent() {
		current.getMenuList().stream().forEach(a -> a.getElements().stream()
			.forEach(b -> b.setComponentValue()));
		current.updateImage();
		return current;
	}

	public void clearImageViews() {
		controller.resetImageViews();
	}
	public void setActiveWrapper(EntityWrapper wrapper){
		//controller.listenCanvas();
		box.getChildren().remove(menuBox);
		box.getChildren().removeAll(createButtonArray);
		box.getChildren().removeAll(editButtonArray);
		current = wrapper;
		menuBox = getMenuBox();
		box.getChildren().add(menuBox);
		box.getChildren().addAll(editButtonArray);
		updateSprite();
	}
	public void refresh(){
		controller.updateCanvas();
		current.updateImage();
		box.getChildren().remove(menuBox);
		ArrayList<HBox> list = new ArrayList<>();
		if (box.getChildren().removeAll(createButtonArray)){
			list.addAll(createButtonArray);
		}
		else {
			box.getChildren().removeAll(editButtonArray);
			list.addAll(editButtonArray);
		}
//		box.getChildren().removeAll(createButtonArray);
//		box.getChildren().removeAll(editButtonArray);
		menuBox = getMenuBox();
		box.getChildren().add(menuBox);
		box.getChildren().addAll(list);
//		//controller.resetImageViews();
		updateSprite();
	}
	public void newWrapper(){
		box.getChildren().remove(menuBox);
		box.getChildren().removeAll(createButtonArray);
		box.getChildren().removeAll(editButtonArray);
		current = new EntityWrapper(new Entity(), this);
		menuBox = getMenuBox();
		box.getChildren().add(menuBox);
		box.getChildren().addAll(createButtonArray);
		controller.resetImageViews();
		updateSprite();
	}
	public EntityWrapper getPureCurrent(){
		return current;
	}

	public void newDuplicateEntity() {
		
		box.getChildren().remove(menuBox);
		box.getChildren().removeAll(createButtonArray);
		box.getChildren().removeAll(editButtonArray);
		current = new EntityWrapper(current, this);
		menuBox = getMenuBox();
		box.getChildren().add(menuBox);
		box.getChildren().addAll(createButtonArray);
		controller.resetImageViews();
		updateSprite();
	}
}
