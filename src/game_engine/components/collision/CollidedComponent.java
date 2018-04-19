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
    //private List<Entity> others;

    /**
     * TODO: FIX LATER THIS IS TRASH
     * Constructor: takes no args, created by System rather than authoring
     */
    public CollidedComponent(List<Entity> val){
    	super(val);
        //others = new ArrayList<Entity>();
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
        if(!others.contains(e)) {
            others.add(e);
        }
    }
    
    public boolean contains(Entity e) {
    	return others.contains(e);
    }
    
    public boolean contains(Class<Component> c) {
    	for(Entity e: others) {
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

    /**
     * @return
     * Getter that gets all the entities contained in this component
     */
    public List<Entity> getEntities(){
        return others;
    }

}
