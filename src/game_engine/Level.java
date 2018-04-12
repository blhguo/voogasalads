package game_engine;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author benhubsch
 * 
 *         This class is simply a convenient data structure to store the Entity objects in a given
 *         level.
 */
public class Level {

	private List<Entity> myLevel = new ArrayList<>();

	/**
	 * Adds the entity to the LEvel.
	 *
	 * @param e the e
	 */
	public void addEntity(Entity e) {
		myLevel.add(e);
	}

	/**
	 * Gets the Iterable<Entity> object.
	 *
	 * @return Iterable<Entity>
	 */
	public Iterable<Entity> getEntities() {
		return () -> myLevel.iterator();
	}
}
