package test;

import java.util.List;

import game_engine.Entity;

public class CollidedComponent extends Component<List<Entity>>{
	public CollidedComponent(List<Entity> val) {
		super(val);
	}
}
