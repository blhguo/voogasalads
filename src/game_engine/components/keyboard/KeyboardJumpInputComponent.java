package game_engine.components.keyboard;

import game_engine.Component;
import javafx.scene.input.KeyCode;

public class KeyboardJumpInputComponent extends Component<KeyCode>{
	public KeyboardJumpInputComponent(String arg){
		super(KeyCode.getKeyCode(arg));
	}
}
