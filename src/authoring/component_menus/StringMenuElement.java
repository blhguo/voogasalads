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
 * @author liampulsifer
 * A menu element for String input
 */
public class StringMenuElement extends MenuElement<String>{
	private TextField field;
	private Node view;
	private String title;
	private static final ResourceBundle userNames = ResourceBundle.getBundle("UserFriendlyNames");
	private static final ResourceBundle tooltips = ResourceBundle.getBundle("Tooltips");

	public StringMenuElement(String title, Component<String> component){
		setMyComponent(component);
		field = new TextField();
		if (!(component.getValue() == null)){
			System.out.println(component);
			System.out.println(component.getValue());
			field.setText(component.getValue().toString());
		}
		else { 
			field.setText("IMMUTABLE");
			field.setEditable(false);
		}
		this.title = title;
		field.setOnKeyPressed(e -> updateComponent(e.getCode(), field.getText(), true));
		field.focusedProperty().addListener(e -> {
					if (!field.focusedProperty().getValue()) {
						updateComponent(KeyCode.ENTER, field.getText(), false);
					}
		});
		try {
			view = ButtonFactory.makeReverseHBox(userNames.getString(title), null, field,
				AuthRes.getInt("MenuElementWidth"));
		}
		catch (Exception e){
			view = ButtonFactory.makeReverseHBox(title, null, field,
				AuthRes.getInt("MenuElementWidth"));
		}
		try {
			Tooltip tip = new Tooltip(tooltips.getString(title));
			Tooltip.install(view, tip);
		}catch (Exception c){
			
		}
		
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
		if (code.equals(KeyCode.ENTER) && !text.equals("IMMUTABLE")) {
				myComponent.setValue(text);
				if (alert) {
					myMenu.alert();
				}
				System.out.println("Nice work, here's the new component value: " + myComponent.getValue());

			}
	}

	@Override
	public void setComponentValue() {
		if (!field.getText().equals("IMMUTABLE")) {
			myComponent.setValue(field.getText());
		}
	}
	@Override
	public StringMenuElement copy(){
		Component<String> comp;
		try {
			comp = new ComponentFactory().createComponent(
					title, myComponent.getValue());
		}
		catch (NullPointerException e){
			comp = new ComponentFactory().createComponent(title, 
					myComponent.getValue());
		}
		return new StringMenuElement(title, comp);
	}
}
