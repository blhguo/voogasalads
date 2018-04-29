package game_engine;

import java.util.List;

/**
 * 
 * This interface specifies the methods that an Entity object must implement.
 * 
 * @author Andy Nguyen
 *
 */
public interface EntityInterface<T> {
	
	/**
	 * Adds a component for the Entity
	 * @param component
	 */
	void addComponent(Component<T> component);
	
	/**
	 * Removes a component for the Entity
	 * To be used not by front-end, but by other classes
	 * @param component
	 */
	void removeComponent(Class<? extends Component<T>> clazz);
	
	/**
	 * Gets a Component based on Component class
	 * @param clazz
	 * @return
	 */
	Component<T> getComponent(Class<? extends Component<T>> clazz);
	
	
	/**
	 * Returns true if an Entity has the specified Components
	 * @param args
	 * @return
	 */
	boolean hasAll(List<Class<? extends Component<T>>> args);
	

	/**
	 * Returns true if it has any one of the components
	 * @param args
	 * @return
	 */
	boolean hasAny(List<Class<? extends Component<T>>> args);
	
	/** returns all components of an entity
	 * @return Map
	 */
	List<Component<T>> getComponents();
}
