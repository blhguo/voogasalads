package authoring.component_menus;

import authoring.utilities.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public class BooleanMenuElement implements MenuElement{
	private CheckBox box;
	private Node view;
	public BooleanMenuElement(String title, boolean d) {
		box = new CheckBox();
		box.setSelected(d);
		view = ButtonFactory.makeHBox(title, null, box);
	}

	@Override
	public Node getView() {
		return view;
	}

	@Override
	public String getValue() {
		return Boolean.toString(box.isSelected())   ;
	}
}
