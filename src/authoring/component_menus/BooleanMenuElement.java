package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import game_engine.ComponentFactory;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import resources.keys.AuthRes;

import java.util.ResourceBundle;

/**
 * @author liampulsifer
 * Boolean checkbox input element
 */
public class BooleanMenuElement extends MenuElement<Boolean>{
	private CheckBox box;
	private Node view;
	private String title;
	
	private static final ResourceBundle userNames = ResourceBundle.getBundle("UserFriendlyNames");
	private static final ResourceBundle tooltips = ResourceBundle.getBundle("Tooltips");

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
		view = ButtonFactory.makeReverseHBox(userNames.getString(title),
				null, box, AuthRes.getInt("MenuElementWidth"));
		Tooltip tip = new Tooltip(tooltips.getString(title));
		Tooltip.install(view, tip);
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
				if (alert) {
					myMenu.alert();
				}
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

	@Override
	public BooleanMenuElement copy(){
		Component comp;
		try {
			comp = new ComponentFactory().createComponent(
					title, myComponent.getValue().toString());
		}
		catch (NullPointerException e){
			comp = new ComponentFactory().createComponent(title, 
					myComponent.getValue().toString());
		}
		return new BooleanMenuElement(title, comp);
	}
}
