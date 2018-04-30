package game_engine.systems.collision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.level.Level;

public abstract class CollisionResponseSystem extends GameSystem {
	private static final Class<? extends Component<List<Entity>>> TOP = TopCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> BOTTOM = BottomCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> RIGHT = RightCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> LEFT = LeftCollidedComponent.class;
	private static final List<Class<? extends Component<?>>> COLLIDED_ARGS = Arrays.asList(TOP, BOTTOM, RIGHT, LEFT);

	protected List<Entity> getCollidedEntities(Level level) {
		return level.getEntitiesContainingAny(COLLIDED_ARGS);
	}

	protected List<Entity> getCollidedEntities(List<Entity> entities, Level level) {
		return level.getEntitiesContainingAny(entities, COLLIDED_ARGS);
	}

	protected List<Entity> getAllCollidedWith(Entity e) {
		List<Entity> entitiesCollidedWith = new ArrayList<>();

		COLLIDED_ARGS.stream().forEach(cc -> {
			List<Entity> others = (List<Entity>) e.getComponent(cc).getValue();
			if (others != null) {
				entitiesCollidedWith.addAll(others);
			}
		});
		return entitiesCollidedWith;
	}
}
