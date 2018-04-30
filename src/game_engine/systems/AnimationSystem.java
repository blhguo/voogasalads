package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.JumpFilenameComponent;
import game_engine.components.sprite.RunFilenameComponent;
import game_engine.components.sprite.StandFilenameComponent;
import game_engine.level.Level;

public class AnimationSystem implements GameSystem {
	
	private static final Class<? extends Component<String>> DISPLAYED = FilenameComponent.class;
	private static final Class<? extends Component<String>> RUN = RunFilenameComponent.class;
	private static final Class<? extends Component<String>> JUMP = JumpFilenameComponent.class;
	private static final Class<? extends Component<String>> STAND = StandFilenameComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	private static final Class<? extends Component<Double>> Y_VEL = YVelComponent.class;
	
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(DISPLAYED, RUN, JUMP, STAND, X_VEL, Y_VEL);
		for (Entity entity : level.getEntitiesContaining(args)) {
			Component<String> displayed = entity.getComponent(DISPLAYED);
			Component<String> run = entity.getComponent(RUN);
			Component<String> jump = entity.getComponent(JUMP);
			Component<String> stand = entity.getComponent(STAND);
			Component<Double> xVel = entity.getComponent(X_VEL);
			Component<Double> yVel = entity.getComponent(Y_VEL);
			
			if (yVel.getValue().intValue() > 0) {
				displayed.setValue(jump.getValue());
				break;
			}
			
			if (Math.abs(xVel.getValue().intValue()) > 0) {
				displayed.setValue(run.getValue());
			} else {
				displayed.setValue(stand.getValue());
			}
		}
	}
}
