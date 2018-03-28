#### Internal API

/**
 *	This interface represents the connection between the DataManager and Menu Classes so that DataManager will always contain the most updated menu data.
 *	Additionally, this will populate the Menu with the correct settings during initialization
 */

public interface MenuData{


/**
 *	This interface represents the connection between the DataManager and ViewManager Classes so that DataManager will always contain the most updated    
 *	HUD data. 
 */

public interface ViewData{


#### External API

/**
 *	This interface allows information from DataManager to be exported to save preferences and save the current game state.
 */

public interface ExportData{

/**
 *	This interface allows for information from the saved data files to be read into the DataManager class. 
 *	This interface also involves pulling in initial game information from Game Authoring Environment
 */

public interface ImportData{
