package game_engine.event.actions.macro;

import game_engine.Engine;
import game_engine.event.Action;

public class LevelChangeAction implements Action{
	private Engine myEngine;
	private int myChangeTo;
	
	public LevelChangeAction(Engine engine, int levelNum){
		myEngine = engine;
		myChangeTo = levelNum;
	}
	
	@Override
	public void execute() {
		myEngine.setLevel(myChangeTo);
	}
	
}
