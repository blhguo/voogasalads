package authoring.component_menus;

import authoring.utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class StringMenuElement implements MenuElement{
	TextField field;
	private Node view;
	public StringMenuElement(String title, String d){
		field = new TextField();
		field.setText(d);
		view = ButtonFactory.makeHBox(title, null, field);
	}

	@Override
	public Node getView() {
		return view;
	}
	@Override
	public String getValue(){
		return field.getText();
	}
}
