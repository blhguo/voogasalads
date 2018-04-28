package game_engine.event.actions.macro;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.event.Action;
import game_engine.level.Level;

public class RemoveEntityAction implements Action{
	private Entity myEntity;
	private Engine myEngine;
	private int myLevelId;
	
	public RemoveEntityAction(Entity entity, Engine engine, int levelId){
		myEntity = entity;
		myEngine = engine;
		myLevelId = levelId;
	}
	
	@Override
	public void execute() {
		myEngine.getLevel(myLevelId).removeEntity(myEntity);
		myEngine.getLevel(myLevelId).removeEntity(myEntity);
	}
}
