package authoring.component_menus;

import authoring.utilities.ButtonFactory;
import frontend_utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;

/**
 * @author liampulsifer
 * A menu element for numeric input
 */
public class NumberMenuElement implements MenuElement{
	TextField field;
	private Node view;
	private String title;
	public NumberMenuElement(String title, String d){
		field = new TextField();
		field.setText(d);
		this.title = title;
		view = ButtonFactory.makeHBox(title, null, field);
	}

	/**
	 *
	 * @return the HBox with the correct input field inside
	 */
	@Override
	public Node getView() {
		return view;
	}

	/**
	 *
	 * @return  the string value of the input field
	 */
	@Override
	public String getValue() {
		return field.getText();
	}

	/**
	 *
	 * @return the title of the input field
	 */
	@Override
	public String getTitle() {
		return title;
	}

}
