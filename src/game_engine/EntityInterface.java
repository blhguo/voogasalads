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
	void removeComponent(Class<? extends Component> clazz);
	
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
	

	/**
	 * Returns true if it has any one of the components
	 * @param args
	 * @return
	 */
	boolean hasAny(List<Class<? extends Component>> args);
	
	/** returns all components of an entity
	 * @return Map
	 */
	List<Component> getComponents();
}
