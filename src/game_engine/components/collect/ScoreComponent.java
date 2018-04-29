package game_engine.components.collect;

import game_engine.Component;

/**
 * Score component keeps track of a specific entity's score (as defined by events)
 * getValue() returns the score as a double
 * @author Kevin Deng, Ben Hubsch, Andy Nguyen, Jeremy Chen
 *
 */

public class ScoreComponent extends Component<Double>{

	public ScoreComponent(String arg){
		super(Double.parseDouble(arg));
	}
}
