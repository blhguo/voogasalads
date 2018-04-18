package game_engine.components.collision;

import java.util.ArrayList;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;

/**
 * @author Jeremy
 * This class represents a Component that serves as a flag for Systems to recognize that the Entity possessing this component has collided with another entity
 * It is an abstract superclass for Top, Bottom, Right, and LeftCollidedComponent
 */
public abstract class CollidedComponent implements Component{
    private List<Entity> others;

    /**
     * Constructor: takes no args, created by System rather than authoring
     */
    public CollidedComponent(){
        others = new ArrayList<Entity>();
    }

    /**
     * @param e
     * If instantiated with the entity this Entity has collided with
     */
    public CollidedComponent(Entity e){
        this();
        addEntity(e);
    }

    /**
     * @param es
     * If instantiated with a list of entities that have collided with the entity containing the component
     */
    public CollidedComponent(List<Entity> es){
        this();
        addEntities(es);
    }

    /**
     * @param e
     * Setter allowing System to add an entity to the list of entities contained in the component
     */
    public void addEntity(Entity e){
        if(!others.contains(e)) {
            others.add(e);
        }
    }

    /**
     * @param es
     * Setter allowing a System to add multiple entities to the list of entities contained in the component
     */
    public void addEntities(List<Entity> es){
        for(Entity e: es){
            addEntity(e);
        }
    }

    /**
     * @return
     * Getter that gets all the entities contained in this component
     */
    public List<Entity> getEntities(){
        return others;
    }
    
    /* (non-Javadoc)
     * @see game_engine.Component#getValues()
     * Placeholder, for stopgap code in Authoring
     */
    @Override
	public String getValues() {
		return null;
	}

	/* (non-Javadoc)
	 * @see game_engine.Component#getName()
	 * Placeholder, for stopgap code in Authoring
	 */
	@Override
	public String getName() {
		return null;
	}
}
