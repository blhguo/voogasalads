package game_engine.event.actions;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

public class DataToggleAction implements Action{
	private Entity myEntity;
	private Class<? extends Component<Boolean>> myComponentClass;
	
	public DataToggleAction(Entity entity, Class<? extends Component<Boolean>> componentClass) {
		myEntity = entity;
		myComponentClass = componentClass;
	}
	
	@Override
	public void execute() {
		Component<Boolean> comp = myEntity.getComponent(myComponentClass);
		comp.setValue(!comp.getValue());
	}

}
