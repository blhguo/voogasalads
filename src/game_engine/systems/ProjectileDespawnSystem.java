package game_engine.systems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.DespawnComponent;
import game_engine.components.ProjectileComponent;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.level.Level;

public class ProjectileDespawnSystem extends GameSystem{
	private static final Class<? extends Component<List<Entity>>> TOP = TopCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> BOTTOM = BottomCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> RIGHT = RightCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> LEFT = LeftCollidedComponent.class;
	private static final Class<? extends Component<Boolean>> PROJECTILE = ProjectileComponent.class;
	
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(PROJECTILE);
		List<Entity> projectileEntities = level.getEntitiesContaining(args);
		
		List<Class<? extends Component<?>>> collidedArgs = Arrays.asList(TOP, BOTTOM, RIGHT, LEFT);
		List<Entity> collidedEntities = level.getEntitiesContainingAny(projectileEntities, collidedArgs);
		
		for (Entity collidedProjectile : collidedEntities) {
			Component<Boolean> despawn = new DespawnComponent();
			collidedProjectile.addComponent(despawn);
			System.out.println("REMOVED");
		}
	}

}
