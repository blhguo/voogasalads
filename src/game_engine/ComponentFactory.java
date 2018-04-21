package game_engine;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 
 * @author benhubsch
 * 
 * A factory for creating Component objects.
 */
public class ComponentFactory {

	private static final String COMPONENT_BUNDLE = "Component";

	private ResourceBundle myComponents;

	/**
	 * Instantiates a new ComponentFactory object.
	 */
	public ComponentFactory() {
		myComponents = ResourceBundle.getBundle(COMPONENT_BUNDLE);
	}

	/**
	 * Adds the component to the Entity, since components are associated with Entity objects.
	 *
	 * @param entity the entity
	 * @param key the key
	 * @param args the args
	 * @return Component
	 */
	public <T> Component<T> addComponent(Entity entity, String key, List<String> args) {
		Component<T> component = createComponent(key, args);
		entity.addComponent(component);
		return component;
	}

	@SuppressWarnings("unchecked")
	private <T> Component<T> createComponent(String key, List<String> args) {
		try {
			Class<?> clazz = Class.forName(myComponents.getString(key) + "Component");
			Constructor<?> ctor = clazz.getDeclaredConstructor(new Class[] { List.class });
			return (Component<T>) ctor.newInstance(args);
		} catch (Exception e) {
			throw new ComponentNotFoundException("Component not found.");
		}
	}

}
