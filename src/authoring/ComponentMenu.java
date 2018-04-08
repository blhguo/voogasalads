package authoring;

import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;

/**
 * This interface defines the various kinds of Component Menus which will be elements
 * of the EntityComponent menu in the Authoring Environment
 * Examples of classes which implement this interface might include
 * CollisionMenu, InteractionMenu, etc.
 */

public interface ComponentMenu{
	/**
	 *  Creates a new Entity Component using user input from the various involved menus, etc.
	 * @return the newly created Entity Component, to be added to some entity
	 */
	Component makeComponent();

	Node getNode();

	TitledPane getTitledPane();
}
