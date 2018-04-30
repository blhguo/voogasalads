package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import resources.keys.AuthRes;

public class KeyMenuElement extends MenuElement{
	private Node view;
	private String title;
	private TextField field;
	public KeyMenuElement(String title, Component component){
		setMyComponent(component);
		field = new TextField();
		field.setEditable(false);
		field.setText(component.getValue().toString());
		field.setPrefHeight(10);
		field.setPrefWidth(field.getText().toString().length() * 10 + 20 );
		this.title = title;
		field.setOnKeyPressed(e -> updateComponent(e.getCode(), field.getText(), true));
		view = ButtonFactory.makeReverseHBox(title, null, field, AuthRes.getInt("MenuElementWidth"));
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
		field.setPrefWidth(field.getText().toString().length() * 10 + 20 );
		myComponent.setValue(code.toString());
	}

	@Override
	public void setComponentValue() {
		myComponent.setValue(field.getText());
	}

}
