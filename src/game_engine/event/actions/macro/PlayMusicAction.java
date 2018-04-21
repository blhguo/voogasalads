package game_engine.event.actions.macro;

import java.io.File;

import game_engine.event.Action;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class PlayMusicAction implements Action{
	private String myMusicFile;
	private double myDuration;
	
	public PlayMusicAction(String musicFile){
		this(musicFile, -1);
	}
	
	public PlayMusicAction(String musicFile, double duration){
		myMusicFile = musicFile;
		myDuration = duration;
	}
	
	@Override
	public void execute() {
		 Media media = new Media(new File(myMusicFile).toURI().toString());
		 MediaPlayer mediaPlayer = new MediaPlayer(media);
		 mediaPlayer.setStartTime(Duration.ZERO);
		 if(myDuration == -1){
			 mediaPlayer.setStopTime(media.durationProperty().get());
		 }else{
			 mediaPlayer.setStopTime(new Duration(myDuration));
		 }
		 mediaPlayer.play();
	}
	
}
