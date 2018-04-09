package game_player;

import java.util.ResourceBundle;

import javafx.scene.control.Button;

/**
 * 
 * @author Dana Park, Brandon Dalla Rosa
 *
 */
public class ButtonMaker {

	public ButtonMaker() {
		// TODO Auto-generated constructor stub
	}

	protected Button makeButton(Button button, ResourceBundle bundle, String string) {
		button = new Button(bundle.getString(string));
		return button;
	}
}
