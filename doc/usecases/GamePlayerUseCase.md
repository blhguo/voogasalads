### The user saves game progress and is able to play from their saved point later


Using the interfaces MenuData, ViewData, ExportData, and ImportData, we will call these methods to save the game progress:

updateMenuData()
updateViewData()
exportPreferences()
exportCurrentState()

* First, the Menu Data and View Data will be updated, and then the current preferences and state will be exported.

The methods below will be used to allow the player to restart the game from a saved point later on:

importPreferences()
importGameState()
populateMenu()
setHUD()

* First, the current preferences and game state will be imported, and then the Menu will be populated with the correct setting and the HUD will be set.