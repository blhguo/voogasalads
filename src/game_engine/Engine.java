package game_engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.systems.InputGarbageCollectionSystem;
import game_engine.systems.PositionSystem;
import game_engine.systems.VelocitySystem;
import game_engine.systems.collision.CollisionBroadSystem;
import game_engine.systems.collision.CollisionResponseSystem;
import game_engine.systems.keyboard.KeyboardJumpSystem;
import game_engine.systems.keyboard.LeftKeyboardMovementSystem;
import game_engine.systems.keyboard.RightKeyboardMovementSystem;
import javafx.scene.input.InputEvent;

/**
 * The Class Engine.
 *
 * @author benhubsch, Kevin Deng, Jeremy Chen, Andy Nguyen
 * 
 *         This class is the main logic distributor of the backend. It receives user input from the
 *         Game Player and also handles the GameSystem update looping, which happens every time our
 *         game loops in Game Player. Lastly, it exposes methods that allow the GameSystem objects
 *         easy access to the Entity objects they need to do their work.
 */
public class Engine {

	private List<Entity> myEntities = new ArrayList<>();
	private List<GameSystem> mySystems = new ArrayList<>();
	private LinkedList<InputEvent> myInputs = new LinkedList<>();

	/**
	 * Instantiates a new Engine object.
	 */
	public Engine() {
		mySystems.add(new PositionSystem(this));
		mySystems.add(new VelocitySystem(this));
		mySystems.add(new LeftKeyboardMovementSystem(this));
		mySystems.add(new RightKeyboardMovementSystem(this));
		mySystems.add(new KeyboardJumpSystem(this));
		mySystems.add(new InputGarbageCollectionSystem(this));
		mySystems.add(new CollisionBroadSystem(this));
		mySystems.add(new CollisionResponseSystem(this));
	}

	/**
	 * Allows each of the GameSystems to update the entities that contain the Components that the
	 * GameSystem is looking for.
	 *
	 * @param elapsedTime the elapsed time
	 */
	public void update(double elapsedTime) {
		for (GameSystem system : mySystems) {
			system.act(elapsedTime);
		}
	}

	/**
	 * Gets the List<Entity> object containing all Entities with only these Components.
	 *
	 * @param args the args
	 * @return List<Entity>
	 */
	public List<Entity> getEntitiesContaining(List<Class<? extends Component>> args) {
		return myEntities.stream().filter(e -> e.hasAll(args)).collect(Collectors.toList());
	}

	/**
	 * Gets the List<Entity> object containing all Entities with these Components.
	 *
	 * @param args the args
	 * @return List<Entity>
	 */
	public List<Entity> getEntitiesContainingAny(List<Class<? extends Component>> args) {
		return myEntities.stream().filter(e -> e.hasAny(args)).collect(Collectors.toList());
	}

	/**
	 * Receives input from Game Player.
	 *
	 * @param input the input
	 */
	public void receiveInput(InputEvent input) {
		myInputs.addFirst(input);
	}

	/**
	 * Gets the List<InputEvent> object, which some of the Systems use to act on their Entity objects.
	 *
	 * @return List<InputEvent>
	 */
	public List<InputEvent> getInput() {
		return myInputs;
	}

	/**
	 * Adds the entity to the backend. This is used during the various instantiation phases.
	 *
	 * @param e the e
	 */
	public void addEntity(Entity e) {
		myEntities.add(e);
	}

	/**
	 * Adds the entity to the backend. This is used during the various instantiation phases.
	 *
	 * @param entities the entities
	 */
	public void addEntities(List<Entity> entities) {
		for (Entity e : entities) {
			myEntities.add(e);
		}
	}
}
