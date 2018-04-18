package authoring.component_menus;

import authoring.utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

/**
 * @author liampulsifer
 * Boolean checkbox input element
 */
public class BooleanMenuElement implements MenuElement{
	private CheckBox box;
	private Node view;
	private String title;
	public BooleanMenuElement(String title, boolean d) {
		box = new CheckBox();
		box.setSelected(d);
		this.title = title;
		view = ButtonFactory.makeHBox(title, null, box);
	}

	/**
	 *
	 * @return the CheckBox entry field in an HBox
	 */
	@Override
	public Node getView() {
		return view;
	}

	/**
	 *
	 * @return the boolean value of whether the box is checked
	 */
	@Override
	public String getValue() {
		return Boolean.toString(box.isSelected())   ;
	}

	/**
	 *
	 * @return the title of the text entry box (i.e. Collidable or something)
	 */
	@Override
	public String getTitle() {
		return title;
	}

}
