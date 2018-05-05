package game_engine.event;

import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.event.conditions.ComponentCollisionCondition;
import game_engine.event.conditions.DataCondition;
import game_engine.event.conditions.EntityCollisionCondition;
import game_engine.event.conditions.KeyboardInputCondition;
import game_engine.event.conditions.MouseInputCondition;

/**
 * The purpose of this class is to provide the Authoring environment a way to create Conditions
 * @author Andy Nguyen, Kevin Deng, Jeremy Chen, Ben Hubsch
 *
 */
public class ConditionFactory {
	public static Condition createCondition(String name, List<Entity> entities, List<Class<Component<?>>> components, List<String> args, Engine engine){
		switch(name){
		case "ComponentCollisionCondition":
			return new ComponentCollisionCondition(entities.get(0), components.get(0));
		case "DataCondition":
			return new DataCondition(entities.get(0), components.get(0), args.get(0), args.get(1));
		case "EntityCollisionCondition":
			return new EntityCollisionCondition(entities.get(0), entities.get(1));
		case "KeyboardInputCondition":
			return new KeyboardInputCondition(engine, args.get(0));
		case "MouseInputCondition":
			return new MouseInputCondition(engine, entities.get(0));
		default:
			throw new ConditionNotFoundException("Condition name does not match any Conditions");		
		}
	}
	

}
