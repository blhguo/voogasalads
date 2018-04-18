package game_engine;

public abstract class Component {
	private String myValue;
	
	public Component(String arg) {
		myValue = arg;
	}
	
	public String getValue() {
		return myValue;
	}
	
	public void setValue(String value) {
		myValue = value;
	}
}
