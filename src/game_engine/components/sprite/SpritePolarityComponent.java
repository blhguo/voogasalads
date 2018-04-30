package game_engine.components.sprite;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class SpritePolarityComponent extends Component<Integer>{

	public SpritePolarityComponent(String val) {
		super(Integer.parseInt(val));
	}

}
