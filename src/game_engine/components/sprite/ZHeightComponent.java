package game_engine.components.sprite;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class ZHeightComponent extends Component<Integer>{

	public ZHeightComponent(String arg) {
		super(Integer.parseInt(arg));
	}

}
