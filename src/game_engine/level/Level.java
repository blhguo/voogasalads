package game_engine.level;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.Component;
import game_engine.Entity;
import game_engine.event.Event;

/**
 * 
 * @author benhubsch
 * 
 *         This class is simply a convenient data structure to store the Entity objects in a given
 *         level.
 */
public class Level {

	private List<Entity> myEntities = new ArrayList<>();
	private List<Event> myEvents;
	private int myId;
	
	public Level(int id){
		myId = id;
	}
	
	/**
	 * Gets the List<Entity> object containing all Entities with only these Components.
	 *
	 * @param args the args
	 * @return List<Entity>
	 */
	public List<Entity> getEntitiesContaining(List<Class<? extends Component<?>>> args) {
		return myEntities.stream().filter(e -> e.hasAll(args)).collect(Collectors.toList());
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
		return myEntities.stream().filter(e -> e.hasAny(args)).collect(Collectors.toList());
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
		myEntities.add(e);
	}

	/**
	 * Adds the entity to the backend. This is used during the various instantiation phases.
	 *
	 * @param entities the entities
	 */
	public void addEntities(List<Entity> entities) {
		for (Entity e : entities) {
			myEntities.add(e);
		}
	}
	
	/**
	 * Adds the entity to the backend. This is used during the various instantiation phases.
	 *
	 * @param e the e
	 */
	public void removeEntity(Entity e) {
		myEntities.remove(e);
	}
	
	public List<Entity> getEntities() {
		return myEntities;
	}

	public void checkEvents() {
		for (Event event : myEvents) {
			event.occur();
		}
	}
	
	public int getId(){
		return myId;
	}
	
	// public Level addEvent()
	
	// public Level removeEvent()

}
