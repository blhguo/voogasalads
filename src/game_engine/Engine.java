package game_engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Engine {
	
	private List<Entity> myEntities = new ArrayList<>();
	private List<System> mySystems = new ArrayList<>();
	private Queue<Vector> myInput = new LinkedList<>();
	
	public void update(double elapsedTime) {
		for (System system : mySystems) {
			system.act(elapsedTime);
		}
	}
	
	public List<Entity> getEntitiesContaining(List<Class<? extends Component>> args) {
		return myEntities.stream().filter(e -> e.hasAll(args)).collect(Collectors.toList());
	}
	
	public void receiveInput(Vector vector) {
		myInput.add(vector);
	}
	
	public Queue<Vector> getInput() {
		return myInput;
	}
}
