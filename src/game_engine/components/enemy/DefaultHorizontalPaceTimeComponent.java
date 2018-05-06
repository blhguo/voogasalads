package game_engine.components.enemy;

import game_engine.Component;

/**
 * Specifies the default oscillation period of an entity that can pace back and forth horizontally
 * @author Kevin Deng
 *
 */
public class DefaultHorizontalPaceTimeComponent extends Component<Double> {

	public DefaultHorizontalPaceTimeComponent(String val) {
		super(Double.parseDouble(val));
	}

}
