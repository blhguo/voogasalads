package game_engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Engine {
	
	private List<Entity> myEntities = new ArrayList<>();
	private List<GameSystem> mySystems = new ArrayList<>();
	private Queue<Input> myInputs = new LinkedList<>();
	
	public void update(double elapsedTime) {
		for (GameSystem system : mySystems) {
			system.act(elapsedTime);
		}
	}
	
	public List<Entity> getEntitiesContaining(List<Class<? extends Component>> args) {
		return myEntities.stream().filter(e -> e.hasAll(args)).collect(Collectors.toList());
	}
	
	public void receiveInput(Input input) {
		myInputs.add(input);
	}
	
	public Queue<Input> getInput() {
		return myInputs;
	}
}
