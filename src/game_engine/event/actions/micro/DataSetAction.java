package game_engine.event.actions.micro;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Action;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Andy Nguyen, Ben Hubsch
 * The purpose of this action is to change the value of a component within a given entity.
 * This action sets the value of a given component within an entity to a given value.
 * This action is defined/instantiated within the authoring environment.
 */
public class DataSetAction implements Action {
	private Entity myEntity;
	private Class<? extends Component<Double>> myComponentClass;
	private Double myValue;

	/**
	 * instantiates a new DataSetAction object, which contains the entity and generic component that will be written to, as well
	 * as the generic value that will be written to that generic component.
	 * @param entity
	 * @param componentClass
	 * @param value
	 */
	public DataSetAction(Entity entity, Class<? extends Component<Double>> componentClass, Double value) {
		myEntity = entity;
		myComponentClass = componentClass;
		myValue = value;
	}

	/**
	 * sets the value of the given generic component to the given generic value
	 */
	@Override
	public void execute() {
		Component<Double> myComponent = myEntity.getComponent(myComponentClass);
		myComponent.setValue(myValue);
	}
}
