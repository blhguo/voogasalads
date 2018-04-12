package game_player;

import javafx.scene.Group;
import javafx.scene.Node;
import java.util.*;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Level;
import game_engine.components.PositionComponent;
import game_engine.components.SpriteComponent;
import game_engine.systems.InputGarbageCollectionSystem;
import game_engine.systems.MovementSystem;
import game_engine.systems.collision.CollisionBroadSystem;
import game_engine.systems.collision.CollisionResponseSystem;
import game_engine.systems.keyboard.KeyboardJumpSystem;
import game_engine.systems.keyboard.KeyboardMovementSystem;

/**
 * 
 * @author Dana Park, Brandon Dalla Rosa
 *
 */
public class Initializer {
	
	private ViewManager vManager;
	
	public Initializer(ViewManager viewManager) {
		vManager = viewManager;
	}
	
	public void instantiate(List<Level> levels) {
		
		Engine engine = new Engine();
		GameSystem movementSystem = new MovementSystem(engine);
		engine.addSystem(movementSystem);
		GameSystem keyboardMovementSystem = new KeyboardMovementSystem(engine);
		engine.addSystem(keyboardMovementSystem);
		GameSystem keyboardJumpSystem = new KeyboardJumpSystem(engine);
		engine.addSystem(keyboardJumpSystem);
		GameSystem inputGarbageCollectionSystem = new InputGarbageCollectionSystem(engine);
		engine.addSystem(movementSystem);
		GameSystem collisionBroadSystem = new CollisionBroadSystem(engine);
		engine.addSystem(movementSystem);
		//GameSystem collisionCircleSystem = new CollisionBroadSystem(engine);
		//GameSystem collisionNarrowSystem = new CollisionBroadSystem(engine);
		GameSystem collisionResponseSystem = new CollisionResponseSystem(engine);
		engine.addSystem(movementSystem);
		//GameSystem collisionSystem = new CollisionBroadSystem(engine);
		engine.startGame(levels);
		
		/*
		engine.addEntities();
		for (Level l : levels) {
			Engine e = new Engine();
			
			e.getEntitiesContaining(SpriteComponent.class, PositionComponent.class);
			List<Entity> entities = l.getEntities();
			//SOMEHOW INSTANTIATE CURRENT ENTITIES
			}
		}
		*/
	}
}
