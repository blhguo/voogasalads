package game_engine.components.projectile;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;
import javafx.scene.input.KeyCode;

@DataConditionable
public class ProjectileKeyboardInputComponent extends Component<KeyCode>{

	public ProjectileKeyboardInputComponent(String arg) {
		super(KeyCode.valueOf(arg));
	}
	
}
