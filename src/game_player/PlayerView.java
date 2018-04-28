package game_player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.PrimeComponent;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.SpritePolarityComponent;
import game_engine.components.sprite.WidthComponent;
import game_engine.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * 
 * @author Dana Park Class that handles animations and updating of animations in game
 *
 */
public class PlayerView {

	private Timeline animation;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final double DOUBLE_RATE = 1.05;
	private static final double HALF_RATE = 0.93;

	private PulldownFactory pullDownFactory;
	private Engine myEngine;
	private Map<String, ImageView> spriteMap;
	private Group root;
	private ViewManager viewManager;
	private SubScene subScene;
	private ParallelCamera cam;
	private DataManager dataManager;
	private Entity gamePlayer;
	private boolean notSet;


	/**
	 * @param pdf
	 * @param engine
	 * @param view constructor for PlayerView
	 *
	 */
	public PlayerView(PulldownFactory pdf, ViewManager view, DataManager dtm) {
		pullDownFactory = pdf;
		viewManager = view;
		dataManager = dtm;
		notSet = true;
	}

	public void setEngine(Engine e) {
		this.myEngine = e;
	}

	/**
	 * method that instantiates the scene with the camera for the game with all necessary sprites
	 *
	 */
	public void instantiate() {
		Scene scene = viewManager.getScene();
		subScene = viewManager.getSubScene();
		root = viewManager.getSubRoot();
		scene.setOnKeyPressed(e -> {
			myEngine.receiveInput(e);
		});
		scene.setOnKeyReleased(e -> myEngine.receiveInput(e));
		subScene.setOnKeyPressed(e -> myEngine.receiveInput(e));
		subScene.setOnKeyReleased(e -> myEngine.receiveInput(e));
		cam = new ParallelCamera();
		subScene.setCamera(cam);
		Level level = myEngine.getLevel();

		spriteMap = new HashMap<>();
		List<Entity> spriteEntities = level.getEntitiesContaining(
				Arrays.asList(FilenameComponent.class, HeightComponent.class, WidthComponent.class));
		for (Entity e : spriteEntities) {
			String imageName = e.getComponent(FilenameComponent.class).getValue();
			Double height = e.getComponent(HeightComponent.class).getValue();
			Double width = e.getComponent(WidthComponent.class).getValue();
			Image image = new Image(imageName);
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(width);
			imageView.setFitHeight(height);
			spriteMap.put(imageName, imageView);
			root.getChildren().add(imageView);
		}

		animationFrame();
	}

	/**
	 * @param vm method that sets viewManager as the param
	 */
	public void setViewManager(ViewManager vm) {
		viewManager = vm;
	}

	private void animationFrame() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step(double delay) {
		//animation.stop();
		myEngine.update(delay);
		render();
		handleUI();
	}

	private void render() {
		root.getChildren().clear();
		myEngine.getLevel().getEntities().stream().filter(this::isInView).forEach(this::display);
		//gamePlayer = myEngine.getLevel().getEntitiesContaining(Arrays.asList(PrimeComponent.class)).get(0);
		gamePlayer = myEngine.getLevel().getEntitiesContaining(Arrays.asList(LeftKeyboardComponent.class)).get(0);
		setGamePlayerOnce();
		Double xPos = gamePlayer.getComponent(XPosComponent.class).getValue();
		Double yPos = gamePlayer.getComponent(YPosComponent.class).getValue();
		cam.relocate(xPos - SCENE_SIZE / 2, yPos - SCENE_SIZE / 2);
	}
	
	private void setGamePlayerOnce() {
		if(notSet) {
			notSet = false;
			dataManager.setGamePlayer(gamePlayer);
		}
	}

	private ImageView getImageView(Entity entity) {
		String filename = entity.getComponent(FilenameComponent.class).getValue();
		if (!spriteMap.containsKey(filename)) {
			spriteMap.put(filename, new ImageView(filename));
		}
		return spriteMap.get(filename);
	}

	private void display(Entity entity) {
		//System.out.println("entity: " + entity);
		Double xPos = entity.getComponent(XPosComponent.class).getValue();
		Double yPos = entity.getComponent(YPosComponent.class).getValue();
		Double width = entity.getComponent(WidthComponent.class).getValue();
		Double height = entity.getComponent(HeightComponent.class).getValue();

		ImageView imageView = getImageView(entity);
		imageView.setX(xPos - width / 2);
		imageView.setY(yPos - height / 2);

		Component<Integer> polarity = entity.getComponent(SpritePolarityComponent.class);
		// changes which direction the imageview faces based off of movement direction of entity
		if (polarity != null) {
			imageView.setScaleX(Math.signum(polarity.getValue()));
		}
		root.getChildren().add(imageView);
	}

	private boolean isInView(Entity entity) {
//		Double xPos = entity.getComponent(XPosComponent.class).getValue();
//		Double yPos = entity.getComponent(YPosComponent.class).getValue();
//		
//		Double xMin = cam.getLayoutX() - SCENE_SIZE / 2;
//		Double xMax = cam.getLayoutX() + SCENE_SIZE / 2;
//		Double yMin = cam.getLayoutY() - SCENE_SIZE / 2;
//		Double yMax = cam.getLayoutY() + SCENE_SIZE / 2;
//		return ((xMin <= xPos && xMax >= xPos ) && (yMin <= yPos && yMax >= yPos));
		return true;
	}
	

	private boolean checkCorner(double entityX, double entityY, double centerX, double centerY) {
		double sceneMinX = centerX - ViewManager.SUBSCENE_WIDTH / 2;
		double sceneMaxX = centerX + ViewManager.SUBSCENE_WIDTH / 2;
		double sceneMinY = centerY - ViewManager.SUBSCENE_HEIGHT / 2;
		double sceneMaxY = centerY + ViewManager.SUBSCENE_HEIGHT / 2;	
		return ((sceneMinX <= entityX && entityX <= sceneMaxX) && (sceneMinY <= entityY && entityY <= sceneMaxY));
	}

	/**
	 * method that handles reactions when buttons are pressed on Menu. Ex: When Play button is pressed,
	 * the method will make the game play
	 *
	 */
	public void handleUI() {
		String selectedAction = pullDownFactory.getSpeedBox().getSelectionModel().getSelectedItem();
		String statusAction = pullDownFactory.getStatusBox().getSelectionModel().getSelectedItem();

		if (selectedAction.equals("Speed Up")) {

			animation.setRate(animation.getRate() * DOUBLE_RATE);
		}
		if (selectedAction.equals("Slow Down")) {
			animation.setRate(animation.getRate() * HALF_RATE);
		}
		if (statusAction.equals("Pause Game")) {
			animation.stop();
		}
		if (statusAction.equals("Play Game")) {
			animation.play();
		}
	}

}
