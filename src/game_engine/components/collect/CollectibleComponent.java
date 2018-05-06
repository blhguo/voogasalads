package game_engine.components.collect;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies if a Component is collectible
 * getValue returns point value when collected
 * @author Kevin Deng
 */
@DataConditionable
public class CollectibleComponent extends Component<Double>{
	
	public CollectibleComponent(String arg){
		super(Double.parseDouble(arg));
	}

}
