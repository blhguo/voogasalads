package game_player_interfaces;

/** 
 * @author Dana Park 
 * 
 *  This interface represents the connection between the DataManager and Menu Classes so that DataManager will always contain the most updated menu data.
 *  Additionally, this will populate the Menu with the correct settings during initialization
 */

public interface MenuData{

	public void updateMenuData(); 
	
	public void populateMenu(); 

}