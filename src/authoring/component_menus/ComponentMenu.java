package authoring.component_menus;

import game_engine.Component;
import game_engine.ComponentFactory;
import game_engine.components.CollidableComponent;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This interface defines the various kinds of Component Menus which will be elements
 * of the EntityPane menu in the Authoring Environment
 * Examples of classes which implement this interface might include
 * CollisionMenu, InteractionMenu, etc.
 */

public class ComponentMenu extends VBox{
	private static final String COMPONENT_BUNDLE = "Component";
	private static final String COMPONENT_DELIM  = ";";
	private static final String ATTRIBUTE_DELIM  = ",";

	private ResourceBundle myComponents;
	private List<MenuElement> elements;
	private String myType;
	public ComponentMenu(String type){
		myComponents = ResourceBundle.getBundle(COMPONENT_BUNDLE);
		myType = type;
		elements = new ArrayList<>();
	}
	public void addMenuElement(MenuElement element){
		elements.add(element);
		this.getChildren().add(element.getView());
	}
	public List<String> getComponentList(){
		List<String> list = new ArrayList<String>();
		elements.stream().forEach(e -> list.add(e.getValue()));
		return list;
	}

	public String getType() {
		return myType;
	}

	public TitledPane getTitledPane() {
		return new TitledPane(myType, this);
	}
}
