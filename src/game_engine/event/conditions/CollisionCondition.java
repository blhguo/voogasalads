package game_engine.event.conditions;

import java.util.List;

import game_engine.Entity;
import game_engine.components.collision.CollidedComponent;
import game_engine.event.Condition;

public abstract class CollisionCondition implements Condition {
	List<Class<? extends CollidedComponent>> sidesToCheck;
	Entity myEntity;
	
	public CollisionCondition(Entity e1, List<Class<? extends CollidedComponent>> sides) {
		myEntity = e1;
		sidesToCheck = sides;
	}
	
	@Override
	public boolean evaluate() {
		return checkSides();
	}
	
	private boolean checkSides() {
		for(Class<? extends CollidedComponent> c: sidesToCheck) {
			CollidedComponent sideComponent = (CollidedComponent) myEntity.getComponent(c);
			if(findCollidedTarget(sideComponent)) {
				return true;
			}
		}
		return false;
	}
	
	protected abstract boolean findCollidedTarget(CollidedComponent sideComponent);
}
