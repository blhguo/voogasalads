package game_engine.event.actions;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

public class DataAction implements Action{
	private Entity myEntity;
	private Class<? extends Component> myComponentClass;
	private String myValue;

	public DataAction(Entity entity, Class<? extends Component> componentClass, String value){
		myEntity = entity;
		myComponentClass = componentClass;
		myValue = value;
	}

	@Override
	public void execute() {
		Component myComponent = myEntity.getComponent(myComponentClass);
		myComponent.setValue(myValue);
	}
}
