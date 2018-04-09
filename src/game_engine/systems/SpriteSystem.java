package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.SpriteComponent;


public class SpriteSystem extends GameSystem{
	
	private static final Class<? extends Component> SPRITE = SpriteComponent.class;

	
	public SpriteSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(SPRITE);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			SpriteComponent sprite = (SpriteComponent) entity.getComponent(SPRITE);
			sprite.show();
		}
		
	}

	
	
}
