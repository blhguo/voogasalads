package game_engine.components;

import game_engine.Component;

/**
 * A null component that is used for specific actions that must take a null component parameter
 * @author Kevin D
 *
 */
public class NullComponent extends Component<String>{
	public NullComponent(String val){
		super(val);
	}
}
