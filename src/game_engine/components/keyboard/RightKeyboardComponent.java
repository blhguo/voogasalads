package game_engine.components.keyboard;

import game_engine.Component;
import javafx.scene.input.KeyCode;

/**
 * Specifies what key corresponds to the Right movement of an entity
 * @author Kevin Deng
 *
 */
public class RightKeyboardComponent extends Component<KeyCode>{
	public RightKeyboardComponent(String arg) {
		super(KeyCode.valueOf(arg));
	}
}
