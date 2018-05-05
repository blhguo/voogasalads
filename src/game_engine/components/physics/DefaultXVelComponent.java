package game_engine.components.physics;

import game_engine.Component;

/**
 * The DefaultXVelComponent specifies What the X velocity of an entity changes to when xvel is changed at all
 * @author Kevin Deng
 *
 */
public class DefaultXVelComponent extends Component<Double> {

	public DefaultXVelComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
