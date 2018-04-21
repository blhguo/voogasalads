package game_engine;

import java.util.LinkedList;
import java.util.List;

import game_engine.event.Event;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

public class GameLoop {
	
	private LinkedList<InputEvent> myInputs;
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

	public void receiveInput(KeyEvent e) {
		myInputs.add(e);
	}
	
	
}
