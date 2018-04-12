package game_player_interfaces;

/**
 * @author Dana Park
 * 
 *  This interface allows information from DataManager to be exported to save preferences and save the current game state.
 */

public interface ExportData{

	public void exportCurrentState();

	public void exportPreferences(); 
	
}

