package game_engine.event.conditions;

import java.util.stream.Collectors;

import game_engine.Engine;
import game_engine.event.Condition;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This condition checks if the key input is equivalent to a specified key
 * @author Andy Nguyen, Ben Hubsch, Kevin Deng, Jeremy Chen
 *
 */
public class KeyboardInputCondition implements Condition {

	private static final EventType<KeyEvent> KEY_PRESSED = KeyEvent.KEY_PRESSED;

	private Engine myEngine;
	private KeyCode myKey;

	public KeyboardInputCondition(Engine engine, String key) {
		myEngine = engine;
		myKey = KeyCode.valueOf(key);
	}

	@Override
	public boolean evaluate() {
		return myEngine.getKeyInputs(myKey).stream().filter(this::isPressed).collect(Collectors.toList()).size() > 0;
	}

	private boolean isPressed(KeyEvent input) {
		return input.getEventType().equals(KEY_PRESSED);
	}
}
