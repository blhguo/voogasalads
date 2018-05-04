package game_engine.components.collision;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
@Deprecated
public class PushableComponent extends Component<Boolean> {
	
	public PushableComponent(String arg) {
		super(Boolean.parseBoolean(arg));
	}
	
}
