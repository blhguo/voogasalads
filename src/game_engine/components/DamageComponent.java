package game_engine.components;

import game_engine.Component;

/**
 * getValue() returns value of damage done to an entity (should be a negative number)
 * @author Kevin Deng, Andy Nguyen, Jeremy Chen, Ben Hubsch
 *
 */

public class DamageComponent extends Component<Double>{
	
	public DamageComponent(String arg){
		super(Double.parseDouble(arg));
	}

}
