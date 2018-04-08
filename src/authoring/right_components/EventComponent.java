package authoring.right_components;

import authoring.GUIComponent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class EventComponent extends StackPane implements GUIComponent {

	public EventComponent() {
		this.getChildren().add(new Button("event"));
		this.setWidth(Double.MAX_VALUE);
		this.setStyle("-fx-background-color: rgba(50, 50, 50, 1);");
	}
	@Override
	public Pane getView() {
		// TODO Auto-generated method stub
		return this;
	}
}
