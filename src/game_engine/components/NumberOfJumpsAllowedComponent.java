package game_engine.components;

import game_engine.Component;

public class NumberOfJumpsAllowedComponent extends Component<Integer> {
	public NumberOfJumpsAllowedComponent(String arg){
		super(Integer.parseInt(arg));
	}
}
