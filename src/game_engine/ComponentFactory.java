package game_engine;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.VBox;

public class ComponentFactory {

	private static final String COMPONENT_BUNDLE = "Component";
	private static final String COMPONENT_DELIM  = "|";
	private static final String ATTRIBUTE_DELIM  = ",";

	private ResourceBundle myComponents;

	public ComponentFactory() {
		myComponents = ResourceBundle.getBundle(COMPONENT_BUNDLE);
	}

	public Component addComponent(Entity entity, String key, List<String> args) {
		Component component = createComponent(key, args);
		entity.addComponent(component);
		return component;
	}

	// property name -> VBox of HBoxes
	public Iterable<VBox> getComponent() {
		List<VBox> components = new ArrayList<>();
		for (String component : myComponents.keySet()) {
			String[] attributes = myComponents.getString(component).split(COMPONENT_DELIM);
			for (String attr : attributes) {
				String[] attrSplit = attr.split(ATTRIBUTE_DELIM);
			}
			components.add(null);
		}
		return null;
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
