package game_engine.components;

import java.util.UUID;

import game_engine.Component;

/**
 * The Prime Component specifies which entity the Camera focuses on during the playing of the game
 * @author Kevin D
 *
 */
public class PrimeComponent extends Component<UUID> {

	public PrimeComponent() {
		super(null);
	}
	public PrimeComponent(String arg) {
		super(null);
	}
}
