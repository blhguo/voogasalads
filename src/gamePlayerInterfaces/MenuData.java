package gamePlayerInterfaces;

/** 
 *  This interface represents the connection between the DataManager and Menu Classes so that DataManager will always contain the most updated menu data.
 *  Additionally, this will populate the Menu with the correct settings during initialization
 */

public interface MenuData{

	public void updateMenuData(); // this will update DataManager so that it contains most updated menu data

	public void populateMenu(); //populates Menu with correct settings during initialization

}