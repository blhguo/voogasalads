package authoring.component_menus;

import authoring.right_components.EntityComponent.EntityWrapper;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

/**
 * @author liampulsifer
 * Defines an Element of a ComponentMenu
 * Which creates an input field of the correct type based on a Component's parameters
 */
public abstract class MenuElement<T>{
	protected ComponentMenu myMenu;
	protected Component<T> myComponent;

	public boolean isDateable() {
		return dateable;
	}

	public void setDateable(boolean dateable) {
		this.dateable = dateable;
	}

	protected boolean dateable = false;

	public void setMyWrapper(EntityWrapper myWrapper) {
		this.myWrapper = myWrapper;
	}

	protected EntityWrapper myWrapper;

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

	public abstract void setValue(Object o);

	/**
	 *
	 * @return the title of the element
	 */
	public abstract String getTitle();

	public Component<T> getComponent() {
		return myComponent;
	}
	public void setMyComponent(Component<T> c){
		myComponent = c;
	}

	public abstract void updateComponent(KeyCode code, String text, boolean alert);

	public void setMyMenu(ComponentMenu myMenu) {
		this.myMenu = myMenu;
	}

	public abstract void setComponentValue();

	public abstract MenuElement copy();
}
