package game_engine.components;

import game_engine.Component;

public class DefaultNumberOfJumpsComponent extends Component<Double> {
	
	public DefaultNumberOfJumpsComponent(String arg) {
		super(Double.parseDouble(arg));
	}
	
}
