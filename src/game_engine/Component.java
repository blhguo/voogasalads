package game_engine;

import game_engine.components.Authoring;

public abstract class Component<T> {
	private T myValue;
	public Component(T val) {
		myValue = val;
	}
	
	public void setValue(T val) {
		myValue = val;
	}
	
	@Authoring
	public T getValue() {
		return myValue;
	}
}

