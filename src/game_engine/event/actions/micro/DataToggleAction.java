package game_engine.event.actions.micro;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

/**
 * 
 * @author Andy Nguyen
 * The purpose of this class is to toggle Boolean-valued Components on a given Entity.
 * An example of this could be to toggle the Impassable Component on an entity so that it goes 
 * from being passable to impassable. This action is defined/instantiated within the authoring environment.
 * @param <T>
 */
public class DataToggleAction implements Action {
	private Entity myEntity;
	private Class<? extends Component<Boolean>> myComponentClass;
	
	/**
	 * instantiates a new DataToggleAction with the given entity and Boolean-valued component
	 * @param entity
	 * @param componentClass
	 */
	public DataToggleAction(Entity entity, Class<? extends Component<Boolean>> componentClass) {
		myEntity = entity;
		myComponentClass = componentClass;
	}
	
	/**
	 * toggles the given Boolean-valued component on the entity
	 */
	@Override
	public void execute() {
		Component<Boolean> comp = myEntity.getComponent(myComponentClass);
		Boolean flipped = !comp.getValue();
		comp.setValue(flipped);
	}

}
