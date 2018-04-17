package game_player;

import java.util.ResourceBundle;

import javafx.scene.control.Button;

/**
 * 
 * @author Dana Park, Brandon Dalla Rosa
 *Class that makes a generic button
 */
public class ButtonMaker {

	public ButtonMaker() {
		// TODO Auto-generated constructor stub
	}
/**
 * 
 * @param button
 * @param bundle
 * @param string
 * This short method just makes a generic button
 *
 */
	protected Button makeButton(Button button, ResourceBundle bundle, String string) {
		button = new Button(bundle.getString(string));
		return button;
	}
}
