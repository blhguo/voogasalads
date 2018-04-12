	package game_engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Entity implements EntityInterface {
	
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
	public void removeComponent(Class<? extends Component> clazz) {
		myComponents.remove(clazz);
	}
	
	@Override
	public Component getComponent(Class<? extends Component> clazz) {
		return myComponents.get(clazz);
	}
	
	@Override
	public List<Component> getComponents(){
		return myComponents.keySet().stream().map(comp -> myComponents.get(comp)).collect(Collectors.toList());
	}
	
	@Override
	public boolean hasAll(List<Class<? extends Component>> args) {
		for (Class<? extends Component> c : args) {
			if (!myComponents.containsKey(c)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean hasAny(List<Class<? extends Component>> args) {
		for(Class<? extends Component> c : args){
			if(myComponents.containsKey(c)){
				return true;
			}
		}
		return false;
	}
}
