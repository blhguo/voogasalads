package game_player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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
	private PlayerView playerView;
	private ArrayList<String> images = new ArrayList<String>(Arrays.asList("pause.png", "play.png", "slow.png","fast.png", "replay.png", "save.png"));
	private ArrayList<Button> buttons =new ArrayList<Button>(Arrays.asList(pause, play, slowControl, speedControl, replay, save));
   // private ArrayList<?> methods=new ArrayList<?>(Arrays.asList(handlePause(), handlePlay(), handleSpeed(), handleReplay(),handleSlow()));
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
			button.setOnAction(click -> {
				playerView.handleUI();
			});
			buttonList.add(button);

			}
		return buttonList;

		}
		
	}
	
//	protected Button pausePlayButton() {
//	
//		ImageView pauseImageView = new ImageView( getClass().getResource( "/game_player_resources/pause.png").toExternalForm());
//        ImageView playImageView = new ImageView( getClass().getResource( "/game_player_resources/play.png").toExternalForm());
//
//		pauseImageView.setFitHeight(30);
//		pauseImageView.setFitWidth(30);
//		playImageView.setFitHeight(30);
//		playImageView.setFitWidth(30);
//        HBox hBox = new HBox();
//        hBox.getChildren().addAll(playImageView,pauseImageView);
//
//		pausePlay = new Button("", hBox);
//		pausePlay.getStyleClass().add("button-nav");
//		pausePlay.setOnAction(click -> {
//			playerView.handleUI();
//		});
//		return pausePlay;
//		
//	}
//	
//	protected Button speedControlButton() {
//		ImageView speedImageView = new ImageView( getClass().getResource( "/game_player_resources/fast.png").toExternalForm());
//
//		speedImageView.setFitHeight(30);
//		speedImageView.setFitWidth(30);
//		speedControl = new Button("", speedImageView);
//		speedControl.getStyleClass().add("button-nav");
//		speedControl.setOnAction(click -> {
//			playerView.handleUI();
//		});
//		return speedControl;
//		
//	}
//	protected Button slowControlButton() {
//		ImageView slowImageView = new ImageView( getClass().getResource( "/game_player_resources/slow.png").toExternalForm());
//
//		slowImageView.setFitHeight(30);
//		slowImageView.setFitWidth(30);
//		slowControl = new Button("", slowImageView);
//		slowControl.getStyleClass().add("button-nav");
//		slowControl.setOnAction(click -> {
//			playerView.handleUI();
//		});
//		return slowControl;
//		
//	}
//	
//	protected Button replayButton() {
//		ImageView replayImageView = new ImageView( getClass().getResource( "/game_player_resources/replay.png").toExternalForm());
//		replayImageView.setFitHeight(30);
//		replayImageView.setFitWidth(30);
//		replay = new Button("", replayImageView);
//		replay.getStyleClass().add("button-nav");
//		replay.setOnAction(click -> {
//			playerView.handleUI();
//		});
//		return replay;
//		
//	}
//	protected Button saveButton() {
//		ImageView saveImageView = new ImageView( getClass().getResource( "/game_player_resources/save.png").toExternalForm());
//		saveImageView.setFitHeight(30);
//		saveImageView.setFitWidth(30);
//		save = new Button("", saveImageView);
//		save.getStyleClass().add("button-nav");
//		save.setOnAction(click -> {
//			playerView.handleUI();
//		});
//		return save;
//		
//	}
//	
//	
//}
