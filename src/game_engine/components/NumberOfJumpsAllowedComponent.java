package game_engine.components;

import game_engine.Component;

public class NumberOfJumpsAllowedComponent extends Component<Double> {
	public NumberOfJumpsAllowedComponent(String arg){
		super(Double.parseDouble(arg));
	}
}
