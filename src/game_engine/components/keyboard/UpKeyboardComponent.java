package game_engine.components.keyboard;

import game_engine.Component;
import javafx.scene.input.KeyCode;

/**
 * Specifies what key corresponds to the Up movement of an entity
 * @author Kevin Deng
 *
 */
public class UpKeyboardComponent extends Component<KeyCode>{
	public UpKeyboardComponent(String arg) {
		super(KeyCode.valueOf(arg));
	}
}
