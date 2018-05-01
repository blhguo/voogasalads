package authoring.component_menus;

import java.util.ResourceBundle;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import game_engine.ComponentFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import resources.keys.AuthRes;

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
			System.out.println("That " + title + "'s not a Double! -- from NumberMenuElement");
			System.out.println(component.getValue());
			if (component.getValue() instanceof Integer){
				component.setValue(((Integer) component.getValue()).doubleValue());
			}
		}

		if (component.getValue() == null) {
			field.setText("IMMUTABLE");
			field.setEditable(false);
		}
		else {
			field.setText(component.getValue().toString());
		}
		field.setOnKeyPressed(e -> updateComponent(e.getCode(), field.getText(), true));
		field.focusedProperty().addListener(e -> {
			if (!field.focusedProperty().getValue()) {
				updateComponent(KeyCode.ENTER, field.getText(), false);
			}
		}
		);
		this.title = title;
		view = ButtonFactory.makeReverseHBox(title, null, field, AuthRes.getInt("MenuElementWidth"));
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

	@Override
	public void setValue(Object o) {
		field.setText(o.toString());
	}

	/**
	 *
	 * @return the title of the input field
	 */
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void updateComponent(KeyCode code, String text, boolean alert) {
		if (code.equals(KeyCode.ENTER)) {
			try {
				if (!text.equals("IMMUTABLE")) {
					myComponent.setValue(Double.parseDouble(text));
					System.out.println("Nice work, here's the new component value: " + myComponent.getValue());
					if (alert) myMenu.alert();
				}
			} catch (NumberFormatException e) {
				field.setText("Sorry, that's not a(n) " + title);
				field.selectAll();
			}
		}
	}


	@Override
	public void setComponentValue() {
		if (!field.getText().equals("IMMUTABLE"))
			myComponent.setValue(Double.parseDouble(field.getText()));
	}

	@Override
	public NumberMenuElement copy(){
		Component comp;
		try {
			comp = new ComponentFactory().createComponent(
					title, myComponent.getValue().toString());
		}
		catch (NullPointerException e){
			comp = new ComponentFactory().createComponent(title, 
					(String) myComponent.getValue());
		}
		NumberMenuElement element = new NumberMenuElement(title, comp);
		return element;
	}

}
