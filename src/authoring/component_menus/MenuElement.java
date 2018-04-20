package authoring.component_menus;

import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import observables.Listener;

/**
 * @author liampulsifer
 * Defines an Element of a ComponentMenu
 * Which creates an input field of the correct type based on a Component's parameters
 */
public abstract class MenuElement {
	private Component myComponent;

	/**
	 *
	 * @return a node representing the view of the menuElement
	 *  usually an HBox with some kind of entry field in it
	 */
	public abstract Node getView();

	/**
	 *
	 * @return the value of the input field
	 */
	public abstract String getValue();

	/**
	 *
	 * @return the title of the element
	 */
	public abstract String getTitle();

	public Component getComponent() {
		return myComponent;
	}
	public void setMyComponent(Component c){
		myComponent = c;
	}
}
