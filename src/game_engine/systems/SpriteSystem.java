package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Input;
import game_engine.Vector;
import game_engine.components.KeyboardMovementInput;
import game_engine.components.Physics;
import game_engine.components.Sprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SpriteSystem extends GameSystem{
	
	private static final Class<? extends Component> SPRITE = Sprite.class;

	
	public SpriteSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(SPRITE);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			Sprite sprite = (Sprite) entity.getComponent(SPRITE);
			sprite.show();
		}
		
	}

	
	
}
