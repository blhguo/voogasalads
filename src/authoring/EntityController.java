package authoring;

import authoring.right_components.EntityComponent.EntityComponent;
import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import game_engine.Entity;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class EntityController {
	Map<ImageView, Entity> map;
	private EntityComponent entityComponent;
	private Canvas canvas;
	private ImageView view;
	public EntityController(EntityComponent component, Canvas c){
		entityComponent = component;
		canvas = c;
		map = new HashMap<>();
		view = ImageBuilder.getImageView("jen.png", 200,200);
		view.setX(view.getX() + 200);
		view.setY(view.getY() + 400);
		map.put(view, new Entity());
	}
	public void add(Entity entity){
		map.put(view, entity);
	}
	public Map<ImageView, Entity> getMap(){
		return map;
	}
	public Button getButton(){
		return ButtonFactory.makeButton(e -> newEntity());
	}
	private void newEntity(){
		this.add(entityComponent.getEntity());
		canvas.update(map);
	}

}
