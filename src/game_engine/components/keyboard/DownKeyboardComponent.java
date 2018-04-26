package game_engine.components.keyboard;

import game_engine.Component;
import javafx.scene.input.KeyCode;

public class DownKeyboardComponent extends Component<KeyCode>{
	public DownKeyboardComponent(String arg) {
		super(KeyCode.valueOf(arg));
	}
}
