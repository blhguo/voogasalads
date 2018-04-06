package authoring.right_components;

import authoring.GUIComponent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class BaseComponent implements GUIComponent {

	protected int SIZE = 310;

	@Override
	public Pane getView() {
		return buildBasicView(null);
	}
	
	protected VBox buildBasicView(String title) {
		VBox masterBox = new VBox();
        masterBox.setPrefWidth(SIZE);
        masterBox.setPadding(new Insets(20, 30, 20 ,30));
        masterBox.setSpacing(20);
		masterBox.getStyleClass().add("pane-back");
		if(title!=null) {
			buildHeader(title, masterBox);
		}
		return masterBox;
	}
    
	private void buildHeader(String title, VBox masterBox) {
        Label paneTitle = new Label(title);
        paneTitle.getStyleClass().add("pane-title");
		masterBox.getChildren().add(paneTitle);
		
		Separator belowTitle = new Separator();
		belowTitle.setHalignment(HPos.CENTER);
		belowTitle.setPrefWidth(SIZE * 2.0/3);
		masterBox.getChildren().add(belowTitle);
	}

}
