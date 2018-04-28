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

public class ProjectileDespawnSystem extends CollisionResponseSystem{
	private static final Class<? extends Component<NullType>> PROJECTILE = ProjectileComponent.class;
	
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(PROJECTILE);
		List<Entity> projectileEntities = level.getEntitiesContaining(args);
		List<Entity> collidedEntities = getCollidedEntities(projectileEntities, level);
		
		for (Entity collidedProjectile : collidedEntities) {
			Component<NullType> despawn = new DespawnComponent();
			collidedProjectile.addComponent(despawn);
			System.out.println("REMOVED");
		}
	}

}
