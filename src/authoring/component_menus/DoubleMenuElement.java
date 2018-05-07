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
 * A menu element for numeric input
 */
public class DoubleMenuElement extends MenuElement<Double>{
	TextField field;
	private Node view;
	private String title;
	private static final ResourceBundle userNames = ResourceBundle.getBundle("UserFriendlyNames");
	private static final ResourceBundle tooltips = ResourceBundle.getBundle("Tooltips");

	public DoubleMenuElement(String title, Component<Double> component){
		setMyComponent(component);
		field = new TextField();

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
		try {
			view = ButtonFactory.makeReverseHBox(userNames.getString(title),
					null, field, AuthRes.getInt("MenuElementWidth"));
		}
		catch (Exception e){
			view = ButtonFactory.makeReverseHBox(title,
					null, field, AuthRes.getInt("MenuElementWidth"));
		}
		try {
			Tooltip tip = new Tooltip(tooltips.getString(title));
			Tooltip.install(view, tip);
		} catch (Exception a){
			System.out.println("No tooltip found for: " + title);
			//In this instance, it's not that important that the tooltip actually throw
			//an exception -- we'd rather the program run well
		}
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
					if (alert) {
						myMenu.alert();
					}
				}
			} catch (NumberFormatException e) {
				field.setText("Sorry, that's not a(n) " + title);
				field.selectAll();
			}
		}
	}


	@Override
	public void setComponentValue() {
		if (!field.getText().equals("IMMUTABLE")) {
			myComponent.setValue(Double.parseDouble(field.getText()));
		}
	}

	@Override
	public DoubleMenuElement copy(){
		Component<Double> comp;
		try {
			comp = new ComponentFactory().createComponent(
					title, myComponent.getValue().toString());
		}
		catch (NullPointerException e){
			comp = new ComponentFactory().createComponent(title, 
					myComponent.getValue().toString());
		}
		return new DoubleMenuElement(title, comp);
	}

}
