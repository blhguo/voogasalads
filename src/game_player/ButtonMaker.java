package game_player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Dana Park, Brandon Dalla Rosa
 * Class that makes the buttons which are used for user interface.
 */
public class ButtonMaker {
	private Button play;
	private Button pause;
	private Button speedControl;
	private Button slowControl;
	private Button replay;
	private Button save;
	private Button about;
	private PlayerView playerView;
	private ArrayList<String> images = new ArrayList<String>(Arrays.asList("pause.png", "play.png", "slow.png","fast.png", "replay.png", "save.png", "about.png"));
	private ArrayList<Button> buttons =new ArrayList<Button>(Arrays.asList(pause, play, slowControl, speedControl, replay, save, about));

	/**
	 * Constructor which creates the instance prior to initialization.
	 */
	public ButtonMaker() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param button
	 * @param bundle
	 * @param string
	 * This short method just makes a generic button.
	 *
	 */
	protected Button makeButton(Button button, ResourceBundle bundle, String string) {
		button = new Button(bundle.getString(string));
		return button;
	}

	protected List<Button> makeMenuButton() {
		List<Button>buttonList = new ArrayList<Button>();

		for (int i=0; i<images.size(); i++) {
			Image im = new Image("game_player_resources/" + images.get(i));
			ImageView buttonImageView = new ImageView(im);

			buttonImageView.setFitHeight(30);
			buttonImageView.setFitWidth(30);

			Button button = buttons.get(i);
			button = new Button("", buttonImageView);
			button.getStyleClass().add("button-nav");
			String temp = ""+i;
			button.setOnAction(click -> {
				playerView.handleUI(Integer.decode(temp));
			});
			buttonList.add(button);

		}
		return buttonList;

	}
	
	/**
	 * This method is used to set the playerview, so that the buttons may access and alter it.
	 * 
	 * @param pv
	 */
	public void setPlayerView(PlayerView pv) {
		playerView = pv;
	}
}
