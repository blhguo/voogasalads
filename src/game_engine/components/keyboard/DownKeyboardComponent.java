package game_engine.components.keyboard;

import game_engine.Component;
import javafx.scene.input.KeyCode;

/**
 * Specifies what Key corresponds to the Down movement of an entity
 * @author Kevin Deng
 *
 */
public class DownKeyboardComponent extends Component<KeyCode>{
	public DownKeyboardComponent(String arg) {
		super(KeyCode.valueOf(arg));
	}
}
