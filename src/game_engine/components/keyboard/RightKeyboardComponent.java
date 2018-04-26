package game_engine.components.keyboard;

import game_engine.Component;
import javafx.scene.input.KeyCode;

public class RightKeyboardComponent extends Component<KeyCode>{
	public RightKeyboardComponent(String k) {
		super(KeyCode.valueOf(k));
	}
}
