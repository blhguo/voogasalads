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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

public class EntityPane extends BasePane {
	private EntityController controller;
	private List<ComponentMenu> menuList;
	private List<ComponentMenu> ogmenuList;
	private Accordion accordion;
	private Group root;
	private StackPane pane;
	private VBox box;
	public EntityPane(){

		menuList = makeMenuList();
		ogmenuList = new ArrayList<>(menuList);
//		menuList.stream().forEach(e -> System.out.println(e));
//		ogmenuList.stream().forEach(e -> System.out.println(e));
		accordion = makeMenuView();
	}

	@Override
	public Pane getView() {
        box = buildBasicView("Entity Creator");
		box.getChildren().add(getStack());
		box.getChildren().addAll(getButtonArray());

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
		list.add(ButtonFactory.makeHBox("Create Entity", null, controller.getButton()));
		return list;
	}

	private void resetAccordion() {
		menuList = makeMenuList();
		accordion = makeMenuView();
		box.getChildren().remove(box.getChildren().size() - 1);
		box.getChildren().add(ButtonFactory.makeHBox("Create Entity", null, controller.getButton()));
		controller.resetImageViews();
	}


	private List<ComponentMenu> makeMenuList(){
		List<ComponentMenu> list;
		list = new ComponentMenuFactory().getMenus();
		return list;
	}
	private Accordion makeMenuView() {
		Accordion acc = new Accordion();
		//acc.getPanes().add(new ImageMenu().getTitledPane());
		acc.getPanes().addAll(
				menuList.stream().map(x -> x.getTitledPane()).collect(Collectors.toList()));
		return acc;
	}
	public Entity getEntity(){
		Entity entity = new Entity();
//		menuList.stream().forEach(menu -> System.out.println(menu + " : " + menu.getType() + " : "
//		+ menu.getComponentList().size()));
		System.out.println("New Entity");
		for(ComponentMenu menu : menuList){
			new ComponentFactory().addComponent(entity, menu.getType(), menu.getComponentList());
		}
		//menuList = ogmenuList.stream().map(e -> e).collect(Collectors.toList());
		return entity;
	}

	public void setController(EntityController controller) {
		this.controller = controller;
	}

	public void updateMenus(Entity entity) {
		accordion.getPanes().clear();
		menuList.clear();
		for (Component comp : entity.getComponents()){
			menuList.add(new ComponentMenuFactory().newComponentMenu(
					comp.getValues().split(";"), comp.getName()));
			accordion.getPanes().add(menuList.get(menuList.size() - 1).getTitledPane());
		}
//		menuList.stream().forEach(e -> System.out.println(e));
//		ogmenuList.stream().forEach(e -> System.out.println(e));

		box.getChildren().remove(box.getChildren().size() - 1);

		Button reset = ButtonFactory.makeButton(e -> resetAccordion());
		box.getChildren().add((ButtonFactory.makeHBox("Go back to new Entity Creation",
				"Displaying current entity", reset)));
		//box.getChildren().remove(accordion);
		//System.out.println("Hit");
	}
}
