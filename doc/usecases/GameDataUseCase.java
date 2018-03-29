/**
 * USE CASE TO SOLVE: The user saves the current game designed by the author in the Game Authoring Environment
 */
 
 
/**
 * Using the interfaces DataManipulation for us to manipulate data to be serialized/deserialized and saved. 
 */

public SaveData implements DataManipulation {
    
    parseData(DataObject do); //this method will be called to parse through the data object especially if it has nested object to be serialized
    
    saveToFile(); //once the parseData is called and the DataObject is serialized, we an now save the information to a file.
    
    }
