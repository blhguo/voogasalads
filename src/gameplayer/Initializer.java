package gameplayer;

import javafx.scene.Group;
import javafx.scene.Node;
import java.util.*;

/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class Initializer {
	
	private ViewManager vManager;
	private ArrayList<Node> entityList;
	
	public Initializer(ViewManager viewManager) {
		vManager = viewManager;
	}
	
	public void instantiate() {
		for (Node gameNode : entityList) {
			vManager.getNode().getChildren().add(gameNode);
			
		}
			
		
	}

}
