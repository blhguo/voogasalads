/**
 *  This interface represents the connection between the DataManager and ViewManager Classes so that DataManager will always contain the most updated    
 *  HUD data. 
 */

public interface ViewData{

public void updateViewData(); //this will update DataManager as ViewManager updates

public void setHUD(); //this will set HUD to correct settings
