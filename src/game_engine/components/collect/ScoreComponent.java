package game_engine.components.collect;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Score component keeps track of a specific entity's score (as defined by events)
 * getValue() returns the score as a double
 * @author Kevin Deng, Ben Hubsch, Andy Nguyen, Jeremy Chen
 *
 */
@DataConditionable
public class ScoreComponent extends Component<Double>{

	public ScoreComponent(String arg){
		super(Double.parseDouble(arg));
	}
	
}
