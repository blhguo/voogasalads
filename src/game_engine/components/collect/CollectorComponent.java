package game_engine.components.collect;

import game_engine.Component;
import game_engine.NullType;

/**
 * Specifies if an Entity can collect other Entities that have the Collectible Component
 * @author Kevin Deng
 *
 */
public class CollectorComponent extends Component<NullType>{

	public CollectorComponent(){
		super(null);
	}
	public CollectorComponent(String arg){
		super(null);
	}
	
}
