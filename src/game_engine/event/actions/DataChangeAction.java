package game_engine.event.actions;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

public class DataChangeAction implements Action {
	private Entity myEntity;
	private Class<? extends Component<Number>> myComponentClass;
	private Number myDelta;

	public DataChangeAction(Entity entity, Class<? extends Component<Number>> componentClass, Number delta) {
		myEntity = entity;
		myComponentClass = componentClass;
		myDelta = delta;
	}
	
	@Override
	public void execute() {
		Component<Number> comp = myEntity.getComponent(myComponentClass);
		comp.setValue(myDelta.doubleValue() + comp.getValue().doubleValue());
	}

}
