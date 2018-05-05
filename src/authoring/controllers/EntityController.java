package authoring.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.Canvas;
import authoring.component_menus.ComponentMenu;
import authoring.right_components.EventPane;
import authoring.right_components.EntityComponent.EntityPane;
import authoring.right_components.EntityComponent.EntityWrapper;
import frontend_utilities.ButtonFactory;
import frontend_utilities.DraggableImageView;
import frontend_utilities.ImageBuilder;
import game_engine.Entity;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.WidthComponent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
/**
 * @author liampulsifer
 * manages interaction between EntityPane and Canvas
 * maintains a map of ImageView to Entity and defines on-click behavior of ImageViews
 */
public class EntityController {
	Map<ImageView, Entity> map;
	List<EntityWrapper> entityList;
	Map<Entity, List<ComponentMenu>> menuMap;
	private EntityPane entityPane;
	private Canvas canvas;
	private LevelController lcontroller;
	private Button button;
	private EventPane eventPane;

	
	public EntityController(EntityPane pane, Canvas c, EventPane eventPane){
		entityPane = pane;
		this.eventPane = eventPane;
		canvas = c;
		map = new HashMap<>();
		entityList = new ArrayList<>();
		menuMap = new HashMap<>();
		button = ButtonFactory.makeButton(e -> removeEntity());
	}

	/**
	 * Adds the passed Entity to the local map of imageviews to entities
	 * calls setUpImageView to make the imageview of the entity
	 * adds the entity to the levelController
	 * @param wrapper the entityWrapper to be added to the map
	 */
	public void add(EntityWrapper wrapper){
		//entityList.add(entity);
		map.put(wrapper.getImageView(), wrapper.getEntity());
		if (!entityList.contains(wrapper)) {
			entityList.add(wrapper);
		}
		//System.out.println("Wrapper Added: " + wrapper);
		//System.out.println("\tWrapper's Entity: " + wrapper.getEntity());
		//System.out.println("Wrapper added: " + entityList.size());
		//menuMap.put(entity, new ArrayList<>(entityPane.getMenuList().
			//stream().filter(e -> e.isIncluded()).collect(Collectors.toList())));
		//iv.setClick(entityPane.showMenu(entity.getMenu()));
		//canvas.update(entityList);
		addToLevel(wrapper.getEntity());
		int currLevel = lcontroller.getEngine().getLevel().getId();
		wrapper.setLevel(currLevel);
		//System.out.println("Entity Components at Add");
		//wrapper.getEntity().getComponents().stream().forEach((b -> System.out.println(b)));
	}

	/**
	 * Sets up an imageview which is draggable and has features to set
	 * new entity positions for its entity
	 * @param entity
	 * @return ImageView
	 */
	private ImageView setUpImageView(Entity entity){
		DraggableImageView iv = ImageBuilder.getDraggableImageView(entity.getComponent(FilenameComponent.class).getValue(),
				entity.getComponent(WidthComponent.class).getValue().intValue(),
				entity.getComponent(HeightComponent.class).getValue().intValue());
		iv.setX(entity.getComponent(XPosComponent.class).getValue());
		iv.setY(entity.getComponent(YPosComponent.class).getValue());
		iv.setOnMouseDragReleased(e -> setPos(iv.getX(), iv.getY(), entity, iv));
		return iv;

	}

	/**
	 * Adds the given entity to the level controller's active level
	 * @param entity
	 */
	private void addToLevel(Entity entity) {
		lcontroller.addEntity(entity);
	}
	/**
	 *
	 * @return a button which removes the currently selected entity
	 *          (Associated with the ImageView selected)
	 */
	public Button getRemoveButton(){
		return button;
	}

	/**
	 *  Removes this entity and imageview from the map and updates the canvas to show the deleted entity
	 */
	public void removeEntity(){
		entityList.remove(entityPane.getPureCurrent());
		lcontroller.getEngine().getLevel().removeEntity(entityPane.getPureCurrent().getEntity());
		canvas.update(entityList);
		entityPane.newWrapper();
	}

	/**
	 * Sets the position component of an entity to be that of its imageview, and updates the map
	 * @param x -- ImageView x
	 * @param y -- ImageView y
	 * @param ent
	 * @param iv
	 */
	public void setPos(double x, double y, Entity ent, ImageView iv){
		ent.getComponent(XPosComponent.class).setValue(x);
		ent.getComponent(YPosComponent.class).setValue(y);

	}

	/**
	 *
	 * @return the default sprite for display at the top of the EntityPane
	 */
	public ImageView getSprite(){
//		ImageView iv = ImageBuilder.getImageView(entityPane.getEntity().getComponent(FilenameComponent.class)
//				.getValue(), 135, 135);
//		return iv;
		return new ImageView();
	}
	@Deprecated
	public Map<Entity, List<ComponentMenu>> getMenuMap(){
		return menuMap;
	}
	/**
	 *
	 */
	public List<ComponentMenu> getMenuComponents(Entity entity){
		return menuMap.get(entity);
	}
	/**
	 *
	 * @return ImageView to Entity map
	 */
	public Map<ImageView, Entity> getMap(){
		return map;
	}

	/**
	 *
	 * @return a button for new entity creation, for use in EntityPane
	 */
	public Button getButton(){
		return ButtonFactory.makeButton(e -> newEntity());
	}

	/**
	 * Creates a new Entity and updates the canvas to display it
	 */
	private void newEntity(){
		this.add(entityPane.getCurrent());
		canvas.update(entityList);
		entityPane.newWrapper();
		
	}

	/**
	 * Adds a levelController for passing Entities to Data
	 * @param lc
	 */
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}


	/**
	 * Makes all imageviews set to their default style
	 */
	public void resetImageViews(){
		entityList.stream().forEach(e -> {
			e.getImageView().setEffect(null);
		});
	}

	public void updateDummies(){
		canvas.updateDummies(entityList);
	}
	public void listenCanvas() {
		canvas.listen();
	}
	public void stopListenCanvas(){
		canvas.stopListen();
	}

	public void alertEntityPane(double sceneX, double sceneY) {
		EntityWrapper wrap = new EntityWrapper(entityPane.getPureCurrent(), entityPane);
		wrap.setPos(sceneX - wrap.getImageView().getFitWidth() / 2,
				sceneY - wrap.getImageView().getFitHeight() / 2);
		if (!entityList.contains(wrap)){
			add(wrap);
		}
		canvas.update(entityList);
		//System.out.println("# of Entities " + entityList.size());
		//entityPane.newWrapper();
		//System.out.println("About to hit it");
		entityPane.newDuplicateEntity();
		//entityPane.refresh();
		this.resetImageViews();
		//entityPane.setActiveWrapper(wrap);
	}

	public void updateCanvas() {
		canvas.update(entityList);
	}
	
	public void updateCanvas(List<EntityWrapper> entList){
		canvas.update(entList);
	}

	public void addToEventPaneBox(EntityWrapper e) {
		eventPane.addToEntityBox(e);
	}
	
	public List<EntityWrapper> getEntities(){
		return entityList;
	}
}
