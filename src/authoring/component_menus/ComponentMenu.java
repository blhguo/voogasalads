package authoring.component_menus;

import java.util.ArrayList;
import java.util.List;

import authoring.right_components.EntityComponent.EntityPane;
import game_engine.Component;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * @author liampulsifer
 * This interface defines the various kinds of Component Menus which will be elements
 * of the EntityComponent menu in the Authoring Environment
 * Examples of classes which implement this interface might include
 * CollisionMenu, InteractionMenu, etc.
 */

public class ComponentMenu extends VBox implements Comparable{
	private static final String COMPONENT_DELIM  = ";";
	private static final String ATTRIBUTE_DELIM  = ",";
	private List<MenuElement> elements;
	private String myType;
	private boolean included;

	public void setMyPane(EntityPane myPane) {
		this.myPane = myPane;
	}

	private EntityPane myPane;
	public ComponentMenu(String type){
		myType = type;
		elements = new ArrayList<>();
		if (!(type.equals("Position") || type.equals("Sprite"))) {
			included = false;
		}
		else {
			included = true;
		}
		//this.setOnMousePressed(e -> Include());
	}
	public String getMyType() {
		return myType;
	}

	public void setMyType(String myType) {
		this.myType = myType;
	}

	/**
	 * Sets included to true -- makes the component menu be added to a created entity
	 */
	public void Include() {
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
	public void setComponentParent(){
		elements.stream().forEach(e -> e.setMyMenu(this));
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
	public List<Component> getComponentList(){
		List<Component> list = new ArrayList<>();
		elements.stream().forEach(e -> list.add(e.getComponent()));
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
		TitledPane pane = new TitledPane(myType, this);
		pane.setOnMouseClicked(e -> Include());
		return pane;
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

	public void alert() {
		myPane.refresh();
	}

}
