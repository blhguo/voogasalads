package game_engine.components;

public class FilenameComponent {
	
	private String myFilename;

	public FilenameComponent(String arg) {
		myFilename = arg;
	}
	
	public String getValue() {
		return myFilename;
	}
	
	public void setValue(String arg) {
		myFilename = arg;
	}
}
