package game_player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.scene.input.KeyCode;
/**
 * 
 * @author Brandon Dalla Rosa
 * Simple test class for Player functionality regarding the DataManager class
 *
 */
public class GamePlayerTester {
	DataManager dataManager = new DataManager();
	@Test
	
/**
 * 
 * method tests that Key Preferences are correctly saved by Player once set 
 *
 */
	public void testKeyPreferences() {
		dataManager.setKey("Front", KeyCode.W);
		String ret = dataManager.getInput(KeyCode.W);
		assertEquals("Preference not saved correctly","Front",ret);
	}
}
