package authoring.controllers;

import authoring.Canvas;
import authoring.right_components.EntityComponent.EntityPane;
import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import game_engine.Entity;
import game_engine.components.PositionComponent;
import game_engine.components.SpriteComponent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javax.swing.text.Position;
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
	}
	public void add(Entity entity){
		SpriteComponent comp = (SpriteComponent) entity.getComponent(SpriteComponent.class);
		PositionComponent pos = (PositionComponent) entity.getComponent(PositionComponent.class);
		ImageView iv = ImageBuilder.getImageView(comp.getFileName(), (int) comp.getWidth(), (int) comp.getHeight());
		iv.setX(pos.getX());
		iv.setY(pos.getY());
		map.put(iv, entity);
		//iv.setClick(entityPane.showMenu(entity.getMenu()));
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
