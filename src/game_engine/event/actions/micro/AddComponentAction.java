package game_engine.event.actions.micro;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

/**
 * 
 * @author Andy Nguyen
 * The purpose of this class is to allow entities to have Components dynamically added
 * to them during the game. This action is defined within the authoring environment.
 *
 */
public class AddComponentAction implements Action{
	private Entity myEntity;
	private Component<?> myComponent;
	
	/**
	 * instantiates a new AddComponentAction with the given entity, as well as the component that will be added to the entity
	 * @param entity
	 * @param component
	 */
	public AddComponentAction(Entity entity, Component<?> component){
		myEntity = entity;
		myComponent = component;
	}
	
	/**
	 * adds the given component to the given entity
	 */
	@Override
	public void execute() {
		myEntity.addComponent(myComponent);
	}
}
