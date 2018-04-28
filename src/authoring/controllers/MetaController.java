package authoring.controllers;

import gameData.ManipData;

public class MetaController {
	
	private ManipData data;
	private LevelController lcontroller;
	
	public MetaController(LevelController lc){
		data = new ManipData();
		lcontroller = lc;
	}
	
	/**
	 * Passes the current levels array to data
	 */
	public void saveGame() {
		// need all 3 parameters
		//data.saveData(currentLevels);
		//or .saveData(currentLevels, currentAttributes)
	}

}
