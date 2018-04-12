package authoring.component_menus;

import authoring.utilities.ButtonFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class NumberMenuElement implements MenuElement{
	TextField field;
	private Node view;
	private String title;
	public NumberMenuElement(String title, String d){
		field = new TextField();
		field.setText(d);
		this.title = title;
//		field.textProperty().addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue,
//			                    String newValue) {
//				if (!newValue.matches("\\d*")) {
//					field.setText(newValue.replaceAll("[^\\d]", ""));
//				}
//			}
//		});
		view = ButtonFactory.makeHBox(title, null, field);
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
	public String getTitle() {
		return title;
	}
}
