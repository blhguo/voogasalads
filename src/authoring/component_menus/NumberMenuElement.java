package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.TextField;

/**
 * @author liampulsifer
 * A menu element for numeric input
 */
public class NumberMenuElement extends MenuElement{
	TextField field;
	private Node view;
	private String title;
	public NumberMenuElement(String title, Component component){
		setMyComponent(component);
		field = new TextField();
		if (!(component.getValue() instanceof Double)) {
			System.out.println("That's not a Double!");
			if (component.getValue() instanceof Integer){
				component.setValue(((Integer) component.getValue()).doubleValue());
			}
		}
		field.setText(Double.toString((Double) component.getValue()));
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
