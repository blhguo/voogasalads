package game_engine;

import java.util.List;
import java.util.stream.Collectors;

public class Engine {
	private List<Level> myLevels;
	private Level myCurrentLevel;
	
	public Engine(List<Level> levels, Level startLevel) {
		myLevels = levels;
		myCurrentLevel = startLevel;
	}
	
	/**
	 * Gets the List<Entity> object containing all Entities with only these Components.
	 *
	 * @param args the args
	 * @return List<Entity>
	 */
	public List<Entity> getEntitiesContaining(List<Class<? extends Component<?>>> args) {
		return myCurrentLevel.getEntities().stream().filter(e -> e.hasAll(args)).collect(Collectors.toList());
	}
	
	/**
	 * @param entities
	 * @param args
	 * @return
	 */
	public List<Entity> getEntitiesContaining(List<Entity> entities, List<Class<? extends Component<?>>> args) {
		return entities.stream().filter(e -> e.hasAll(args)).collect(Collectors.toList());
	}

	/**
	 * Gets the List<Entity> object containing all Entities with these Components.
	 *
	 * @param args the args
	 * @return List<Entity>
	 */
	public List<Entity> getEntitiesContainingAny(List<Class<? extends Component<?>>> args) {
		return myCurrentLevel.getEntities().stream().filter(e -> e.hasAny(args)).collect(Collectors.toList());
	}
	

	/**
	 * @param entities
	 * @param args
	 * @return
	 */
	public List<Entity> getEntitiesContainingAny(List<Entity> entities, List<Class<? extends Component<?>>> args) {
		return entities.stream().filter(e -> e.hasAny(args)).collect(Collectors.toList());
	}

	/**
	 * Adds the entity to the backend. This is used during the various instantiation phases.
	 *
	 * @param e the e
	 */
	public void addEntity(Entity e) {
		myCurrentLevel.addEntity(e);
	}

	/**
	 * Adds the entity to the backend. This is used during the various instantiation phases.
	 *
	 * @param entities the entities
	 */
	public void addEntities(List<Entity> entities) {
		for (Entity e : entities) {
			myCurrentLevel.addEntity(e);
		}
	}
	
	/**
	 * Adds the entity to the backend. This is used during the various instantiation phases.
	 *
	 * @param e the e
	 */
	public void removeEntity(Entity e) {
		myCurrentLevel.remove(e);
	}
	
	public void setLevel(Level level){
		myCurrentLevel = level;
	}
	
}
