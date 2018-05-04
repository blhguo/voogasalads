package game_engine.event.actions.macro;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.event.Action;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Andy Nguyen, Ben Hubsch
 * The purpose of this class is to act as an action that allows entities to be removed from the game
 * dynamically in the playing environment. The action removes a given entity from the current level once it is executed.
 * This action is defined/instantiated within the authoring environment.
 */
public class RemoveEntityAction implements Action{
	private Entity myEntity;
	private Engine myEngine;
	
	/**
	 * instantiates a new RemoveEntityAction with a reference to the entity to be removed, the engine, and the level number the entity
	 * will be removed from
	 * @param entity
	 * @param engine
	 */
	public RemoveEntityAction(Entity entity, Engine engine){
		myEntity = entity;
		myEngine = engine;
	}
	
	/**
	 * removes an entity from the given level within engine
	 */
	@Override
	public void execute() {
		myEngine.getLevel().removeEntity(myEntity);
	}
}
