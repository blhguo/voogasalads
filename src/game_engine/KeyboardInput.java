package game_engine;

import javafx.scene.input.KeyCode;

public class KeyboardInput implements Input{
	private String myInput;
	public KeyboardInput(KeyCode key){
		myInput = key.toString();
	}
	
	public String getInput(){
		return myInput;
	}
}
