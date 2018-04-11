package game_engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.systems.InputGarbageCollectionSystem;
import game_engine.systems.MovementSystem;
import game_engine.systems.collision.CollisionBroadSystem;
import game_engine.systems.collision.CollisionResponseSystem;
import game_engine.systems.keyboard.KeyboardJumpSystem;
import game_engine.systems.keyboard.KeyboardMovementSystem;
import javafx.scene.input.InputEvent;

public class Engine {
	
	private List<Entity> myEntities = new ArrayList<>();
	private List<GameSystem> mySystems = new ArrayList<>();
	private LinkedList<InputEvent> myInputs = new LinkedList<>();
	
	public Engine(){
		mySystems.add(new MovementSystem(this));
		mySystems.add(new KeyboardMovementSystem(this));
		mySystems.add(new KeyboardJumpSystem(this));
		mySystems.add(new InputGarbageCollectionSystem(this));
		mySystems.add(new CollisionBroadSystem(this));
		mySystems.add(new CollisionResponseSystem(this));
	}

	
	public void update(double elapsedTime) {
		for (GameSystem system : mySystems) {
			system.act(elapsedTime);
		}
	}
	
	public List<Entity> getEntitiesContaining(List<Class<? extends Component>> args) {
		return myEntities.stream().filter(e -> e.hasAll(args)).collect(Collectors.toList());
	}

	public List<Entity> getEntitiesContainingAny(List<Class<? extends Component>> args) {
		return myEntities.stream().filter(e -> e.hasAny(args)).collect(Collectors.toList());
	}
	
	public void receiveInput(InputEvent input) {
		myInputs.addFirst(input);
	}
	
	public List<InputEvent> getInput() {
		return myInputs;
	}

	public void addEntity(Entity e){
		myEntities.add(e);
	}

	public void addEntities(List<Entity> entities){
		for(Entity e: entities){
			myEntities.add(e);
		}
	}
}
