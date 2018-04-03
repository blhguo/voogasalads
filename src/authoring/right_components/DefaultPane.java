package authoring.right_components;


import authoring.GUIComponent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

//Right Pane
public class DefaultPane extends StackPane implements GUIComponent{

	public DefaultPane() {
		initializeFormat();
	}
	
	private void initializeFormat() { //refactor to CSS; find a way to display pane when no text is placed
		this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8);");
		this.setHeight(Double.MAX_VALUE);
		this.getChildren().add(new Text("testtesttesttesttesttesttesttest"));
	}
	
	@Override
	public Pane getView() {
		return this;
	}

}