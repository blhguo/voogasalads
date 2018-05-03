package game_engine;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

/**
 * 
 * @author benhubsch
 * 
 *         A factory for creating Component objects.
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
	public <T> Component<T> addComponent(Entity entity, String key, String arg) {
		Component<T> component = createComponent(key, arg);
		entity.addComponent(component);
		return component;
	}

	@SuppressWarnings("unchecked")
	public <T> Component<T> createComponent(String key, String arg) {
		try {
			Class<?> clazz = Class.forName(myComponents.getString(key));
			Constructor<?> ctor = clazz.getDeclaredConstructor(String.class);
			return (Component<T>) ctor.newInstance(arg);
		} catch (Exception e) {
			throw new ComponentNotFoundException("Component " + key + " " + arg);
		}
	}

	@SuppressWarnings("unchecked")
	public Class<? extends Component<?>> getComponentClass(String key) {
		try {
			return (Class<? extends Component<?>>) Class.forName(myComponents.getString(key));
		} catch (Exception e) {
			throw new ComponentNotFoundException("Component class of name " + key + " not found");
		}
	}
}
