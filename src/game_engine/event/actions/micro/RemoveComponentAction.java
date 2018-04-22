package game_engine.event.actions.micro;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

public class RemoveComponentAction implements Action{
	private Entity myEntity;
	private Class<? extends Component<?>> myComponentClass;
	public RemoveComponentAction(Entity entity, Class<? extends Component<?>> componentClass){
		myEntity = entity;
		myComponentClass = componentClass;
	}
	
	@Override
	public void execute() {
		myEntity.removeComponent(myComponentClass);
	}

}
