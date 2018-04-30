package game_engine.event.condition_factory;

import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.event.Condition;

public class ComponentCollisionConditionFactory extends ConditionFactory{

	@Override
	public Condition createCondition(Entity eventArg) {
		Component<Entity> entity = eventArg.getComponent(FactoryEntityComponent.class);
		Component<Component> comp = eventArg.getComponent(FactoryComponentComponent.class);
		Component<List<Component<?>>> compList = eventArg.getComponent(CollisionListComponent.class);

		return new Condition()
	}

}
