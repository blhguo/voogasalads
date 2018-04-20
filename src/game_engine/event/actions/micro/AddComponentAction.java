package game_engine.event.actions.micro;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

public class AddComponentAction implements Action{
	private Entity myEntity;
	private Component<?> myComponent;
	public AddComponentAction(Entity entity, Component<?> component){
		myEntity = entity;
		myComponent = component;
	}
	
	@Override
	public void execute() {
		myEntity.addComponent(myComponent);
	}
}
