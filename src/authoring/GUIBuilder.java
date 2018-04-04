package authoring;

import javafx.scene.Scene;

public interface GUIBuilder {
	
	/**
	 * some dependencies - need to add style sheet in this method
	 * @return
	 */
	public Scene display();
}
