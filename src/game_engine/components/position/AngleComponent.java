package game_engine.components.position;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the angle of rotation of an entity
 * @author Jeremy Chen
 *
 */
@DataConditionable
public class AngleComponent extends Component<Double> {

	public AngleComponent(String arg) {
		super(Double.parseDouble(arg));
	}
	
}
