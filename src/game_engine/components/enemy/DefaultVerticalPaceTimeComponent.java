package game_engine.components.enemy;

import game_engine.Component;

/** Specifies the default oscillation period of an entity that can pace back and forth vertically
* @author Ben Hubsch, Kevin Deng, Andy Nguyen, Jeremy Chen
*
*/

public class DefaultVerticalPaceTimeComponent extends Component<Double> {

	public DefaultVerticalPaceTimeComponent(String val) {
		super(Double.parseDouble(val));
	}

}
