package game_player_interfaces;

/**
 * @author Dana Park
 * 
 *  This interface represents the connection between the DataManager and ViewManager Classes so that DataManager will always contain the most updated    
 *  HUD data. 
 */

public interface ViewData{

	public void updateViewData();
	
	public void setHUD(); 
	
}

