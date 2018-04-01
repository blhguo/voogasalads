package gameplayer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class ViewManager {
	
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Stage stg;
	private Scene myScene;
	private Group root;
	private Timeline animation;

	
	
	public ViewManager() {
		// TODO Auto-generated constructor stub
	}
	
	public void start(Stage stage) {
		
	}
	
	private Scene setUpGame() {
		return null;
		
	}
	
	private void animationFrame() {
		
			KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
					e -> step(SECOND_DELAY));
			if(animation!=null) {
				animation.stop();
			}
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
		}
	
	public void step(double delay) {
		
	}

}
