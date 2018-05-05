package game_engine.components;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;


/**
 * The Health Component defines how much Health Points an Entity has. Health decreases if Damage is dealt
 * to the Entity with the HealthComponent.
 * @author Kevin Deng
 *
 */
@DataConditionable
public class HealthComponent extends Component<Double> {
	public HealthComponent(String arg){
		super(Double.parseDouble(arg));
	}
}
