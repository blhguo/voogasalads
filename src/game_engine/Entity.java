package game_engine;

import java.util.HashMap;
import java.util.Map;

public class Entity implements EntityInterface{
	
	private Map<Class<? extends Component>, Component> myComponents;
	
	public Entity() {
		myComponents = new HashMap<>();
	}
	
	@Override
	public void addComponent(Component component) {
		myComponents.put(component.getClass(), component);
	}
	
	// to be used not by front-end, but by other classes
	// seen here: https://gfycat.com/gifs/detail/directornatecapeghostfrog
	@Override
	public void removeComponent(Component component) {
		myComponents.remove(component.getClass());
	}
	
	@Override
	public Component getComponent(Class<? extends Component> clazz) {
		return myComponents.get(clazz);
	}
	
	//surpressed warnings about <? extends Component>
	@Override
	@SuppressWarnings("unchecked") 
	public boolean hasAll(Class<? extends Component>... args) {
		for (Class<? extends Component> c : args) {
			// need to figure out the whole interface reflection stuff...not sure this is riht
			if (!myComponents.containsKey(c)) {
				return false;
			}
		}
		return true;
	}
}
