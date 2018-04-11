package game_engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.components.PositionComponent;
import game_engine.components.SpriteComponent;
import game_engine.components.keyboard.KeyboardJumpInputComponent;
import game_engine.components.keyboard.KeyboardMovementInputComponent;
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
	
	public void addSystem(GameSystem system){
		mySystems.add(system);
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
	
	public void startGame(List<Level> levels){
		//TODO: figure out level changes, right now only takes first level
		for(Entity e: levels.get(0).getEntities()){
			myEntities.add(e);
			
		}
	}
	
	public List<Entity> getDisplayableEntities(){
		return getEntitiesContaining(Arrays.asList(SpriteComponent.class, PositionComponent.class));
	}
	
	public List<Entity> getInputEntities(){
		return getEntitiesContaining(Arrays.asList(KeyboardJumpInputComponent.class, KeyboardMovementInputComponent.class));
	}
	
}
