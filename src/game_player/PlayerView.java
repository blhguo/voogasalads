package game_player;

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
	private static final double DOUBLE_RATE = 2.0;
	private static final double HALF_RATE = 0.5;
	private PulldownFactory pullDownFactory;
	
	public PlayerView(PulldownFactory pdf) {
		pullDownFactory = pdf;
	}

	
	
	private void animationFrame() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private void step(double delay) {
		//engine.update();
		updateGame();
		handleUI();
	}
	
	//testing code
	private void handleUI() {
		String selectedAction = pullDownFactory.SpeedBox().getSelectionModel().getSelectedItem();
		if (selectedAction.equals("Speed Up")) {
			animation.setRate(animation.getRate() * DOUBLE_RATE);
		}
		if (selectedAction.equals("Slow Down")) {
			animation.setRate(animation.getRate() * HALF_RATE);
		}
		if (selectedAction.equals("Pause")) {
			animation.stop();
		}
		if (selectedAction.equals("Play")) {
			animation.play();
		}
	}
	
	private void updateGame() {
		
	}

}
