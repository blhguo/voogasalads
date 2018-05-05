package game_engine.event.actions.macro;

import game_engine.Engine;
import game_engine.event.Action;

/**
 * 
 * @author Andy Nguyen
 * The purpose of this class is to allow level changes to happen during the game. 
 * This action changes the engine's level to the a given level.
 * This action is defined/instantiated within the authoring environment.
 */
public class LevelChangeAction implements Action{
	private Engine myEngine;
	private int myChangeTo;
	
	/**
	 * instantiates a new LevelChangeAction with a reference of the engine as well as the level number that
	 * this action will set the engine's level to
	 * @param engine
	 * @param levelNum
	 */
	public LevelChangeAction(Engine engine, int levelNum){
		myEngine = engine;
		myChangeTo = levelNum;
	}
	public LevelChangeAction(Engine engine, double levelNum){
		myEngine = engine;
		myChangeTo = (int) levelNum;
	}
	
	/**
	 * sets the engine's level to that of the given level number
	 */
	@Override
	public void execute() {
		myEngine.setLevel(myChangeTo);
	}
	
}
