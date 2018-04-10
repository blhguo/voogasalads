package authoring.right_components.EntityComponent;

import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

public class EntityPane extends BasePane {
	private EntityController controller;
	private List<ComponentMenu> menuList;
	private Accordion accordion;
	private Group root;
	private StackPane pane;
	public EntityPane(){
		menuList = makeMenuList();
		accordion = makeMenuView();
	}

	@Override
	public Pane getView() {
        VBox box = buildBasicView("Entity Creator");
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
		list.add(controller.getButton());
		return list;
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
		for(ComponentMenu menu : menuList){
			new ComponentFactory().addComponent(entity, menu.getType(), menu.getComponentList());
		}
		return entity;
	}

	public void setController(EntityController controller) {
		this.controller = controller;
	}

	public void updateMenus(Entity entity) {
		accordion.getPanes().clear();
		for (Component comp : entity.getComponents()){
			accordion.getPanes().add(new ComponentMenuFactory().newComponentMenu(
					comp.getValues().split(";"), comp.getName()).getTitledPane());
		}
		//System.out.println("Hit");
	}
}
