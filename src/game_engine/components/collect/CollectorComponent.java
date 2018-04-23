package game_engine.components.collect;

import game_engine.Component;

public class CollectorComponent extends Component<Boolean>{

	public CollectorComponent(String arg){
		super(Boolean.getBoolean(arg));
	}
}
