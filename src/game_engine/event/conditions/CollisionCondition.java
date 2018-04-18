package game_engine.event.conditions;

import game_engine.Entity;
import game_engine.components.collision.CollidedComponent;
import game_engine.event.Condition;

public class CollisionCondition implements Condition {
	
	private Entity myFirst;
	private Entity mySecond;
	
	public CollisionCondition(Entity first, Entity second) {
		myFirst = first;
		mySecond = second;
	}

	@Override
	public boolean evaluate() {
		return myFirst.getComponent(CollidedComponent.class).contains(mySecond);
	}

}
