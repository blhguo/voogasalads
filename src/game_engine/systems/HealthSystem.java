package game_engine.systems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.DamageComponent;
import game_engine.components.DespawnComponent;
import game_engine.components.HealthComponent;
import game_engine.NullType;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.level.Level;

/**
 * This system manages each entity's health status 
 * It checks the collisions of an entity and health is decreased based on the DamageComponent of the 
 * entity it collided with
 * @author Kevin Deng, Andy Nguyen, Jeremy Chen, Ben Hubsch
 *
 */

public class HealthSystem extends GameSystem {
	
	private static final Class<? extends Component<Double>> HEALTH = HealthComponent.class;
	private static final Class<? extends Component<List<Entity>>> TOP = TopCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> BOTTOM = BottomCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> RIGHT = RightCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> LEFT = LeftCollidedComponent.class;
	private static final Class<? extends Component<Double>> DAMAGE = DamageComponent.class;

	@Override
	public void act(double elapsedTime, Level level) {
		//Loops through entities with HealthComponent
		List<Class<? extends Component<?>>> args = Arrays.asList(HEALTH);
		List<Entity> healthyEntities = level.getEntitiesContaining(args);

		//Loops through entities with HealthComponent AND one of the Collided Components
		List<Class<? extends Component<?>>> collidedArgs = Arrays.asList(TOP, BOTTOM, RIGHT, LEFT);
		List<Entity> collidedEntities = level.getEntitiesContainingAny(healthyEntities, collidedArgs);
				
		for (Entity e : collidedEntities) {
			Component<Double> myHealth = e.getComponent(HEALTH);
			Component<List<Entity>> topCollision = e.getComponent(TOP);
			Component<List<Entity>> bottomCollision = e.getComponent(BOTTOM);
			Component<List<Entity>> rightCollision = e.getComponent(RIGHT);
			Component<List<Entity>> leftCollision = e.getComponent(LEFT);
			
			List<Entity> combinedList = new ArrayList<Entity>();
			if (topCollision != null) {
				combinedList.addAll(topCollision.getValue());
			}
			if (bottomCollision != null) {
				combinedList.addAll(bottomCollision.getValue());
			}
			if (rightCollision != null) {
				combinedList.addAll(rightCollision.getValue());
			}
			if (leftCollision != null) {
				combinedList.addAll(leftCollision.getValue());
			}
			
			for (Entity damager : combinedList) {
				if (damager.hasAll(Arrays.asList(DamageComponent.class))) {
					myHealth.setValue(myHealth.getValue() - damager.getComponent(DAMAGE).getValue());
				}
			}
			
			if(myHealth.getValue() <= 0){
				Component<NullType> despawn = new DespawnComponent();
				e.addComponent(despawn);
			}
		}

		
	}

}
