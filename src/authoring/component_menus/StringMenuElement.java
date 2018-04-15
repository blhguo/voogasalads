package authoring.component_menus;

import authoring.utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;

/**
 * A menu element for String input (i.e. file names, etc.)
 */
public class StringMenuElement implements MenuElement{
	TextField field;
	private Node view;
	private String title;
	public StringMenuElement(String title, String d){
		field = new TextField();
		field.setText(d);
		this.title = title;
		view = ButtonFactory.makeHBox(title, null, field);
	}

	/**
	 *
	 * @return the HBox with the textfield inside
	 */
	@Override
	public Node getView() {
		return view;
	}

	/**
	 *
	 * @return  the value of the textfield
	 */
	@Override
	public String getValue(){
		return field.getText();
	}

	/**
	 *
	 * @return the title of the element
	 */
	@Override
	public String getTitle() {
		return title;
	}
}
