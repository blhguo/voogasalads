package game_engine.components.projectile;

import game_engine.Component;
import javafx.scene.input.KeyCode;

public class ProjectileKeyboardInputComponent extends Component<KeyCode>{

	public ProjectileKeyboardInputComponent(String arg) {
		super(KeyCode.valueOf(arg));
	}
	
}
