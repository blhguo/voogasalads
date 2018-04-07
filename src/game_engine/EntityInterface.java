package game_engine;

import java.util.List;

public interface EntityInterface {
	
	/**
	 * Adds a component for the Entity
	 * @param component
	 */
	void addComponent(Component component);
	
	/**
	 * Removes a component for the Entity
	 * To be used not by front-end, but by other classes
	 * @param component
	 */
	void removeComponent(Component component);
	
	/**
	 * Gets a Component based on Component class
	 * @param clazz
	 * @return
	 */
	Component getComponent(Class<? extends Component> clazz);
	
	
	/**
	 * Returns true if an Entity has the specified Components
	 * @param args
	 * @return
	 */
	boolean hasAll(List<Class<? extends Component>> args);
}
