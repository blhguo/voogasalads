package gameplayer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.scene.input.KeyCode;

public class GamePlayerTester {
	DataManager dataManager = new DataManager();
	@Test
	public void testKeyPreferences() {
		dataManager.setKey("Front", KeyCode.W);
		String ret = dataManager.getInput(KeyCode.W);
		assertEquals("Preference not saved correctly","Front",ret);
	}
}
