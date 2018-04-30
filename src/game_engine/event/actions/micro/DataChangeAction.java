package game_engine.event.actions.micro;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

import java.math.BigDecimal;

import com.udojava.evalex.Expression;

public class DataChangeAction implements Action{
	private Entity myEntity;
	private Class<? extends Component<Double>> myComponentClass;
	private double myValue;
	private String myExpression;

	public DataChangeAction(Entity entity, Class<? extends Component<Double>> componentClass, String expression, double value) {
		myEntity = entity;
		myComponentClass = componentClass;
		myExpression = expression;
		myValue = value;
	}
	
	@Override
	public void execute() {
		Component<Double> comp = myEntity.getComponent(myComponentClass);
		String expression = comp.getValue() + myExpression + myValue; 
		BigDecimal result =  new Expression(expression).eval();
		comp.setValue(result.doubleValue());
	}
}
