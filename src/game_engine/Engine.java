package game_engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import javafx.scene.input.InputEvent;

public class Engine {
	
	private List<Entity> myEntities = new ArrayList<>();
	private List<GameSystem> mySystems = new ArrayList<>();
	private Queue<InputEvent> myInputs = new LinkedList<>();
	
	public void update(double elapsedTime) {
		for (GameSystem system : mySystems) {
			system.act(elapsedTime);
		}
	}
	
	public List<Entity> getEntitiesContaining(List<Class<? extends Component>> args) {
		return myEntities.stream().filter(e -> e.hasAll(args)).collect(Collectors.toList());
	}
	
	public List<Entity> getEntitiesContainingAny(List<Class<? extends Component>> args){
		return myEntities.stream().filter(e -> e.hasAny(args)).collect(Collectors.toList());
	}
	
	public void receiveInput(InputEvent input) {
		myInputs.add(input);
	}
	
	public Queue<InputEvent> getInputQueue() {
		return myInputs;
	}

	public void addEntity(Entity e){
		myEntities.add(e);
	}

	public void addEntities(List<Entity> es){
		for(Entity e: es){
			myEntities.add(e);
		}
	}
}
