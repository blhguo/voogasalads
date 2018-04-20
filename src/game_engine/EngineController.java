package game_engine;

import java.util.LinkedList;
import java.util.List;

import game_engine.event.Event;
import javafx.scene.input.InputEvent;

public class EngineController {
	private List<GameSystem> mySystems;
	private List<Event> myEvents;
	private LevelManager myLevels;
	
	public EngineController(List<Class <? extends GameSystem>> systems, List<Event> events, LevelManager levelManager) {
		myEvents = events;
		myLevels = levelManager;
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
