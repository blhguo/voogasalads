package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
 * @author liampulsifer
 * A menu element for String input (i.e. file names, etc.)
 */
public class StringMenuElement extends MenuElement{
	TextField field;
	private Node view;
	private String title;
	public StringMenuElement(String title, Component component){
		setMyComponent(component);
		myComponent.setMyMenuElement(this);
		field = new TextField();
		field.setText(component.getValue().toString());
		this.title = title;
		field.setOnKeyPressed(e -> updateComponent(e.getCode(), field.getText()));
		field.focusedProperty().addListener(e -> {
					if (!field.focusedProperty().getValue()) {
						updateComponent(KeyCode.ENTER, field.getText());
					}
		});
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

	@Override
	public void updateComponent(KeyCode code, String text) {
		if (code.equals(KeyCode.ENTER)) {
			myComponent.setValue(text);
			System.out.println("Nice work, here's the new component value: " + myComponent.getValue());
		}
	}

	@Override
	public void alert(Object o ){
		field.setText((String) o );
		myWrapper.updateImage();
		myWrapper.updateSprite();
	}

	@Override
	public void setComponentValue() {
		myComponent.setValue(field.getText());
	}
}
