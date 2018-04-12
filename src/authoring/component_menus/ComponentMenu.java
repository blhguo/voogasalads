package authoring.component_menus;

import game_engine.Component;
import game_engine.ComponentFactory;
//import game_engine.components.CollidableComponent;
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
 * of the EntityComponent menu in the Authoring Environment
 * Examples of classes which implement this interface might include
 * CollisionMenu, InteractionMenu, etc.
 */

public class ComponentMenu extends VBox implements Comparable{
	private static final String COMPONENT_BUNDLE = "Component";
	private static final String COMPONENT_DELIM  = ";";
	private static final String ATTRIBUTE_DELIM  = ",";
	private boolean included;
	private ResourceBundle myComponents;
	private List<MenuElement> elements;
	private String myType;
	public ComponentMenu(String type){
		myComponents = ResourceBundle.getBundle(COMPONENT_BUNDLE);
		myType = type;
		elements = new ArrayList<>();
		if (!(type.equals("Position") || type.equals("Sprite"))) {
			included = false;
		}
		else {
			included = true;
		}
		this.setOnMousePressed(e -> Include());
	}

	/**
	 * Sets included to true -- makes the component menu be added to a created entity
	 */
	private void Include() {
		included = true;
		System.out.println("Menu included");
	}

	/**
	 *
	 * @return included
	 */
	public boolean isIncluded(){
		return included;
	}

	/**
	 *
	 * @return the list of all menuElements
	 */
	public List<MenuElement> getElements(){
		return elements;
	}

	/**
	 * Adds a new MenuElement to the list
	 * @param element the menuElement (i.e. entry field) to be added
	 */
	public void addMenuElement(MenuElement element){
		elements.add(element);
		this.getChildren().add(element.getView());
	}

	/**
	 * Used to get the list of parameters to input to the constructor of each component
	 * @return the list of values of each component
	 */
	public List<String> getComponentList(){
		List<String> list = new ArrayList<String>();
		elements.stream().forEach(e -> list.add(e.getValue()));
		return list;
	}

	/**
	 *
	 * @return the type of the menu
	 */
	public String getType() {
		return myType;
	}

	/**
	 * Converts this object into a titledpane for use in EntityPane
	 * @return the TitledPane
	 */
	public TitledPane getTitledPane() {
		//this.getChildren().stream().forEach(item -> System.out.println(item));
		TitledPane myPane = new TitledPane(myType, this);
		myPane.setOnMouseClicked(e -> Include());
		return myPane;
	}

	/**
	 *
	 * @param o object to be compared
	 * @return returns 1 if this is greater than object
	 */
	@Override
	public int compareTo(Object o) {
		return this.getType().compareTo(o.toString());
	}
}
