package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.NullType;
import game_engine.components.DamageComponent;
import game_engine.components.DespawnComponent;
import game_engine.components.HealthComponent;
import game_engine.level.Level;
import game_engine.systems.collision.CollisionResponseSystem;

/**
 * This system manages each entity's health status 
 * It checks the collisions of an entity and health is decreased based on the DamageComponent of the 
 * entity it collided with
 * @author Kevin Deng, Andy Nguyen, Jeremy Chen, Ben Hubsch
 *
 */

public class HealthSystem extends CollisionResponseSystem {
	
	private static final Class<? extends Component<Double>> HEALTH = HealthComponent.class;
	private static final Class<? extends Component<Double>> DAMAGE = DamageComponent.class;

	@Override
	public void act(double elapsedTime, Level level) {
		//Loops through entities with HealthComponent
		List<Class<? extends Component<?>>> args = Arrays.asList(HEALTH);
		List<Entity> healthyEntities = level.getEntitiesContaining(args);

		//Loops through entities with HealthComponent AND one of the Collided Components
		List<Entity> collidedEntities = getCollidedEntities(healthyEntities, level);
				
		for (Entity e : collidedEntities) {
			Component<Double> myHealth = e.getComponent(HEALTH);
			List<Entity> combinedList = getAllCollidedWith(e);
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
