package authoring.controllers;

import authoring.Canvas;
import authoring.right_components.EntityComponent.EntityPane;
import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import game_engine.Entity;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class EntityController {
	Map<ImageView, Entity> map;
	private EntityPane entityPane;
	private Canvas canvas;
	private ImageView view;
	public EntityController(EntityPane pane, Canvas c){
		entityPane = pane;
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
		this.add(entityPane.getEntity());
		canvas.update(map);
	}

}
