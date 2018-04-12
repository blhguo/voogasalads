package game_engine.components.physics;

import java.util.List;

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
