package game_engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Class Entity.
 *
 * @author benhubsch
 * 
 *         The Entity is one of our main abstractions: everything you see on-screen or interact with
 *         during a game is an Entity. We favor Composition over Inheritance here by aggregating an
 *         Entity's attributes using a Map of Components. The entity exposes various methods that
 *         allow the rest of the backend to function.
 */
public class Entity {
	private Map<Class<?>, Component<?>> myComponents;

	/**
	 * Instantiates a new Entity object.
	 */
	public Entity() {
		myComponents = new HashMap<>();
	}

	/* (non-Javadoc)
	 * @see game_engine.EntityInterface#addComponent(game_engine.Component)
	 */
	public void addComponent(Component<?> component) {
		myComponents.put(component.getClass(), component);
	}

	/* (non-Javadoc)
	 * @see game_engine.EntityInterface#removeComponent(java.lang.Class)
	 */
	public void removeComponent(Class<? extends Component<?>> clazz) {
		myComponents.remove(clazz);
	}

	/* (non-Javadoc)
	 * @see game_engine.EntityInterface#getComponent(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> Component<T> getComponent(Class<? extends Component<T>> clazz) {
		return (Component<T>) myComponents.get(clazz);
	}

	/* (non-Javadoc)
	 * @see game_engine.EntityInterface#getComponents()
	 */
	public List<Component<?>> getComponents() {
		return myComponents.values().stream().collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see game_engine.EntityInterface#hasAll(java.util.List)
	 */
	public boolean hasAll(List<Class<? extends Component<?>>> args) {
		for (Class<? extends Component<?>> c : args) {
			if (!myComponents.containsKey(c)) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see game_engine.EntityInterface#hasAny(java.util.List)
	 */
	public boolean hasAny(List<Class<? extends Component<?>>> args) {
		for (Class<? extends Component<?>> c : args) {
			if (myComponents.containsKey(c)) {
				return true;
			}
		}
		return false;
	}
}
