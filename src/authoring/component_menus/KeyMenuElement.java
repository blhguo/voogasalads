package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import game_engine.ComponentFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import resources.keys.AuthRes;

import java.util.ResourceBundle;

/**
 * @author Liam Pulsifer
 * A menu element for getting KeyCode input
 */
public class KeyMenuElement extends MenuElement<KeyCode>{
	private Node view;
	private String title;
	private TextField field;
	private static final ResourceBundle userNames = ResourceBundle.getBundle("UserFriendlyNames");
	private static final ResourceBundle tooltips = ResourceBundle.getBundle("Tooltips");

	public KeyMenuElement(String title, Component<KeyCode> component){
		setMyComponent(component);
		field = new TextField();
		field.setEditable(false);
		field.setText(component.getValue().toString());
		field.setPrefHeight(AuthRes.getInt("FieldHSpacing"));
		field.setPrefWidth(field.getText().length() 
				* AuthRes.getInt("FieldMultiplier") 
				+ AuthRes.getInt("FieldWSpacing"));
		this.title = title;
		field.setOnKeyPressed(e -> updateComponent(e.getCode(), field.getText(), true));
		view = ButtonFactory.makeReverseHBox(userNames.getString(title),
				null, field, AuthRes.getInt("MenuElementWidth"));
		Tooltip tip = new Tooltip(tooltips.getString(title));
		Tooltip.install(view, tip);
	}

	@Override
	public Node getView() {
		return view;
	}

	@Override
	public String getValue() {
		return field.getText();
	}

	@Override
	public void setValue(Object o) {
		field.setText(o.toString());
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void updateComponent(KeyCode code, String text, boolean alert) {
		field.setText(code.toString());
		field.setPrefWidth(field.getText().length() 
				* AuthRes.getInt("FieldMultiplier") 
				+ AuthRes.getInt("FieldWSpacing"));
		myComponent.setValue(code);
	}

	@Override
	public void setComponentValue() {
		myComponent.setValue(KeyCode.valueOf(field.getText()));
	}

	@Override
	public KeyMenuElement copy(){
		Component<KeyCode> comp;
		try {
			comp = new ComponentFactory().createComponent(
					title, myComponent.getValue().toString());
		}
		catch (NullPointerException e){
			comp = new ComponentFactory().createComponent(title, 
					myComponent.getValue().toString());
		}
		return new KeyMenuElement(title, comp);
	}
}
