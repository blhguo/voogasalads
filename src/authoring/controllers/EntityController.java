package authoring.controllers;

import authoring.Canvas;
import authoring.component_menus.ComponentMenu;
import authoring.right_components.EntityComponent.EntityPane;
import authoring.utilities.ButtonFactory;
import authoring.utilities.DraggableImageView;
import authoring.utilities.ImageBuilder;
import game_engine.Entity;
import game_engine.components.PositionComponent;
import game_engine.components.SpriteComponent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityController {
	Map<ImageView, Entity> map;
	List<Entity> entityList;
	Map<Entity, List<ComponentMenu>> menuMap;
	private EntityPane entityPane;
	private Canvas canvas;
	private ImageView view;
	private LevelController lcontroller;
	
	public EntityController(EntityPane pane, Canvas c){
		entityPane = pane;
		canvas = c;
		map = new HashMap<>();
		entityList = new ArrayList<>();
		menuMap = new HashMap<>();
	}
	public void add(Entity entity){
		SpriteComponent comp = (SpriteComponent) entity.getComponent(SpriteComponent.class);
		PositionComponent pos = (PositionComponent) entity.getComponent(PositionComponent.class);
		DraggableImageView iv = ImageBuilder.getDraggableImageView(comp.getFileName(), (int) comp.getWidth(), (int) comp.getHeight());
		iv.setX(pos.getX());
		iv.setY(pos.getY());
		System.out.println("New Enitity imageview xPos:" + pos.getX());
		System.out.println("New Enitity imageview yPos:" + pos.getY());

		map.put(iv, entity);
		iv.setOnMouseClicked(e -> UpdateMenus(iv));
		iv.setOnMouseReleased(e -> setPos(iv.getX(), iv.getY(), pos, entity, iv));
		entityList.add(entity);
		menuMap.put(entity, entityPane.getMenuList());
		System.out.println("Number of Entities: " + map.keySet().size());
		//iv.setClick(entityPane.showMenu(entity.getMenu()));

		lcontroller.getActiveLevel().addEntity(entity);
	}
	public void setPos(double x, double y, PositionComponent pos, Entity ent, ImageView iv){
		pos.setX(x);
		pos.setY(y);
		UpdateMenus(iv);

	}
	public ImageView getSprite(){
		SpriteComponent comp = (SpriteComponent) entityPane.getEntity().getComponent(SpriteComponent.class);
		ImageView iv = ImageBuilder.getImageView(comp.getFileName(), 200, 200);
		return iv;
	}
	public Map<Entity, List<ComponentMenu>> getMenuMap(){
		return menuMap;
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
	public void UpdateMenus(ImageView iv){
		toggleStyle(iv);
		entityPane.updateMenus(map.get(iv));
	}
	
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}

	private void toggleStyle(ImageView iv) {
		resetImageViews();
		iv.setStyle("-fx-opacity: .5;");
	}
	public void resetImageViews(){
		map.keySet().stream().forEach(image -> image.setStyle(""));
	}

}
