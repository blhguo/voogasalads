package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class LevelCreator {

	ArrayList<Level> currentLevels;			//where to create Level class?
											//delete import stmt for logging.Level once class is created
	
	public LevelCreator() {
		currentLevels = new ArrayList<Level>();
	}
	
	public void addLevel(Level l) {
		currentLevels.add(l);
	}
	
	public List<Level> getLevels() {
		return currentLevels;
	}
}
