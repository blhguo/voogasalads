package game_engine.components.collision;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class PassableComponent extends Component<Boolean> {
	
	public PassableComponent(String arg) {
		super(Boolean.parseBoolean(arg));
	}
	
}
