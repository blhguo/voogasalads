/**
 * USE CASE TO SOLVE: The user saves game progress and is able to play from their saved point later
 */
 
 
/**
 * Using the interfaces MenuData, ViewData, ExportData, and ImportData, we will call these methods to save the game progress:
 * First, the Menu Data and View Data will be updated, and then the current preferences and state will be exported.
 */

public void saveData() {
    
    updateMenuData(); //this method will update DataManager from the Menu HBox information on top of the screen
    updateViewData(); //this method will update DataManager as ViewManager updates
    exportPreferences(); //this method will export the current player preferences
    exportCurrentState(); //this method will export the current game state
    
    }

/**
 * The methods below will be used to allow the player to restart the game from a saved point later on:
 * First, the current preferences and game state will be imported, and then the Menu will be populated with the correct setting and the HUD will be set.
 */
 
public void loadData(){
    
    importPreferences(); //this method will import current player preferences
    importGameState(); //this method will import current game state
    populateMenu(); //this method will populate the Menu HBox with correct settings during initialization
    setHUD(); //this method will set the HUD to the correct settings during initialization
    
    }

