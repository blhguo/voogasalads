package game_engine.event.conditions;

import java.lang.reflect.Method;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Condition;

public class DataCondition implements Condition{
	private Entity myEntity;
	private Class<? extends Component> myComponent;
	private Method myMethod;
	private String myComparisonType;
	private String myExpectedVal;
	
	public DataCondition(Entity entity, Class<? extends Component> component, Method method, String compType, String expectedVal){
		myEntity = entity;
		myComponent = component;
		myMethod = method;
		myComparisonType = compType;
		myExpectedVal = expectedVal;
	}
	@Override
	public boolean evaluate() {
		// TODO Auto-generated method stub
		return false;
	}

}
