package game_engine.event.actions.micro;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

import java.math.BigDecimal;

import com.udojava.evalex.Expression;

/**
 * 
 * @author Andy Nguyen
 * The purpose of this action is to change the value of a component within a given entity. This type of value change within the
 * component is a relative data change. For example, a relative data change could involve adding a value to the current value in the
 * component or multiplying the current value of the component by a number. This action is defined/instantiated within the authoring environment.
 */
public class DataChangeAction implements Action{
	private Entity myEntity;
	private Class<? extends Component<Double>> myComponentClass;
	private double myValue;
	private String myExpression;

	/**
	 * instantiates a new DataChangeAction object containing the entity and the component class of the entity whose data will be changed.
	 * In addition, the constructor takes in an expression that will be acted onto the value of the component, as well as a value that will be applied
	 * to the expression. For instance, an example expression would be "+", and a value would be 5, so that means this action will
	 * add a value of 5 to the current value in the given component
	 * @param entity
	 * @param componentClass
	 * @param expression
	 * @param value
	 */
	public DataChangeAction(Entity entity, Class<? extends Component<Double>> componentClass, String expression, double value) {
		myEntity = entity;
		myComponentClass = componentClass;
		myExpression = expression;
		myValue = value;
	}
	
	/**
	 * changes the data of the given component using the given expression and value
	 */
	@Override
	public void execute() {
		Component<Double> comp = myEntity.getComponent(myComponentClass);
		String expression = comp.getValue() + myExpression + myValue; 
		BigDecimal result =  new Expression(expression).eval();
		comp.setValue(result.doubleValue());
	}
}
