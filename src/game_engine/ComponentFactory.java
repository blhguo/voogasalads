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
	public Component addComponent(Entity entity, String key, List<String> args) {
		Component component = createComponent(key, args);
		entity.addComponent(component);
		return component;
	}

	private Component createComponent(String key, List<String> args) {
		try {
			System.out.println("key: " + key);
			System.out.println("component: " + myComponents.getString(key));
			Class<?> clazz = Class.forName(myComponents.getString(key) + "Component");
			System.out.println("clazz: " + clazz);

			Constructor<?> ctor = clazz.getDeclaredConstructor(new Class[] { List.class });
			return (Component) ctor.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ComponentNotFoundException("Component not found.");
		}
	}

}
