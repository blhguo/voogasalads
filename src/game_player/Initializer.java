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
