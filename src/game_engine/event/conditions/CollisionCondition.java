package game_engine.event.conditions;

import game_engine.Entity;
import game_engine.components.collision.CollidedComponent;
import game_engine.event.Condition;

public abstract class CollisionCondition implements Condition {
	@Override
	public abstract boolean evaluate();
}
