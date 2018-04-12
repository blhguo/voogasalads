package authoring.component_menus;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * @author liampulsifer
 * Defines an Element of a ComponentMenu
 * Which creates an input field of the correct type based on a Component's parameters
 */
public interface MenuElement {

	/**
	 *
	 * @return a node representing the view of the menuElement
	 *  usually an HBox with some kind of entry field in it
	 */
	Node getView();

	/**
	 *
	 * @return the value of the input field
	 */
	String getValue();

	/**
	 *
	 * @return the title of the element
	 */
	String getTitle();
}
