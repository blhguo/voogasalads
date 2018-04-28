package game_engine.event;

import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.event.actions.macro.*;
import game_engine.event.actions.micro.*;

/**
 * @author Jeremy Chen, Kevin Deng, Andy Nguyen, Ben Hubsch
 * @param <T>
 *
 */

public class ActionFactory {
	private static final String ACTION_BUNDLE = "Action";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Action createAction(String name, List<Entity> entities, List<Component<T>> components,
			List<String> expressions, List<T> values, Engine engine) {
		
		switch(name) {
			case "AddEntityAction":
				return new AddEntityAction(entities.get(0), engine);
				
			case "LevelChangeAction":
				return new LevelChangeAction(engine, (int) values.get(0));
				
			case "PlayMusicAction":
				return new PlayMusicAction((String)values.get(0), (int)values.get(1)); 
				
			case "RemoveEntityAction":
				return new RemoveEntityAction(entities.get(0), engine, (int)values.get(0));
				
			case "AddComponentAction":
				return new AddComponentAction(entities.get(0), components.get(0));
				
			case "DataChangeAction":
				return new DataChangeAction(entities.get(0), (Class<? extends Component<Double>>) components.get(0).getClass(), expressions.get(0), (double) values.get(0));
			
			case "DataSetAction":
				return new DataSetAction(entities.get(0), components.getClass(), values.get(0));
				
			case "DataToggleAction":
				return new DataToggleAction(entities.get(0), components.get(0).getClass());
				
			case "RemoveComponentAction":
				return new RemoveComponentAction(entities.get(0), (Class<? extends Component<?>>) components.get(0).getClass());
			
			default: throw new ActionNotFoundException("Action name does not match any Actions");
		}
	}
}
