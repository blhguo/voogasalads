package game_player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Dana Park, Brandon Dalla Rosa
 *Class that makes a generic button
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
	private ArrayList<String> images = new ArrayList<String>(Arrays.asList("pause.png", "play.png", "slow.png","fast.png", "replay.png", "save.png","about.png"));
	private ArrayList<Button> buttons =new ArrayList<Button>(Arrays.asList(play, pause, slowControl, speedControl, replay, save, about));

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
	
	protected List<Button> makeMenuButton() {
		List<Button>buttonList = new ArrayList<Button>();

		for (int i=0; i<images.size(); i++) {
			System.out.println(images.get(i));
			ImageView buttonImageView = new ImageView( getClass().getResource( "/game_player_resources/"+images.get(i)).toExternalForm());

			buttonImageView.setFitHeight(30);
			buttonImageView.setFitWidth(30);
	
			Button button = buttons.get(i);
			button = new Button("", buttonImageView);
			button.getStyleClass().add("button-nav");
			int toSend = 0+i;
			button.setOnAction(click -> {
				playerView.handleUI(toSend);
			});
			buttonList.add(button);

			}
		return buttonList;

		}
		public void setPlayerView(PlayerView pv) {
			playerView = pv;
		}
	}

