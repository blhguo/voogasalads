package game_engine.event.actions.macro;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.event.Action;

public class RemoveEntityAction implements Action{
	private Entity myEntity;
	private Engine myEngine;
	
	public RemoveEntityAction(Entity entity, Engine engine){
		myEntity = entity;
	}
	
	@Override
	public void execute() {
		myEngine.removeEntity(myEntity);
	}
}
