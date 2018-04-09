package game_engine.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.Vector;
import game_engine.components.CollidableComponent;
import game_engine.components.CollidedComponent;
import game_engine.components.HitboxComponent;
import game_engine.components.JumpComponent;
import game_engine.components.KeyboardJumpInputComponent;
import game_engine.components.KeyboardMovementInputComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;
import game_engine.components.SpriteComponent;
import game_engine.systems.CollisionBroadSystem;
import game_engine.systems.KeyboardJumpSystem;
import game_engine.systems.KeyboardMoveRightSystem;
import game_engine.systems.MovementSystem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JumpTest extends Application{
	private Entity entity;

    private Engine engine;

    private Rectangle rect;

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private static final String TITLE = "Collision Tester";
    private static final int WIDTH = 1600;
    private static final int HEIGHT= 800;
    private static final Paint BACKGROUND = Color.rgb(36, 36, 36);

    private Group root;
    private Scene myScene;

    
    private KeyboardJumpSystem keyboardJumpSys;
    private MovementSystem movementSys;

    @Override
    public void start(Stage stage) throws Exception {
        setup();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();

        // attach "game loop" to timeline to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    /**
     * @param elapsedTime
     */
    private void step(double elapsedTime) {
    	keyboardJumpSys.act(elapsedTime);
    	movementSys.act(elapsedTime);
        updateRectPos();
    }

    private void setup(){
        engine = new Engine();
        keyboardJumpSys = new KeyboardJumpSystem(engine);
        movementSys = new MovementSystem(engine);
        root = new Group();
        myScene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
        myScene.setOnKeyPressed(e -> engine.receiveInput(e));

        buildEntities();
        initRects();
    }

    private void updateRectPos(){
        PositionComponent pos = (PositionComponent) entity.getComponent(PositionComponent.class);
        double x = pos.getX();
        double y = pos.getY();
        rect.setX(x);
        rect.setY(y);
    }

    private void buildEntities(){


        ArrayList<String> phys =new ArrayList<String>();
        phys.add("0");
        phys.add("-500");
        phys.add("10");

        ArrayList<String> pos = new ArrayList<String>();
        pos.add("250");
        pos.add("250");
        pos.add("0");

        ArrayList<String> jump = new ArrayList<String>();
        jump.add("true");
        jump.add("2");
        
        ArrayList<String> jumpInput = new ArrayList<String>();
        jumpInput.add(KeyCode.UP.toString());

        entity = new Entity();
        entity.addComponent(new PhysicsComponent(phys));
        entity.addComponent(new PositionComponent(pos));
        entity.addComponent(new JumpComponent(jump));
        entity.addComponent(new KeyboardJumpInputComponent(jumpInput));

        engine.addEntity(entity);
    }

    private void initRects(){
        PositionComponent pos = (PositionComponent) entity.getComponent(PositionComponent.class);
        PhysicsComponent phys = (PhysicsComponent) entity.getComponent(PhysicsComponent.class);
        phys.setCurrYVel(0);
        rect = new Rectangle(pos.getX(), pos.getY(), 100, 100);
        root.getChildren().add(rect);
    }

    /**
     * Starts the program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


}
