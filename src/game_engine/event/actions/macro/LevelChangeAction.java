package game_engine.event.actions.macro;

import game_engine.Engine;
import game_engine.Level;
import game_engine.event.Action;

public class LevelChangeAction implements Action{
	private Engine myEngine;
	private Level myLevel;
	
	public LevelChangeAction(Engine engine, Level level){
		myEngine = engine;
		myLevel = level;
	}
	
	@Override
	public void execute() {
		myEngine.setLevel(myLevel);
	}
	
}
