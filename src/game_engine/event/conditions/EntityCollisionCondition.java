package game_engine.event.conditions;

import game_engine.Entity;
import game_engine.components.collision.CollidedComponent;

/**
 * @author Jeremy Chen
 * CollisionCondition that checks for the presence of a certain entity in list of collided entities of the "this" entity
 */
public class EntityCollisionCondition extends CollisionCondition{
	
	private Entity myTargetEntity;
	
	public EntityCollisionCondition(Entity e1, Entity e2) {
		super(e1);
		myTargetEntity = e2;
	}
	
	@Override
	protected boolean findCollidedTarget(CollidedComponent sideComponent) {
		return (sideComponent != null) && (sideComponent.contains(myTargetEntity));
	}
}
