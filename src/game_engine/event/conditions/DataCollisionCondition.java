package game_engine.event.conditions;

import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.components.collision.CollidedComponent;

public class DataCollisionCondition extends CollisionCondition{
	Class<Component> targetComponent;
	private String myComparison;
	private String myExpected;
	
	
	public DataCollisionCondition(Entity e1, Class<Component> targetComp, String comparison, String expected, List<Class<CollidedComponent>> sides) {
		super(e1, sides);
		targetComponent = targetComp;
		myExpected = expected;
		myComparison = comparison;
	}
	
	@Override
	protected boolean findCollidedTarget(CollidedComponent sideComponent) {
		return sideComponent.contains(targetComponent) 
				&& new DataCondition(myEntity, targetComponent, myComparison, myExpected).evaluate();
	}
}
