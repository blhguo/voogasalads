package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.NullType;
import game_engine.components.DespawnComponent;
import game_engine.components.ProjectileComponent;
import game_engine.level.Level;
import game_engine.systems.collision.CollisionResponseSystem;

/**
 * 
 * @author Andy Nguyen, Jeremy Chen, Ben Hubsch, Kevin Deng
 * The purpose of this class is to provide default behavior for projectiles upon collision with entities in the game.
 * The default behavior described by this system is to despawn projectiles when they collide with anything. This can of course
 * be overwritten from an event, but we added this default behavior to make things convenient for the game authorer.
 *
 */
public class ProjectileDespawnSystem extends CollisionResponseSystem {
	private static final Class<? extends Component<NullType>> PROJECTILE = ProjectileComponent.class;
	
	/**
	 * adds a despawn component to projectiles when they collide with any entity
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(PROJECTILE);
		List<Entity> projectileEntities = level.getEntitiesContaining(args);
		List<Entity> collidedEntities = getCollidedEntities(projectileEntities, level);
		for (Entity collidedProjectile : collidedEntities) {
			Component<NullType> despawn = new DespawnComponent();
			collidedProjectile.addComponent(despawn);
		}
	}

}
