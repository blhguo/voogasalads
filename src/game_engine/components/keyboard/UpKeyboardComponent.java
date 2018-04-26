package game_engine.components.keyboard;

import game_engine.Component;
import javafx.scene.input.KeyCode;

public class UpKeyboardComponent extends Component<KeyCode>{
	public UpKeyboardComponent(String k) {
		super(KeyCode.valueOf(k));
	}
}
