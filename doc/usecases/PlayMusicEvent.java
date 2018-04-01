package voogasalad_callussalad;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 * 
 * @author GameEngine
 * The user selects a sound file to play when an event is triggered
 */
public class PlayMusicEvent implements Event{
	private String fileName;
	private State state;
	public PlayMusicEvent(String fileName, State state){
		this.fileName = fileName;
		this.state = state;
	}
	private void execute(){
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.fileName).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	@Override
	public void listen(){
		boolean status = this.state.isReady();
		if(status){
			this.execute();
		}
	}
}
