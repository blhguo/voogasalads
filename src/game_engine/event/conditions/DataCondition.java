package game_engine.event.conditions;

import java.math.BigDecimal;
import java.util.Arrays;

import com.udojava.evalex.Expression;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.ComponentNotPresentException;
import game_engine.event.Condition;

/**
 * The purpose of this Condition is to check if an entity has a specific component data that is equivalent
 * to the passed data
 * @author Andy Nguyen, Ben Hubsch, Kevin Deng, Jeremy Chen
 *
 * @param <T>
 */
public class DataCondition<T> implements Condition {
	private Entity myEntity;
	private Class<? extends Component<T>> myComponentClass;
	private String myComparison;
	private String myExpected;

	public DataCondition(Entity entity, Class<? extends Component<T>> componentClass, String comparison, String expected) {
		myEntity = entity;
		myComponentClass = componentClass;
		myComparison = comparison;
		myExpected = expected;
		
		if (! myEntity.hasAll(Arrays.asList(myComponentClass))) {
			throw new ComponentNotPresentException(Arrays.asList(myComponentClass));
		}
	}

	@Override
	public boolean evaluate() {
		String expression = myEntity.getComponent(myComponentClass).getValue().toString() + myComparison + myExpected;
		BigDecimal result =  new Expression(expression).eval();
		return result.equals(BigDecimal.ONE);
	}
}
