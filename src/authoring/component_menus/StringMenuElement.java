package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author liampulsifer
 * A menu element for String input (i.e. file names, etc.)
 */
public class StringMenuElement extends MenuElement{
	private TextField field;
	private Node view;
	private String title;
	public StringMenuElement(String title, Component component){
		setMyComponent(component);
		field = new TextField();
		field.setText(component.getValue().toString());
		this.title = title;
		field.setOnKeyPressed(e -> updateComponent(e.getCode(), field.getText(), true));
		field.focusedProperty().addListener(e -> {
					if (!field.focusedProperty().getValue()) {
						updateComponent(KeyCode.ENTER, field.getText(), false);
					}
		});
		view = ButtonFactory.makeReverseHBox(title, null, field);
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

	@Override
	public void setValue(Object o) {
		field.setText(o.toString());
	}

	/**
	 *
	 * @return the title of the element
	 */
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void updateComponent(KeyCode code, String text, boolean alert) {
		if (code.equals(KeyCode.ENTER)) {
			myComponent.setValue(text);
			if (alert) myMenu.alert();
			System.out.println("Nice work, here's the new component value: " + myComponent.getValue());
		}
	}
	

	@Override
	public void setComponentValue() {
		myComponent.setValue(field.getText());
	}
}
