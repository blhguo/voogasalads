package game_engine.systems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.NullType;
import game_engine.components.DespawnComponent;
import game_engine.components.collect.CollectibleComponent;
import game_engine.components.collect.CollectorComponent;
import game_engine.components.collect.ScoreComponent;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.level.Level;

/**
 * 
 * @author Andy Nguyen
 * The purpose of this system is to provide default behavior to entities containing the collectors and collectibles within
 * the game. An example of this system being put into action would be with Mario (entity containing the Score and Collector component)
 * collecting a coin (Entity containing collectible component). This system despawns collectible entities from the level upon collision
 * and also adds the collectible's value to the score of the collector entity.
 *
 */
public class CollectibleSystem implements GameSystem {
	private static final Class<? extends Component<List<Entity>>> TOP = TopCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> RIGHT = RightCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> BOTTOM = BottomCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> LEFT = LeftCollidedComponent.class;
	private static final Class<? extends Component<Double>> SCORE = ScoreComponent.class;
	private static final Class<? extends Component<NullType>> COLLECTOR = CollectorComponent.class;
	private static final Class<? extends Component<Double>> COLLECTIBLE = CollectibleComponent.class;

	/**
	 * Despawns entities containing the collectible component upon collision with a collector and adds that 
	 * component's value to the score component of the collector entity it is colliding with.
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Entity> collidedEntities = level.getEntitiesContainingAny(Arrays.asList(TOP, RIGHT, BOTTOM, LEFT));
		List<Entity> collidedCollectors = level.getEntitiesContaining(collidedEntities, Arrays.asList(COLLECTOR, SCORE));
		for (Entity entity : collidedCollectors) {
			List<Entity> collidedWith = getCollidedEntitiesOn(entity, Arrays.asList(TOP, BOTTOM, RIGHT, LEFT));
			for (Entity other : collidedWith) {
				if (other.hasAll(Arrays.asList(COLLECTIBLE))) {
					Component<Double> entityScore = entity.getComponent(SCORE);
					Component<Double> collectible = other.getComponent(CollectibleComponent.class);
					entityScore.setValue(entityScore.getValue() + collectible.getValue());
					Component<NullType> despawnComponent = new DespawnComponent();
					other.addComponent(despawnComponent);
				}
			}
		}
	}

	private List<Entity> getCollidedEntitiesOn(Entity entity, List<Class<? extends Component<List<Entity>>>> sides) {
		List<Entity> collidedEntities = new ArrayList<>();
		for (Class<? extends Component<List<Entity>>> collisionSide : sides) {
			Component<List<Entity>> comp = entity.getComponent(collisionSide);
			if (comp != null) {
				collidedEntities.addAll(comp.getValue());
			}
		}
		return collidedEntities;
	}

}
