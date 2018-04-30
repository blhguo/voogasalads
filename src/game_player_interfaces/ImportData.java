package game_player_interfaces;

/**
 * @author Dana Park
 *  This interface allows for information from the saved data files to be read into the DataManager class. 
 *  This interface also involves pulling in initial game information from Game Authoring Environment
 */

public interface ImportData{

	public void importGame(); 

	public void importPreferences(); 

	public void importGameState(); 
	
}

