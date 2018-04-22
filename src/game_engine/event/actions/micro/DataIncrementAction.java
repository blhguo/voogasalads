package game_engine.event.actions.micro;

import game_engine.Component;


import game_engine.Entity;
import game_engine.event.Action;

public class DataIncrementAction implements Action {
	private Entity myEntity;
	private Class<? extends Component<Double>> myComponentClass;
	private double myDelta;

	public DataIncrementAction(Entity entity, Class<? extends Component<Double>> componentClass, double delta) {
		myEntity = entity;
		myComponentClass = componentClass;
		myDelta = delta;
	}
	
	@Override
	public void execute() {
		Component<Double> comp = myEntity.getComponent(myComponentClass);
		comp.setValue(myDelta + comp.getValue());
	}

}
