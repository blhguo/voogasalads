package game_engine.components;

public class AngleComponent {

	private String myAngle;
	
	public AngleComponent(String arg) {
		myAngle = arg;
	}
	
	public String getValue() {
		return myAngle;
	}
	
	public void setValue(String arg) {
		myAngle = arg;
	}
}
