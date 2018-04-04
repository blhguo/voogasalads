package voogasalad_callussalad;

import java.util.HashMap;
import java.util.Map;

public class Entity {
	
	private Map<Class<?>, Component> myComponents;
	
	public Entity() {
		myComponents = new HashMap<>();
	}
	
	public void addComponent(Component component) {
		myComponents.put(component.getClass(), component);
	}
	
	// to be used not by front-end, but by other classes
	// seen here: https://gfycat.com/gifs/detail/directornatecapeghostfrog
	public void removeComponent(Component component) {
		myComponents.remove(component.getClass());
	}
	
	public Component getComponent(Class<? extends Component> clazz) {
		return myComponents.get(clazz);
	}
	
	public boolean hasAll(Class<?>... args) {
		for (Class<?> c : args) {
			// need to figure out the whole interface reflection stuff...not sure this is riht
			if (!myComponents.containsKey(c)) {
				return false;
			}
		}
		return true;
	}
}
