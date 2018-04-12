package game_engine.components.physics;

import java.util.List;

public class XPhysicsComponent extends PhysicsComponent {
	
	public XPhysicsComponent(List<String> args) {
		super(args);
	}

	@Override
	public String getValues() {
		String vals = "DefaultXVelocity,d," + this.getDefaultVel() + ";Acceleration,d," + this.getAccel();
		return vals;
	}

	@Override
	public String getName() {
		return "XPhysics";
	}
}
