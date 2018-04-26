package game_engine.components.keyboard;

import game_engine.Component;
import javafx.scene.input.KeyCode;

public class LeftKeyboardComponent extends Component<KeyCode>{
	public LeftKeyboardComponent(String arg) {
		super(KeyCode.valueOf(arg));
	}
}
