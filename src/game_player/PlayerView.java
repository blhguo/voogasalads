package game_player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.Vector;
import game_engine.components.HealthComponent;
import game_engine.components.PrimeComponent;
import game_engine.components.collect.ScoreComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.SpritePolarityComponent;
import game_engine.components.sprite.VisibilityComponent;
import game_engine.components.sprite.WidthComponent;
import game_engine.components.sprite.ZHeightComponent;
import game_engine.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Dana Park, Brandon Dalla Rosa
 * 
 *         Class that handles animations and updating of animations in game. Holds the game scene
 *         which is visible to the user.
 *
 */
public class PlayerView {

	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final double DOUBLE_RATE = 1.05;
	private static final double HALF_RATE = 0.93;
	private static final int[] SCORE_POS = { 500, 40 };
	private static final int[] HEALTH_POS = { 500, 15 };

	private Timeline animation;
	private DataConnect dataConnect;
	private Engine myEngine;
	private Map<Entity, Map<String, ImageView>> spriteMap;
	private Group root;
	private ViewManager viewManager;
	private SubScene subScene;
	private ParallelCamera cam;
	private DataManager dataManager;
	private boolean notSet;
	private double scoreData = 0;
	private double healthData = 0;
	private double camInX;
	private double camInY;

	private Entity primary;

	/**
	 * @param pdf
	 * @param engine
	 * @param view
	 * 
	 *        Constructor for PlayerView prior to initialization.
	 *
	 */
	public PlayerView() {
		// TODO something
	}

	/**
	 * Method to initialize the class after creation.
	 * 
	 * @param storage
	 */
	public void initialize(InstanceStorage storage) {
		dataConnect = storage.getDataConnect();
		viewManager = storage.getViewManager();
		dataManager = storage.getDataManager();
		notSet = true;
	}

	/**
	 * Method called to set the current engine of the game level.
	 * 
	 * @param e
	 */
	public void setEngine(Engine e) {
		this.myEngine = e;
	}

	/**
	 * method that instantiates the scene with the camera for the game with all necessary sprites.
	 *
	 */
	public void instantiate() {
		Scene scene = viewManager.getScene();
		subScene = viewManager.getSubScene();
		root = viewManager.getSubRoot();
		scene.setOnKeyReleased(event -> myEngine.receiveKeyInput(event));
		scene.setOnKeyPressed(event -> myEngine.receiveKeyInput(event));
		cam = new ParallelCamera();
		subScene.setCamera(cam);
		camInX = cam.getLayoutX();
		camInY = cam.getLayoutY();
		Level level = myEngine.getLevel();

		spriteMap = new HashMap<>();
		List<Entity> spriteEntities = level.getEntitiesContaining(
				Arrays.asList(FilenameComponent.class, HeightComponent.class, WidthComponent.class));
		for (Entity e : spriteEntities) {
			getImageView(e);
		}

		animationFrame();
	}

	/**
	 * @param vm Method that sets viewManager as the parameter.
	 */
	public void setViewManager(ViewManager vm) {
		viewManager = vm;
	}

