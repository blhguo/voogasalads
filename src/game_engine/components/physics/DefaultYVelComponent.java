package game_engine.components.physics;

import game_engine.Component;

/**
 *  * The DefaultYVelComponent specifies What the Y velocity of an entity changes to when yvel is changed at all

 * @author Kevin Deng
 *
 */
public class DefaultYVelComponent extends Component<Double> {

	public DefaultYVelComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
