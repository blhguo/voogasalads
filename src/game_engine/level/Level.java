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
public class Level extends Entity {
	private List<Entity> myEntities = new ArrayList<>();
	private List<Event> myEvents = new ArrayList<>();
	private int myId;

	/**
	 * instantiates a new Level object with a given id
	 * @param id
	 */
	public Level(int id) {
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
	public void removeLastEvent(){
		myEvents.remove(myEvents.size() - 1);
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
	 * Removes the entity from the backend. This is used during the various instantiation phases.
	 *
	 * @param e the e
	 */
	public void removeEntity(Entity e) {
		myEntities.remove(e);
	}

	/**
	 * gets the current list of entities in the level
	 * @return
	 */
	public List<Entity> getEntities() {
		return myEntities;
	}

	/**
	 * checks/executes all events in the current list of events
	 */
	public void checkEvents() {
		for (Event event : myEvents) {
			event.occur();
		}
	}

	/**
	 * returns the id of this level
	 * @return
	 */
	public int getId() {
		return myId;
	}

	/**
	 * adds an event to the current list of events
	 * @param event
	 */
	public void addEvent(Event event) {
		myEvents.add(event);
	}

	/**
	 * removes the events from the current list of events
	 * @param event
	 */
	public void removeEvent(Event event) {
		myEvents.remove(event);
	}

}
