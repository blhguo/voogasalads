package game_engine.event.actions.macro;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.event.Action;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Andy Nguyen, Ben Hubsch
 * The purpose of this class is to act as an action that allows entities to be added to the game
 * dynamically in the playing environment. The action adds a given entity to the current level once it is executed.
 * This action is defined/instantiated within the authoring environment.
 */
public class AddEntityAction implements Action{
	private Entity myEntity;
	private Engine myEngine;

	/**
	 * instantiates a new AddEntityAction with a reference to the engine it is adding the given entity to
	 * @param entity
	 * @param engine
	 */
	public AddEntityAction(Entity entity, Engine engine){
		myEntity = entity;
		myEngine = engine;
		myEngine.getLevel().removeEntity(entity);
	}

	/**
	 * adds the given entity to the current level of the engine
	 */
	@Override
	public void execute() {
		if (! myEngine.getLevel().getEntities().contains(myEntity)) {
			myEngine.getLevel().addEntity(myEntity);
		}
	}
}
