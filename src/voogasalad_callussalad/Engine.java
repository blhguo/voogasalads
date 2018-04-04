package voogasalad_callussalad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {
	
	private List<Entity> myEntities = new ArrayList<>();
	private List<System> mySystems = new ArrayList<>();
	
	public void update(double elapsedTime) {
		for (System system : mySystems) {
			system.act(elapsedTime);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Entity> getEntitiesContaining(Class<? extends Component>... args) {
		return myEntities.stream().filter(e -> e.hasAll(args)).collect(Collectors.toList());
	}
}
