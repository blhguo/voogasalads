package gamePlayerInterfaces;

/**
 *  This interface allows for information from the saved data files to be read into the DataManager class. 
 *  This interface also involves pulling in initial game information from Game Authoring Environment
 */

public interface ImportData{

	public void importGame(); // imports an entire game 

	public void importPreferences(); // imports saved preferences for a game

	public void importGameState(); //imports saved Game State 
	
}

