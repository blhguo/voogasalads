#### Internal API

```java
/** 
 *	This interface represents the connection between the DataManager and Menu Classes so that DataManager will always contain the most updated menu data.
 *	Additionally, this will populate the Menu with the correct settings during initialization
 */

public interface MenuData{

public void updateMenuData(); // this will update DataManager so that it contains most updated menu data

public void populateMenu(); //populates Menu with correct settings during initialization

/**
 *	This interface represents the connection between the DataManager and ViewManager Classes so that DataManager will always contain the most updated    
 *	HUD data. 
 */

public interface ViewData{

public void updateViewData(); //this will update DataManager as ViewManager updates

public void setHUD(); //this will set HUD to correct settings

```
#### External API

```java
/**
 *	This interface allows information from DataManager to be exported to save preferences and save the current game state.
 */

public interface ExportData{

public void exportCurrentState(); //exports current game state

public void exportPreferences(); //exports saved preferences

/**
 *	This interface allows for information from the saved data files to be read into the DataManager class. 
 *	This interface also involves pulling in initial game information from Game Authoring Environment
 */

public interface ImportData{

public void importGame(); // imports an entire game 

public void importPreferences(); // imports saved preferences for a game

public void importGameState(); //imports saved Game State 
```