package game_engine;

import authoring.component_menus.MenuElement;

public abstract class Component<T> {
	private T myValue;
	public Component(T val) {
		myValue = val;
	}

	public void setValue(T val) {
		myValue = val;
	}
	
	public T getValue() {
		return myValue;
	}
}

