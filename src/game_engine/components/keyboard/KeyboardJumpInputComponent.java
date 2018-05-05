package game_engine.components.keyboard;

import game_engine.Component;
import javafx.scene.input.KeyCode;

/**
 * Specifies what key corresponds to the Jump component and movement of an entity
 * @author Kevin Deng
 *
 */
public class KeyboardJumpInputComponent extends Component<KeyCode>{
	public KeyboardJumpInputComponent(String arg){
		super(KeyCode.getKeyCode(arg));
	}
}
