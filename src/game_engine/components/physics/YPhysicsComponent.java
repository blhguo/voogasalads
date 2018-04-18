package game_engine.components.physics;

import java.util.List;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * Stores the velocity and acceleration for the Y direction
 */
public class YPhysicsComponent extends PhysicsComponent {

	public YPhysicsComponent(List<String> args) {
		super(args);
	}

	@Override
	public String getValues() {
		String vals = "DefaultYVelocity,d," + this.getDefaultVel() + ";Acceleration,d," + this.getAccel();
		return vals;
	}

	@Override
	public String getName() {
		return "YPhysics";
	}
}
