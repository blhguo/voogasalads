package game_engine.components;

import java.util.List;

import game_engine.Component;
import javafx.scene.input.KeyCode;

public class MovementInputComponent implements Component {
	private KeyCode myLeft;
	private KeyCode myRight;
	private KeyCode myUp;
	private KeyCode myDown;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public MovementInputComponent(List<String> args) {
		myLeft = KeyCode.valueOf(args.get(0));
		myRight = KeyCode.valueOf(args.get(1));
		myUp = KeyCode.valueOf(args.get(2));
		myDown = KeyCode.valueOf(args.get(3));
	}
	
	public KeyCode getLeft() {
		return myLeft;
	}
	
	public KeyCode getRight() {
		return myRight;
	}
	
	public KeyCode getUp() {
		return myUp;
	}
	
	public KeyCode getDown() {
		return myDown;
	}

	@Override
	public String getValues() {
		return null;
	}

	@Override
	public String getName() {
		return "Movement Input";
	}
}
