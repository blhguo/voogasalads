

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
