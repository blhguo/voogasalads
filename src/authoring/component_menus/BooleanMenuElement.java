package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import resources.keys.AuthRes;

/**
 * @author liampulsifer
 * Boolean checkbox input element
 */
public class BooleanMenuElement extends MenuElement{
	private CheckBox box;
	private Node view;
	private String title;
	public BooleanMenuElement(String title, Component component) {
		setMyComponent(component);
		box = new CheckBox();
		if (!(component.getValue() instanceof Boolean)){
			System.out.println("That's not a bool!");
		}
		box.setSelected((boolean) component.getValue());
		box.setOnKeyPressed(e -> updateComponent(e.getCode(), Boolean.toString(box.isSelected()), true));
		box.focusedProperty().addListener(e -> {
			if (!box.focusedProperty().getValue()) {
				updateComponent(KeyCode.ENTER, Boolean.toString(box.isSelected()), false);
			}
		});
		this.title = title;
		view = ButtonFactory.makeReverseHBox(title, null, box, AuthRes.getInt("MenuElementWidth"));
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

	@Override
	public void setValue(Object o) {
		box.setSelected(Boolean.parseBoolean(o.toString()));
	}

	/**
	 *
	 * @return the title of the text entry box (i.e. Collidable or something)
	 */
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void updateComponent(KeyCode code, String text, boolean alert) {
		if (code.equals(KeyCode.ENTER)) {
			try {
				myComponent.setValue(Boolean.parseBoolean(text));
				if (alert) myMenu.alert();
				System.out.println("Nice work, here's the new component value: " + myComponent.getValue());
			} catch (Exception e){
				System.out.println("Sorry, that's not a boolean");
			}
		}
	}

	@Override
	public void setComponentValue() {
		myComponent.setValue(box.isSelected());
	}

}
