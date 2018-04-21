package game_engine;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.event.Event;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Engine {
	private List<Level> myLevels;
	private int myLevelDex;
	private List<GameSystem> mySystems;
	private List<Event> myEvents;
	private LinkedList<InputEvent> myInputs;

	public Engine(List<Level> levels) {
		myLevels = levels;
		myLevelDex = 0;
	}

	public void update(double elapsedTime) {
		for (GameSystem system : mySystems) {
			system.act(elapsedTime, myLevels.get(myLevelDex));
		}

		for (Event event : myEvents) {
			event.occur();
		}
	}
	
	// public Level addLevel()
	
	// public Level removeLevel()
	
	// public Level addEvent()
	
	// public Level removeEvent()

	public Level getLevel() {
		return myLevels.get(myLevelDex);
	}

	public void setLevel(int dex) {
		myLevelDex = dex;
	}

	public List<InputEvent> getInput(Component<KeyCode> keyInput) {
		return myInputs.stream().map(input -> (KeyEvent) input)
				.filter(keyEvent -> keyInput.getValue().equals(keyEvent.getCode())).collect(Collectors.toList());
	}

	public void receiveInput(KeyEvent event) {
		myInputs.add(event);
	}

}
