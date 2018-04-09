package game_engine.test;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.*;
import game_engine.systems.CollisionBroadSystem;
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

import java.util.ArrayList;

public class CollisionTest extends Application {

    private Entity e1;
    private Entity e2;

    private Engine e;

    private Rectangle r1;
    private Rectangle r2;

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private static final String TITLE = "Collision Tester";
    private static final int WIDTH = 1600;
    private static final int HEIGHT= 800;
    private static final Paint BACKGROUND = Color.rgb(36, 36, 36);

    private Group root;
    private Scene myScene;

    private CollisionBroadSystem colSys;

    /**
     * What to do each time a key is pressed
     * Handles cheat keys and paddle controls
     * @param code
     */
    private void handleKeyInput(KeyCode code) {
        PositionComponent pos = (PositionComponent) e1.getComponent(PositionComponent.class);
        if(code == KeyCode.RIGHT){
           pos.setX(pos.getX()+5);
        }
        else if (code == KeyCode.LEFT){
            pos.setX(pos.getX()-5);
        }
        else if (code == KeyCode.UP){
            pos.setY(pos.getY()-5);
        }
        else if (code == KeyCode.DOWN){
            pos.setY(pos.getY()+5);
        }
        else if (code == KeyCode.Q){
            pos.setAngle(pos.getAngle()+1);
        }
        else if (code == KeyCode.E){
            pos.setAngle(pos.getAngle()-1);
            System.out.println(pos.getAngle());
        }
    }

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
        updateRectPos();
        updateRectColor();
    }

    private void setup(){
        e = new Engine();
        colSys = new CollisionBroadSystem(e);
        root = new Group();
        myScene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

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


        ArrayList<String> phys =new ArrayList<String>();
        for(int i = 0; i < 3; i ++)
            phys.add("0");

        ArrayList<String> pos1 = new ArrayList<String>();
        pos1.add("250");
        pos1.add("250");
        pos1.add("0");

        ArrayList<String> pos2 = new ArrayList<String>();
        pos2.add("1000");
        pos2.add("750");
        pos2.add("0");

        ArrayList<String> hb1 = new ArrayList<String>();
        hb1.add("500");
        hb1.add("500");
        hb1.add("0");
        hb1.add("0");

        ArrayList<String> cc = new ArrayList<String>();
        cc.add("true");
        cc.add("true");
        cc.add("0");

        e1 = new Entity();
        e1.addComponent(new PhysicsComponent(phys));
        e1.addComponent(new PositionComponent(pos1));
        e1.addComponent(new HitboxComponent(hb1));
        e1.addComponent(new CollidableComponent(cc));

        e2 = new Entity();
        e2.addComponent(new PhysicsComponent(phys));
        e2.addComponent(new PositionComponent(pos2));
        e2.addComponent(new HitboxComponent(hb1));
        e2.addComponent(new CollidableComponent(cc));

        e.addEntity(e1);
        e.addEntity(e2);
    }

    private void initRects(){
        PositionComponent pos1 = (PositionComponent) e1.getComponent(PositionComponent.class);
        PositionComponent pos2 = (PositionComponent) e2.getComponent(PositionComponent.class);

        HitboxComponent hb1 = (HitboxComponent) e1.getComponent(HitboxComponent.class);
        HitboxComponent hb2 = (HitboxComponent) e2.getComponent(HitboxComponent.class);

        r1 = new Rectangle(pos1.getX() - hb1.getWidth()/2, pos1.getY() - hb1.getHeight()/2, hb1.getWidth(), hb1.getHeight());
        r2 = new Rectangle(pos2.getX() - hb2.getWidth()/2, pos2.getY() - hb2.getHeight()/2, hb2.getWidth(), hb2.getHeight());

        root.getChildren().add(r1);
        root.getChildren().add(r2);
    }

    /**
     * Starts the program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
