package game_engine.test;
import java.util.ArrayList;
import java.util.List;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.JumpComponent;
import game_engine.components.PositionComponent;
import game_engine.components.collision.CollidableComponent;
import game_engine.components.collision.CollidedComponent;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.hitbox.HitboxComponent;
import game_engine.components.keyboard.KeyboardJumpInputComponent;
import game_engine.components.keyboard.KeyboardMovementInputComponent;
import game_engine.components.physics.XPhysicsComponent;
import game_engine.components.physics.YPhysicsComponent;
import game_engine.systems.InputGarbageCollectionSystem;
import game_engine.systems.MovementSystem;
import game_engine.systems.collision.CollisionBroadSystem;
import game_engine.systems.collision.CollisionResponseSystem;
import game_engine.systems.keyboard.KeyboardJumpSystem;
import game_engine.systems.keyboard.KeyboardMovementSystem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CollisionTest extends Application {

    private Entity e1;
    private Entity e2;
    private Entity e3;

    private Engine e;

    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
    private static final String GRAVITY = "-1000"; //effects of gravity
//    private static final String GRAVITY = "0";
	private static final String JUMP_VELOCITY = "500"; //effects of how high you jump

    private static final String TITLE = "Collision Tester";
    private static final int WIDTH = 1600;
    private static final int HEIGHT= 800;
    private static final Paint BACKGROUND = Color.rgb(36, 36, 36);

    private Group root;
    private Scene myScene;

    private CollisionBroadSystem colSys;
    private MovementSystem movementSys;
    private KeyboardJumpSystem keyboardJumpSys;
    private KeyboardMovementSystem keyboardMovementSys;
    private InputGarbageCollectionSystem inputGarbageCollectionSystem;
    private CollisionResponseSystem colResponseSys;

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
    	colSys.act(elapsedTime);
    	colResponseSys.act(elapsedTime);
		keyboardMovementSys.act(elapsedTime); //update position
		movementSys.act(elapsedTime); //update position
		
		keyboardJumpSys.act(elapsedTime); //update jump
		inputGarbageCollectionSystem.act(elapsedTime);
		
//		CollidedComponent temp = (BottomCollidedComponent) e1.getComponent(BottomCollidedComponent.class);
//		if(temp!=null) {
//			System.out.println("\n\n Bottom collided with " + temp.getEntities().get(0));
//		}
//		System.out.println(e1.getComponent(BottomCollidedComponent.class)!=null);

        updateRectPos();
        updateRectColor();
    }

    private void setup(){
        e = new Engine();
        colResponseSys = new CollisionResponseSystem(e);
        keyboardJumpSys = new KeyboardJumpSystem(e);
        keyboardMovementSys = new KeyboardMovementSystem(e);
        colSys = new CollisionBroadSystem(e);
        
        movementSys = new MovementSystem(e);
        
//        moveResponseSys = new MovementResponseSystem(e);
        inputGarbageCollectionSystem = new InputGarbageCollectionSystem(e);
        
        
        root = new Group();
        myScene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
        
        myScene.setOnKeyPressed(b -> e.receiveInput(b));
		myScene.setOnKeyReleased(b -> e.receiveInput(b));

        buildEntities();
        initRects();
    }

    private void updateRectPos(){
        PositionComponent pos = (PositionComponent) e1.getComponent(PositionComponent.class);
        double x = pos.getX() - r1.getWidth()/2;
        double y = pos.getY() - r1.getHeight()/2;

        double theta = pos.getAngle();

        r1.setX(x);
        r1.setY(y);
        r1.setRotate(theta);
    }

    private void updateRectColor(){
        if(e1.getComponent(CollidedComponent.class)!=null){
            r1.setFill(Color.BLUE);
        }
        else{
            if(r1.getFill()!=Color.GRAY){
                r1.setFill(Color.GRAY);
            }
        }
    }

    private void buildEntities(){
    	e1 = new Entity();
        e2 = new Entity();
        e3 = new Entity();

    	//Movement Input Componenet		
    	List<String> keyboardMovementInputArgs = new ArrayList<>();
    	keyboardMovementInputArgs.add(KeyCode.LEFT.toString());
    	keyboardMovementInputArgs.add(KeyCode.RIGHT.toString());
    	KeyboardMovementInputComponent keyboardInputComponent = new KeyboardMovementInputComponent(keyboardMovementInputArgs);
    	e1.addComponent(keyboardInputComponent);
    	
    	//Physics Component
    	List<String> yPhysicsArgs = new ArrayList<>();
    	yPhysicsArgs.add(JUMP_VELOCITY);
    	yPhysicsArgs.add(GRAVITY); //acceleration
    	YPhysicsComponent xPhysicsComponent = new YPhysicsComponent(yPhysicsArgs);
    	e1.addComponent(xPhysicsComponent);

    	List<String> xPhysicsArgs = new ArrayList<>();
    	xPhysicsArgs.add("400"); //X velocity aka maxX velocity aka dx (the distance it moves each step)
    	xPhysicsArgs.add("0"); //acceleration
    	XPhysicsComponent yPhysicsComponent = new XPhysicsComponent(xPhysicsArgs);
    	yPhysicsComponent.setCurrVel(0);
    	e1.addComponent(yPhysicsComponent);
    			
    	//Jump Component
    	List<String> jumpArgs = new ArrayList<String>();
    	jumpArgs.add("true");
    	jumpArgs.add("-1"); //number of jumps
    	// Y velocity
    	JumpComponent jumpComponent= new JumpComponent(jumpArgs);
    	e1.addComponent(jumpComponent);
    			
    	//Jump Input Component
    	ArrayList<String> jumpInputArgs = new ArrayList<String>();
    	jumpInputArgs.add(KeyCode.UP.toString()); // Press UP for jump
    	KeyboardJumpInputComponent keyboardJumpInputComponent = new KeyboardJumpInputComponent(jumpInputArgs);
    	e1.addComponent(keyboardJumpInputComponent);
    	
        ArrayList<String> phys =new ArrayList<String>();
        for(int i = 0; i < 2; i ++)
            phys.add("0");

        ArrayList<String> pos1 = new ArrayList<String>();
        pos1.add("250");
        pos1.add("250");
        pos1.add("0");

        ArrayList<String> pos2 = new ArrayList<String>();
        pos2.add("250");
        pos2.add("1000");
        pos2.add("0");
        
        ArrayList<String> pos3 = new ArrayList<String>();
        pos3.add("550");
        pos3.add("700");
        pos3.add("0");

        ArrayList<String> hb2 = new ArrayList<String>();
        hb2.add("3000");
        hb2.add("500");
        hb2.add("0");
        hb2.add("0");

        ArrayList<String> hb1 = new ArrayList<String>();
        hb1.add("50");
        hb1.add("50");
        hb1.add("0");
        hb1.add("0");
        
        ArrayList<String> hb3 = new ArrayList<String>();
        hb3.add("1000");
        hb3.add("1000");
        hb3.add("0");
        hb3.add("0");

        ArrayList<String> cc = new ArrayList<String>();
        cc.add("true");
        cc.add("true");
        cc.add("0");

        
        e1.addComponent(new PositionComponent(pos1));
        e1.addComponent(new HitboxComponent(hb1));
        e1.addComponent(new CollidableComponent(cc));

        e2.addComponent(new XPhysicsComponent(phys));
        e2.addComponent(new YPhysicsComponent(phys));
        e2.addComponent(new PositionComponent(pos2));
        e2.addComponent(new HitboxComponent(hb2));
        e2.addComponent(new CollidableComponent(cc));
        
        
        e3.addComponent(new XPhysicsComponent(phys));
        e3.addComponent(new YPhysicsComponent(phys));
        e3.addComponent(new PositionComponent(pos3));
        e3.addComponent(new HitboxComponent(hb3));
        e3.addComponent(new CollidableComponent(cc));

        
        e.addEntity(e1);
        e.addEntity(e2);
        e.addEntity(e3);
    }

    private void initRects(){
        PositionComponent pos1 = (PositionComponent) e1.getComponent(PositionComponent.class);
        PositionComponent pos2 = (PositionComponent) e2.getComponent(PositionComponent.class);
        PositionComponent pos3 = (PositionComponent) e3.getComponent(PositionComponent.class);

        HitboxComponent hb1 = (HitboxComponent) e1.getComponent(HitboxComponent.class);
        HitboxComponent hb2 = (HitboxComponent) e2.getComponent(HitboxComponent.class);
        HitboxComponent hb3 = (HitboxComponent) e3.getComponent(HitboxComponent.class);


        r1 = new Rectangle(pos1.getX() - hb1.getWidth()/2, pos1.getY() - hb1.getHeight()/2, hb1.getWidth(), hb1.getHeight());
        r2 = new Rectangle(pos2.getX() - hb2.getWidth()/2, pos2.getY() - hb2.getHeight()/2, hb2.getWidth(), hb2.getHeight());
        r3 = new Rectangle(pos3.getX() - hb3.getWidth()/2, pos3.getY() - hb3.getHeight()/2, hb3.getWidth(), hb3.getHeight());

        root.getChildren().add(r1);
        root.getChildren().add(r2);
        root.getChildren().add(r3);
    }

    /**
     * Starts the program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
