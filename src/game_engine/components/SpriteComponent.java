package game_engine.components;

import java.util.List;

import game_engine.Component;

public class Sprite implements Component{
	
	private String myFileName;
	private boolean isVisible;
	
	public Sprite(List<String> args) {
		myFileName = args.get(0);
		isVisible = Boolean.parseBoolean(args.get(1));
	}
	
	public String getFileName() {
		return myFileName;
	}

	public void setFileName(String fileName) {
		myFileName = fileName;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible() {
		isVisible = true;
	}
	
	public void setInvisible() {
		isVisible = false;
	}
	

}
