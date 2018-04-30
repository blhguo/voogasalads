package game_engine.components.sprite;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class VisibilityComponent extends Component<Boolean> {

	public VisibilityComponent(String arg) {
		super(Boolean.parseBoolean(arg));
	}
	
}
