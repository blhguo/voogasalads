package game_engine;

import java.util.List;

import game_engine.event.Event;

public class GameLoop {
	private List<GameSystem> mySystems;
	private List<Event> myEvents;
	private Engine myEngine;
	
	public GameLoop(List<Class <? extends GameSystem>> systems, List<Event> events, Engine engine) {
		myEvents = events;
		myEngine = engine;
		// init systems
	}
	
	public void update(double elapsedTime) {
		for(GameSystem system: mySystems) {
			system.act(elapsedTime);
		}
		for(Event event: myEvents) {
			event.occur();
		}
	}
	
	
}
