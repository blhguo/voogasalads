package game_engine;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.ResourceBundle;

public class ComponentFactory {
	
	private static final String COMPONENT_BUNDLE = "Component";
	
	private ResourceBundle myComponents;
	
	public ComponentFactory() {
		myComponents = ResourceBundle.getBundle(COMPONENT_BUNDLE);
	}

	public Component addComponent(Entity entity, String key, Map<String, String> args) {
		Component component = createComponent(key, args);
		entity.addComponent(component);
		return component;
	}

	private Component createComponent(String key, Map<String, String> args) {
		try {
			Class<?> clazz = Class.forName(myComponents.getString(key));
			Constructor<?> ctor = clazz.getDeclaredConstructor(new Class[] { Map.class });
			return (Component) ctor.newInstance(args);
		} catch (Exception e) {
			throw new ComponentNotFoundException("Component not found.");
		}
	}
	
}
