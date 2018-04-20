package game_engine.components.collect;

import game_engine.Component;

/**
 * Specifies if a Component is collectible
 * getValue returns point value when collected
 */

public class CollectibleComponent extends Component<Double>{
	
	public CollectibleComponent(String arg){
		super(Double.parseDouble(arg));
	}

}
