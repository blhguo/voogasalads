package game_engine.event.conditions;

import java.util.List;

import game_engine.Entity;
import game_engine.components.collision.CollidedComponent;

public class EntityCollisionCondition extends CollisionCondition{
	Entity targetEntity;
	
	public EntityCollisionCondition(Entity e1, Entity e2, List<Class<CollidedComponent>> sides) {
		super(e1, sides);
		targetEntity = e2;
	}
	
	@Override
	protected boolean findCollidedTarget(CollidedComponent sideComponent) {
		return sideComponent.contains(targetEntity);
	}
}
