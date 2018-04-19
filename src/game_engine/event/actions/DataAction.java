package game_engine.event.actions;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

public class DataAction<T> implements Action {
	private Entity myEntity;
	private Class<? extends Component<T>> myComponentClass;
	private T myValue;

	public DataAction(Entity entity, Class<? extends Component<T>> componentClass, T value) {
		myEntity = entity;
		myComponentClass = componentClass;
		myValue = value;
	}

	@Override
	public void execute() {
		Component<T> myComponent = myEntity.getComponent(myComponentClass);
		myComponent.setValue(myValue);
	}
}
