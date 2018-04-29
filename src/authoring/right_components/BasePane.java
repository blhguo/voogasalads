package authoring.right_components;

import java.util.ArrayList;
import java.util.List;

import authoring.GUI_Heirarchy.GUINode;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.keys.AuthRes;

/**
 * @author Elizabeth Shulman
 * Base Pane is the super class for the right menus. 
 * Default that is displayed when the Authoring Environment is first opened. 
 * Implements GUINode because it is a sub-part of a scene
 */
public class BasePane implements GUINode {

	/**
	 * Implements method from GUINode interface. Returns visual of base pane
	 */
	@Override
	public Pane getView() {
		return buildBasicView(null);
	}
	
	protected VBox buildBasicView(String title) {
		VBox masterBox = new VBox();
        masterBox.setPrefWidth(AuthRes.getInt("PrefBaseSize"));
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
        
		masterBox.getChildren().addAll(paneTitle, new Separator());
	}

	/**
	 * Method for adding buttons to the Pane. Overwritten by subclasses
	 * @return List<Node>
	 */
	public List<Node> getButtonArray() {
		List<Node> list = new ArrayList<>();
		return list;
	}
	
	/**
	 * Method that adds lines
	 * @return Separator
	 */
	public Separator newSeparator() {
		Separator line = new Separator();
		line.setHalignment(HPos.CENTER);
		line.setPrefWidth(AuthRes.getInt("PrefBaseSize") * 2.0/3);
		return line;
	}

}
