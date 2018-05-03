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
public abstract class CollidedComponent extends Component<List<Entity>> {

    public CollidedComponent(List<Entity> val){
    	super(val);
    }
    
    public CollidedComponent() {
    	super(new ArrayList<Entity>());
    }

    /**
     * @param e
     * Setter allowing System to add an entity to the list of entities contained in the component
     */
    public void addEntity(Entity e){
        if(!getValue().contains(e)) {
            getValue().add(e);
        }
    }
    
    /**
     * @param e
     * Setter allowing System to add an entity to the list of entities contained in the component
     */
    public void removeEntity(Entity e){
        if(getValue().contains(e)) {
        	getValue().remove(getValue().indexOf(e));
        }
    }
    
    /**
     * @param e
     * @return
     * 
     * Checks to see if the entity holding this component has collided with a specific entity
     */
    public boolean contains(Entity e) {
    	return getValue().contains(e);
    }
    
    /**
     * @param c
     * @return
     * 
     * Checks to see if the entity holding this component has collided with an entity containing a specific type of component
     */
    public <T> boolean contains(Class<? extends Component<T>> c) {
    	for(Entity e: getValue()) {
    		if(e.getComponent(c)!=null) {
    			return true;
    		}
    	}
    	return false;
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
}