	/**
	 * Sets up the animation for the scene.
	 */
	private void animationFrame() {
		if(animation != null){
			animation.stop();
		}
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/**
	 * Called once per frame to update the scene.
	 * 
	 * @param delay
	 */
	private void step(double delay) {
		primary = myEngine.getLevel().getEntitiesContaining(Arrays.asList(PrimeComponent.class)).get(0);
		setGamePlayerOnce();
		myEngine.update(delay);
		render();
		double x = cam.getLayoutX();
		double y = cam.getLayoutY();
		if(primary.getComponent(ScoreComponent.class)!=null) {
			scoreData = primary.getComponent(ScoreComponent.class).getValue();
			viewManager.createText(x + SCORE_POS[0] - camInX, y + SCORE_POS[1] - camInY, "Score: " + scoreData);
		}
		if(primary.getComponent(HealthComponent.class)!=null) {
			healthData = primary.getComponent(HealthComponent.class).getValue();
			viewManager.createText(x + HEALTH_POS[0] - camInX, y + HEALTH_POS[1] - camInY, "Health: " + healthData);
		}
		//viewManager.changeBackground();
	}

	/**
	 * Method called to render the correct sprites onto the scene from the engine.
	 */
	private void render() {
		root.getChildren().clear();

		Double xPos = primary.getComponent(XPosComponent.class).getValue();
		Double yPos = primary.getComponent(YPosComponent.class).getValue();
		cam.relocate(xPos - ViewManager.SUBSCENE_WIDTH / 2, yPos - ViewManager.SUBSCENE_HEIGHT / 2);

		myEngine.getLevel().getEntities().stream().sorted(this::compareZ).forEach(this::display);
	}

	/**
	 * Compares the layer of the entities for display.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private int compareZ(Entity a, Entity b) {
		Component<Double> zCompA = a.getComponent(ZHeightComponent.class);
		Component<Double> zCompB = b.getComponent(ZHeightComponent.class);
		Double zHeightA = (zCompA == null) ? 0.0 : zCompA.getValue();
		Double zHeightB = (zCompB == null) ? 0.0 : zCompB.getValue();
		return zHeightA.compareTo(zHeightB);
	}

	/**
	 * Used to provide the game player entity to the data manager.
	 */
	private void setGamePlayerOnce() {
		if (notSet) {
			notSet = false;
			dataManager.setGamePlayer(primary);
		}
	}

	/**
	 * Method to recieve the mouse input from the scene.
	 * 
	 * @param imageView
	 */
	private void clickInput(ImageView imageView) {
		double middleX = imageView.getX() + imageView.getFitWidth() / 2;
		double middleY = imageView.getY() + imageView.getFitHeight() / 2;
		Vector click = new Vector(middleX, middleY);
		myEngine.receiveMouseInput(click);
	}

	/**
	 * Method called to obtain the imageviews for the scene.
	 * 
	 * @param entity
	 * @return
	 */
	private ImageView getImageView(Entity entity) {
		String filename = entity.getComponent(FilenameComponent.class).getValue();
		if (!spriteMap.containsKey(entity)) {
			Map<String, ImageView> imageMap = new HashMap<>();
			spriteMap.put(entity, imageMap);
		}

		if (!spriteMap.get(entity).containsKey(filename)) {
			ImageView imageView = new ImageView(filename);
			imageView.setOnMousePressed(event -> clickInput(imageView));
			spriteMap.get(entity).put(filename, imageView);
		}

		ImageView imageView = spriteMap.get(entity).get(filename);
		return imageView;
	}

	/**
	 * Method called to display the image of an entity correctly.
	 * 
	 * @param entity
	 */
	private void display(Entity entity) {
		Double xPos = entity.getComponent(XPosComponent.class).getValue();
		Double yPos = entity.getComponent(YPosComponent.class).getValue();
		Double width = entity.getComponent(WidthComponent.class).getValue();
		Double height = entity.getComponent(HeightComponent.class).getValue();
		Boolean visibility = entity.getComponent(VisibilityComponent.class).getValue();

		ImageView imageView = getImageView(entity);
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);
		imageView.setX(xPos - width / 2);
		imageView.setY(yPos - height / 2);
		imageView.setVisible(visibility);

		Component<Double> polarity = entity.getComponent(SpritePolarityComponent.class);
		// changes which direction the imageview faces based off of movement direction of entity
		if (polarity != null) {
			imageView.setScaleX(Math.signum(polarity.getValue()));
		}
		if (! root.getChildren().contains(imageView))
			root.getChildren().add(imageView);
	}

	/**
	 * Method that handles reactions when buttons are pressed on Menu. Ex: When Play button is pressed,
	 * the method will make the game play
	 *
	 */
	public void handleUI(int index) {
		// TODO Refactor and improve this code

		if (index == 0) {
			animation.stop();
		} else if (index == 1) {
			animation.play();
		} else if (index == 2) {
			animation.setRate(animation.getRate() * HALF_RATE);
		} else if (index == 3) {
			animation.setRate(animation.getRate() * DOUBLE_RATE);
		} else if (index == 4) {
			dataConnect.handleReplay();
		} else if (index == 5) {
			dataConnect.handleSave();
		} else if (index == 6) {
			dataConnect.aboutGame();
		}
	}

}
