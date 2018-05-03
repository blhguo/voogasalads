package game_engine.event.actions.macro;

import java.net.URL;

import game_engine.event.Action;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * 
 * @author Andy Nguyen, Ben Hubsch, Jeremy Chen The purpose of this class is to allow diagetic and non-diagetic sounds in game to be played.
 *         This action plays a music file for a particular duration of time. This action is
 *         defined/instantiated within the authoring environment.
 *
 */
public class PlayMusicAction implements Action {
	private String myMusicFile;
	private double myDuration;
	private MediaPlayer myMediaPlayer;

	/**
	 * initializes a PlayMusicAction with a music file under the assumption that the entire music file
	 * will be played
	 * 
	 * @param musicFile
	 */
	public PlayMusicAction(String musicFile) {
		this(musicFile, -1);
	}

	/**
	 * initializes a PlayMusicAction with a music file with the duration of time the musicFile will be
	 * played for
	 * 
	 * @param musicFile
	 * @param duration
	 */
	public PlayMusicAction(String musicFile, double duration) {
		myMusicFile = musicFile;
		myDuration = duration;
	}

	private Media loadMedia(){
		URL musicPath;
		if(myMusicFile.charAt(0) != '/'){
			musicPath = getClass().getResource("/" + myMusicFile);
		}
		else{
			musicPath = getClass().getResource(myMusicFile);
		}
		return new Media(musicPath.toExternalForm());
	}

	/**
	 * plays the music file for a given duration of time specified by the initial conditions set in the
	 * constructor
	 */
	@Override
	public void execute() {
		myMediaPlayer = new MediaPlayer(loadMedia());
		if(myDuration >= 0){
			myMediaPlayer.setStopTime(new Duration(myDuration * 1000.0));
		}
		myMediaPlayer.play();
	}

}
