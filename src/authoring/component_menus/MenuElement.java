package authoring.component_menus;

import authoring.right_components.EntityComponent.EntityWrapper;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

/**
 * @author liampulsifer
 * Defines an Element of a ComponentMenu
 * Which creates an input field of the correct type based on a Component's parameters
 * Also maps to a Component
 * and updates that Component's values
 */
public abstract class MenuElement<T>{
	protected ComponentMenu myMenu;
	protected Component<T> myComponent;
	protected boolean dateable = false;
	protected EntityWrapper myWrapper;

	/**
	 * Returns the dateable variable, which determines if the user
	 * should be able to see this component in the display or not
	 * (i.e. if it's a user-editable component or an engine-facing component
	 * @return
	 */
	public boolean isDateable() {
		return dateable;
	}

	/**
	 * Sets the dateable variable
	 * @param dateable
	 */
	public void setDateable(boolean dateable) {
		this.dateable = dateable;
	}

	/**
	 * Sets the wrapper that this element is associated with
	 * @param myWrapper
	 */
	public void setMyWrapper(EntityWrapper myWrapper) {
		this.myWrapper = myWrapper;
	}

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
	 * Sets the value of the input field associated with this menu element
	 * @param o
	 */
	public abstract void setValue(Object o);

	/**
	 *
	 * @return the title of the element
	 */
	public abstract String getTitle();

	/**
	 * Gets the component of this element
	 * @return
	 */
	public Component<T> getComponent() {
		return myComponent;
	}

	/**
	 * Sets the component of this menuElement
	 * @param c
	 */
	public void setMyComponent(Component<T> c){
		myComponent = c;
	}

	/**
	 * Updates the component of this menuElement
	 * @param code
	 * @param text
	 * @param alert
	 */
	public abstract void updateComponent(KeyCode code, String text, boolean alert);

	/**
	 * Sets the menu associated with this menuElement
	 * @param myMenu
	 */
	public void setMyMenu(ComponentMenu myMenu) {
		this.myMenu = myMenu;
	}

	/**
	 * Sets the component's value
	 */
	public abstract void setComponentValue();

	/**
	 * Deep copies this menuElement and returns it
	 * @return
	 */
	public abstract MenuElement copy();
}
