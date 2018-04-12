package authoring.right_components.EntityComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import authoring.component_menus.ComponentMenu;
import authoring.component_menus.ComponentMenuFactory;
import authoring.component_menus.ImageMenu;
import authoring.controllers.EntityController;
import authoring.right_components.BasePane;
import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import game_engine.Component;
import game_engine.ComponentFactory;
import game_engine.Entity;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import resources.keys.AuthRes;

public class EntityPane extends BasePane {
	private EntityController controller;
	private List<ComponentMenu> menuList;
	private List<ComponentMenu> ogmenuList;
	private Accordion accordion;
	private Group root;
	private StackPane pane;
	private VBox box;
	private HBox reset;
	private Button createButton;
	private List<HBox> createButtonArray;
	private List<HBox> editButtonArray;
	private VBox newbox;

	public EntityPane(){
		createButtonArray = new ArrayList<>();
		editButtonArray = new ArrayList<>();
		menuList = makeMenuList();
		Collections.sort(menuList);

		ogmenuList = new ArrayList<>(menuList);
//		menuList.stream().forEach(e -> System.out.println(e));
//		ogmenuList.stream().forEach(e -> System.out.println(e));
		accordion = makeMenuView();
	}

	@Override
	public Pane getView() {
        box = buildBasicView("Entity Creator");
		box.getChildren().add(getStack());
		newbox = new VBox();
		newbox.getChildren().addAll(getButtonArray());
		ScrollPane pane = new ScrollPane(newbox);
		pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		pane.setStyle("-fx-background: transparent;");
		pane.setPrefHeight(250);
		pane.setPrefWidth(150);
		box.getChildren().add(pane);
		box.getChildren().addAll(createButtonArray);
		//box.getChildren().addAll(getButtonArray());

	    return box;
	}
	

	public Node getStack() {
		pane = new StackPane();
		pane.getChildren().add(controller.getSprite());
		return pane;
	}

	public List<Node> getButtonArray() {
		List<Node> list = new ArrayList<>();
		list.add(accordion);
		createButton = controller.getButton();
		createButtonArray.add(ButtonFactory.makeHBox("Create Entity", null, controller.getButton()));
		list.addAll(createButtonArray);
		return list;
	}
	private void resetEditButtons(){
		editButtonArray.clear();
		editButtonArray.add(ButtonFactory.makeHBox("Create Entity", null, controller.getButton()));
		Button button = ButtonFactory.makeButton(e -> resetAccordion());
		editButtonArray.add(ButtonFactory.makeHBox("Back to new Entity Creation",
				"Displaying current entity", button));
		editButtonArray.add(ButtonFactory.makeHBox("Delete Entity", null, controller.getRemoveButton()));

	}
	private void resetAccordion() {
		newbox.getChildren().remove(accordion);
		menuList = makeMenuList();
		Collections.sort(menuList);
		accordion = makeMenuView();
		newbox.getChildren().add(accordion);
		box.getChildren().removeAll(editButtonArray);
		box.getChildren().addAll(createButtonArray);
		controller.resetImageViews();
	}


	private List<ComponentMenu> makeMenuList(){
		List<ComponentMenu> list;
		list = new ComponentMenuFactory().getMenus();
		//list.stream().forEach(e -> e.getComponentList().stream().forEach(ev -> System.out.println(ev)));
		return list;
	}
	private Accordion makeMenuView() {
		Accordion acc = new Accordion();
		//acc.getPanes().add(new ImageMenu().getTitledPane());
		//menuList.stream().forEach(e -> e.getComponentList().stream().forEach(ev -> System.out.println(ev)));
		for (ComponentMenu menu : menuList){
			acc.getPanes().add(menu.getTitledPane());
		}
		//acc.getPanes().stream().forEach(e -> System.out.println(e));
		return acc;
	}
	public Entity getEntity(){
		Entity entity = new Entity();
//		menuList.stream().forEach(menu -> System.out.println(menu + " : " + menu.getType() + " : "
//		+ menu.getComponentList().size()));
		//System.out.println("New Entity");
		for(ComponentMenu menu : menuList){
			if (menu.isIncluded())
				new ComponentFactory().addComponent(entity, menu.getType(), menu.getComponentList());
		}
		//menuList = ogmenuList.stream().map(e -> e).collect(Collectors.toList());
		return entity;
	}

	public void setController(EntityController controller) {
		this.controller = controller;
	}
	public List<ComponentMenu> getMenuList(){
		return menuList;
	}
	public void updateMenus(Entity entity) {
		accordion.getPanes().clear();
		menuList.clear();
		for (Component comp : entity.getComponents()){
			ComponentMenu add = new ComponentMenuFactory().newComponentMenu(
					comp.getValues().split(";"), comp.getName());
			add.Include();
			menuList.add(add);
			//accordion.getPanes().add(menuList.get(menuList.size() - 1).getTitledPane());
		}
		Collections.sort(menuList);
		accordion.getPanes().addAll(menuList.stream().map(e -> e.getTitledPane()).collect(Collectors.toList()));

//		menuList.stream().forEach(e -> System.out.println(e));
//		ogmenuList.stream().forEach(e -> System.out.println(e));
		box.getChildren().removeAll(editButtonArray);
		resetEditButtons();
		box.getChildren().removeAll(createButtonArray);
		box.getChildren().addAll(editButtonArray);
		//box.getChildren().remove(accordion);
		//System.out.println("Hit");
	}
}
