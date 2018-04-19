package game_engine.event.conditions;

import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.components.collision.CollidedComponent;

public class ComponentCollisionCondition extends CollisionCondition{
	Class<Component> targetComponent;
	
	public ComponentCollisionCondition(Entity e1, Class<Component>c, List<Class<CollidedComponent>> sides) {
		super(e1, sides);
		targetComponent = c;
	}
	
	@Override
	protected boolean findCollidedTarget(CollidedComponent sideComponent) {
		return sideComponent.contains(targetComponent);
	}
}
