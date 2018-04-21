package game_engine.event.actions.macro;

import game_engine.Entity;
import game_engine.Level;
import game_engine.event.Action;

public class RemoveEntityAction implements Action{
	private Entity myEntity;
	private Level myLevel;
	
	public RemoveEntityAction(Entity entity, Level level){
		myEntity = entity;
	}
	
	@Override
	public void execute() {
		myLevel.removeEntity(myEntity);
	}
}
