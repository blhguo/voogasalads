package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Entity {
	private Map<Class<? extends Component>, Component<?>> myComponents;

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
	public Component<?> getComponent(Class<? extends Component<?>> clazz) {
		return myComponents.get(clazz);
	}

	/* (non-Javadoc)
	 * @see game_engine.EntityInterface#getComponents()
	 */
	public List<Component<?>> getComponents() {
		return myComponents.keySet().stream().map(comp -> myComponents.get(comp)).collect(Collectors.toList());
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
