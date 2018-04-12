package game_engine.components.collision;

import game_engine.Component;
import game_engine.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class CollidedComponent implements Component{
    private List<Entity> others;

    public CollidedComponent(){
        others = new ArrayList<Entity>();
    }

    public CollidedComponent(Entity e){
        this();
        addEntity(e);
    }

    public CollidedComponent(List<Entity> es){
        this();
        addEntities(es);
    }

    public void addEntity(Entity e){
        if(!others.contains(e)) {
            others.add(e);
        }
    }

    public void addEntities(List<Entity> es){
        for(Entity e: es){
            addEntity(e);
        }
    }

    public List<Entity> getEntities(){
        return others;
    }
    
    @Override
	public String getValues() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}
}
