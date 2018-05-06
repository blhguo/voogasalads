package authoring.right_components.EntityComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import authoring.component_menus.ComponentMenu;
import authoring.component_menus.MenuElement;
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
	private EntityController controller;

	public EntityPane(Stage s){
		current = new EntityWrapper(this);
		stage = s;
	}
	
	public void setController(EntityController controller) {
		this.controller = controller;
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
		vbox.setSpacing(AuthRes.getInt("VBPadding"));
		Label l = new Label("Default Entities");
		l.getStyleClass().add("event-label");
		vbox.getChildren().add(l);
		HBox box = new HBox();
		box.setSpacing(AuthRes.getInt("HBPadding"));
		for (String key : bundle.keySet()){
			System.out.println("KEY: " + key);
			Button def = ButtonFactory.makeButton(e -> {
				//newWrapper();
				includeAll(Arrays.asList(bundle.getString(key).split(",")));
				current.getEntity().getComponent(FilenameComponent.class).setValue(key + ".png");
				updateSprite();
				refresh();
			});
			def.setText(key);
			def.getStyleClass().add("button-entity");
			box.getChildren().add(def);
			box.getChildren().add(new Separator(Orientation.VERTICAL));
		}
		box.getChildren().remove(box.getChildren().size() - 1);
		vbox.getChildren().add(box);
		return vbox;
	}


	private void includeAll(List<String> list){
		newWrapper();
		current.getMenuList().stream().forEach(e -> e.unInclude());
		current.getMenuList().stream().filter(e -> list.contains(e.getType()
				.replaceAll(" ", "")))
				.forEach(a -> a.Include());
	}
	private List<HBox> instantiateCreateButtonArray() {
		List<HBox> list = new ArrayList<>();
		HBox newBox = new HBox();
		Label l = new Label("Click the screen \nto create a new entity!");
		l.getStyleClass().add("event-label2");
		newBox.getChildren().add(l);
		list.add(newBox);
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
		newBox.setSpacing(AuthRes.getInt("HBPadding"));
		//TODO: Here is where you could filter the ComponentMenus
		newBox.getChildren().addAll(current.getView());
		HBox addBox = ButtonFactory.makeHBox("Add Component", null,
				ButtonFactory.makeButton(e -> newComponent()));
		addBox.setMaxHeight(AuthRes.getInt("MenuBoxSizing"));
		addBox.setMaxWidth(AuthRes.getInt("MenuBoxSizing"));
		Button addComponentButton = ButtonFactory.makeButton(e -> newComponent());
		newBox.getChildren().add(ButtonFactory.makeHBox("Add New Component", null, addComponentButton));
		return newBox;
	}

	private void newComponent() {
		ComponentSelectionWindow window = new ComponentSelectionWindow(this.getPureCurrent(), this, stage);
		window.display();
	}

	public void updateSprite() {
		sprite.setImage(new Image(current.getEntity().getComponent(FilenameComponent.class).getValue(), 
				AuthRes.getInt("EntityPaneSprite"), 
				AuthRes.getInt("EntityPaneSprite"), true, true));
		ImageBuilder.resize(sprite, AuthRes.getInt("EntityPaneSprite"));
	}

	public Pane getSprite(){
		StackPane pane = new StackPane();
		sprite = ImageBuilder.getImageView(current.getEntity().getComponent(FilenameComponent.class).getValue(),
				AuthRes.getInt("EntityPaneSprite"), 
				AuthRes.getInt("EntityPaneSprite"));
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
		current.addAllComponents(current.getEntity());
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
	
	public void load(List<EntityWrapper> newEntList){
		newEntList.stream().forEach(				e -> {
			e.getMenuList().stream().forEach(a -> a.setMyPane(this));
			controller.add(e);
		});
		controller.updateCanvas(controller.getEntities());
		getView();
		controller.updateDummies();
	}
	
	public void newWrapper(){
		box.getChildren().remove(menuBox);
		box.getChildren().removeAll(createButtonArray);
		box.getChildren().removeAll(editButtonArray);
		current = new EntityWrapper(this);
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
