package game_engine.components;

import game_engine.Component;

public class Sprite implements Component{
	
	private String myFileName;
	private boolean isVisible;
	
	public Sprite(String fileName) {
		myFileName = fileName;
		isVisible = true;
	}
	
	public String getFileName() {
		return myFileName;
	}

	public void setFileName(String fileName) {
		myFileName = fileName;
	}
	
	public boolean isVisible() {
		return isVisible();
	}
	
	public void setVisible() {
		isVisible = true;
	}
	
	public void setInvisible() {
		isVisible = false;
	}
	

}
