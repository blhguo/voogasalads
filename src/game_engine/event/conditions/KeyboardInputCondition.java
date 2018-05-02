package game_engine.event.conditions;

import game_engine.Engine;
import game_engine.event.Condition;
import javafx.scene.input.KeyCode;

public class KeyboardInputCondition implements Condition{
	
	private Engine myEngine;
	private KeyCode myKey;
	
	public KeyboardInputCondition(Engine engine, String key){
		myEngine = engine;
		myKey = KeyCode.valueOf(key);
	}
	
	@Override
	public boolean evaluate() {
		return myEngine.getKeyInputs(myKey).size() > 0;
	}
}
