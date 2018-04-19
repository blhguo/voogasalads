package game_engine.components;

import game_engine.Component;

public class DefaultNumberOfJumpsComponent extends Component<Integer> {
	public DefaultNumberOfJumpsComponent(String arg) {
		super(Integer.parseInt(arg));
	}
}
