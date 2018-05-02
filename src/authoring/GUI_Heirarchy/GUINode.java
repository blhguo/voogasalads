package authoring.GUI_Heirarchy;

import javafx.scene.Node;

/**
 * @author Jennifer Chin
 * GUINode interface. Implemented by all sub-parts of a Scene. For example, this interface
 * is implemented by all our right menus, the canvas, and the left menu of the Authoring
 * Environment. This interface is intended to standardize the different JavaFX Nodes that
 * make up a Scene. Every Pane that is part of a larger Scene in the Authoring Environment
 * implements this interface to provide some hierarchy to the GUINodes.
 */

public interface GUINode {

	/**
	 * Returns the visual JavaFX element of the class. Our side panels have front end 
	 * components, but also deal with controllers to communicate with each other and 
	 * provide functionality. This method is where the front end portion is separated. 
	 * @return Pane
	 */
	public Node getView();
	
}
