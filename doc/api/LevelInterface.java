
/**
 * 
 * @author Kevin D
 *
 */

/**
 * A Level is a data structure that can be written to and read by the Game Authoring as well as the Game Player.
 * Through the Level interface, the Game Authoring should be able to add and remove entities in the given Level.
 * The Game Player should be able to read from these entities from the Level.
 * The general functionality for the Level module is shown below:
 * @author Kevin D
 *
 */
public interface LevelInterface{
	/**
	 * Returns all entities present in the level
	 * @return
	 */
    Collection<Entity> getEntities();
    
    /**
     * Returns all the events present in the level
     * @return
     */
    Collection<Entity> getEvents();
    
    /**
     * Adds an entity to the level
     * @param ent
     */
    void addEntity(Entity ent);
    
    /**
     * Removes an entity from the level
     * @param ent
     */
    void removeEntity(Entity ent);
}
