package game_engine;

public abstract class Component<T> {
	private T myValue;
	
	public Component(T val) {
		myValue = val;
	}
	
	public T getValue() {
		return myValue;
	}
	
	public void setValue(T value) {
		myValue = value;
	}
}
