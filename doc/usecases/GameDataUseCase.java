/**
 * USE CASE TO SOLVE: The user saves the current game designed by the author in the Game Authoring Environment
 */
 
 
/**
 * Using the interfaces MenuData, ViewData, ExportData, and ImportData, we will call these methods to save the game progress:
 * First, the Menu Data and View Data will be updated, and then the current preferences and state will be exported.
 */

public void SaveData() {
    
    updateMenuData(); //this method will update DataManager from the Menu HBox information on top of the screen
    updateViewData(); //this method will update DataManager as ViewManager updates
    exportPreferences(); //this method will export the current player preferences
    exportCurrentState(); //this method will export the current game state
    
    }
