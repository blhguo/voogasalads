package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;

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
		myComponent.setMyMenuElement(this);
		box = new CheckBox();
		if (!(component.getValue() instanceof Boolean)){
			System.out.println("That's not a bool!");
		}
		box.setSelected((boolean) component.getValue());
		box.setOnKeyPressed(e -> updateComponent(e.getCode(), Boolean.toString(box.isSelected())));
		box.focusedProperty().addListener(e -> {
			if (!box.focusedProperty().getValue()) {
				updateComponent(KeyCode.ENTER, Boolean.toString(box.isSelected()));
			}
		});
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

	@Override
	public void updateComponent(KeyCode code, String text) {
		if (code.equals(KeyCode.ENTER)) {
			try {
				myComponent.setValue(Boolean.parseBoolean(text));
				System.out.println("Nice work, here's the new component value: " + myComponent.getValue());
			} catch (Exception e){
				System.out.println("Sorry, that's not a boolean");
			}
		}
	}
	public void alert(Object o){
		box.setSelected((Boolean) o );
		myWrapper.updateImage();
		myWrapper.updateSprite();
	}

	@Override
	public void setComponentValue() {
		myComponent.setValue(box.isSelected());
	}
}
