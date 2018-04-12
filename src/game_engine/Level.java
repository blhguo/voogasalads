package game_engine;
import java.util.ArrayList;
import java.util.List;

public class Level {
	
	private List<Entity> myLevel = new ArrayList<>();
	
    public void addEntity(Entity e) {
    		myLevel.add(e);
    }

    public void remove(Entity e) {myLevel.remove(e);}
    
    public Iterable<Entity> getEntities() {
    	return () -> myLevel.iterator();
    }
}
