package voogasalad_callussalad;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

public class Entity {
	
	private Map<Class<? extends Component>, Component> myComponents;
	
	public Entity() {
		myComponents = new HashMap<>();
	}
	
	public void addComponent(Component component) {
		myComponents.put(component.getClass(), component);
	}
	
	public void removeComponent(Component component) {
		myComponents.remove(component.getClass());
	}
	
	public Component getComponent(Class<Component> clazz) {
		return myComponents.get(clazz);
	}
}
