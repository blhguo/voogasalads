package game_engine.event.actions.micro;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

public class DataMultiplyAction implements Action{
	private Entity myEntity;
	private Class<? extends Component<Double>> myComponentClass;
	private Number myScale;

	public DataMultiplyAction(Entity entity, Class<? extends Component<Double>> componentClass, double scale) {
		myEntity = entity;
		myComponentClass = componentClass;
		myScale = scale;
	}
	
	@Override
	public void execute() {
		Component<Double> comp = myEntity.getComponent(myComponentClass);
		comp.setValue(myScale.doubleValue() * comp.getValue().doubleValue());
	}

}
