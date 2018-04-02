package gameplayer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * 
 * @author Dana Park
 *
 */
public class PlayerView {
	
	private Timeline animation;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private PulldownFactory pullDownFactory = new PulldownFactory();

	
	
	private void animationFrame() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void step(double delay) {
		updateGame();
		handleUI();
		
		
	}
	
	//testing code
	private void handleUI() {
		String selectedAction = pullDownFactory.SpeedBox().getSelectionModel().getSelectedItem();
		if (selectedAction.equals("Speed Up")) {
		}
		
	}
	
	private void updateGame() {
		
	}

}
