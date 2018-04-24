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
public abstract class CollidedComponent extends Component<List<Entity>>{

    public CollidedComponent(List<Entity> val){
    	super(val);
    }
    
    public CollidedComponent() {
    	super(new ArrayList<Entity>());
    }

    //COMMENTED OUT BY KEVIN 4/18/18 - question: why are there 3 constructors?
//    /**
//     * @param e
//     * If instantiated with the entity this Entity has collided with
//     */
//    public CollidedComponent(Entity e){
//        this();
//        addEntity(e);
//    }
//
//    /**
//     * @param es
//     * If instantiated with a list of entities that have collided with the entity containing the component
//     */
//    public CollidedComponent(List<Entity> es){
//        this();
//        addEntities(es);
//    }

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
    
    public boolean contains(Entity e) {
    	return getValue().contains(e);
    }
    
    public boolean contains(Class<? extends Component<?>> c) {
    	for(Entity e: getValue()) {
//    		if(e.getComponent(c)!=null) {
//    			return true;
//    		}
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
