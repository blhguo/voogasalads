package game_engine.event.actions.micro;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

/**
 * 
 * @author Andy Nguyen
 * The purpose of this class is to allow entities to have Components dynamically added
 * to them during the game. This action is defined within the authoring environment.
 */
public class RemoveComponentAction implements Action{
	private Entity myEntity;
	private Class<? extends Component<?>> myComponentClass;
	
	/**
	 * instantiates a new RemoveComponentAction with the given entity, as well as the component that will be removed from the entity
	 * @param entity
	 * @param componentClass
	 */
	public RemoveComponentAction(Entity entity, Class<? extends Component<?>> componentClass){
		myEntity = entity;
		myComponentClass = componentClass;
	}
	
	/**
	 * removes the given component from the given entity
	 */
	@Override
	public void execute() {
		myEntity.removeComponent(myComponentClass);
	}

}
