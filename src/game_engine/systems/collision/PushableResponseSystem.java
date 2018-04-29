package game_engine.systems.collision;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.Component;
import game_engine.Entity;
import game_engine.components.collision.PushableComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.level.Level;

public class PushableResponseSystem extends CollisionResponseSystem {
	private static final Class<? extends Component<Boolean>> PUSHABLE = PushableComponent.class;

	private Entity getPushingEntity(List<Entity> entities, Class<? extends Component<Double>> velClass, double sign) {
		double maxVel = 0;
		Entity maxEntity = null;
		for(Entity e: entities) {
			double vel = e.getComponent(velClass).getValue();
			if(Math.abs(vel) > Math.abs(maxVel) && vel*sign > 0) {
				maxVel = vel;
				maxEntity = e;
			}
		}
		return maxEntity;
	}
	
	private void pushingByAxis(Entity pushed, Entity pusher1, Entity pusher2, Class<? extends Component<Double>> velClass) {
		
		
	}

	@Override
	public void act(double elapsedTime, Level level) {
		List<Entity> collidedEntities = getCollidedEntities(level);
		List<Entity> pushableEntities = level.getEntitiesContaining(collidedEntities, Arrays.asList(PUSHABLE))
				.stream()
				.filter(e -> e.getComponent(PUSHABLE).getValue())
				.collect(Collectors	.toList());
		
		for(Entity e: pushableEntities) {
			List<Entity> topEntities = e.getComponent(TOP).getValue();
			List<Entity> bottomEntities = e.getComponent(BOTTOM).getValue();
			List<Entity> leftEntities = e.getComponent(LEFT).getValue();
			List<Entity> rightEntities = e.getComponent(RIGHT).getValue(); 
			
			Entity topEntity = getPushingEntity(topEntities, YVelComponent.class, 1);
			Entity bottomEntity = getPushingEntity(bottomEntities, YVelComponent.class, -1);
			Entity leftEntity = getPushingEntity(leftEntities, XVelComponent.class, 1);
			Entity rightEntity = getPushingEntity(rightEntities, XVelComponent.class, -1);
			
			
			// axis decision-making
			// check for null
				// if both null, do nothing
				// if one null, one pushing, set vel to that of pushing
				// if neither null
					// if one == 0, and one pushing do not move
					// if both pushing, don't move? or you're fucked
			
		}
		
	}
}
