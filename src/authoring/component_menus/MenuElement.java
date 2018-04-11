package authoring.component_menus;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public interface MenuElement {

	Node getView();

	String getValue();

	String getTitle();
}
