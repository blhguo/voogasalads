package game_engine.components;

public class HeightComponent {
	
	private String myHeight;

	public HeightComponent(String arg) {
		myHeight = arg;
	}
	
	public String getValue() {
		return myHeight;
	}
	
	public void setValue(String arg) {
		myHeight = arg;
	}
}
