package game_engine.event.actions.macro;

import game_engine.Engine;
import game_engine.event.Action;

public class PlayMusicAction implements Action{
	private Engine myEngine;
	
	public PlayMusicAction(Engine engine){
		myEngine = engine;
	}
	
	@Override
	public void execute() {
		//TODO figure out a way to play music!
	}
	
}
