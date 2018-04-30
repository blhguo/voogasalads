package game_engine.test;

import java.util.HashMap;
import java.util.Map;

import gameData.ManipData;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.PrimeComponent;
import game_engine.components.collision.CollidableComponent;
import game_engine.components.collision.PassableComponent;
import game_engine.components.collision.hitbox.HitboxHeightComponent;
import game_engine.components.collision.hitbox.HitboxWidthComponent;
import game_engine.components.collision.hitbox.HitboxXOffsetComponent;
import game_engine.components.collision.hitbox.HitboxYOffsetComponent;
import game_engine.components.keyboard.KeyboardJumpInputComponent;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.keyboard.RightKeyboardComponent;
import game_engine.components.physics.DefaultXVelComponent;
import game_engine.components.physics.DefaultYVelComponent;
import game_engine.components.physics.XAccelComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YAccelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.JumpFilenameComponent;
import game_engine.components.sprite.RunFilenameComponent;
import game_engine.components.sprite.SpritePolarityComponent;
import game_engine.components.sprite.StandFilenameComponent;
import game_engine.components.sprite.VisibilityComponent;
import game_engine.components.sprite.WidthComponent;
import game_engine.components.sprite.ZHeightComponent;
import game_engine.level.Level;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class RainTest extends Application {

	private Entity mainCharacter;
	private Engine engine;

	private void setup(){
		buildMainCharacterEntity();
		buildEngine();
		initRects();
		
		ManipData data = new ManipData();
		Map<String, String> map = new HashMap<>();
		map.put("dog", "cat");
		map.put("potato", "fruit");
		data.saveData(engine, "Rain", map);
	}

	private void buildMainCharacterEntity(){
		mainCharacter = new Entity();
		
		//components needed for each of the 4 keyboard movement systems
		mainCharacter.addComponent(new DefaultXVelComponent("200"));
		mainCharacter.addComponent(new DefaultYVelComponent("2000"));
		mainCharacter.addComponent(new LeftKeyboardComponent(KeyCode.A.toString()));
		mainCharacter.addComponent(new RightKeyboardComponent(KeyCode.D.toString()));
		mainCharacter.addComponent(new KeyboardJumpInputComponent(KeyCode.W.toString()));
		mainCharacter.addComponent(new XVelComponent("0"));
		mainCharacter.addComponent(new YVelComponent("0"));
		mainCharacter.addComponent(new PrimeComponent());
		mainCharacter.addComponent(new VisibilityComponent("true"));

		//extra components needed for velocity system
		mainCharacter.addComponent(new XAccelComponent("0"));
		mainCharacter.addComponent(new YAccelComponent("-400"));

		//extra components needed for position system
		mainCharacter.addComponent(new XPosComponent("500"));
		mainCharacter.addComponent(new YPosComponent("300"));

		//extra components needed for collision detection
		mainCharacter.addComponent(new HitboxHeightComponent("200.0"));
		mainCharacter.addComponent(new HitboxWidthComponent("200.0"));
		mainCharacter.addComponent(new HitboxXOffsetComponent("0.0"));
		mainCharacter.addComponent(new HitboxYOffsetComponent("0.0"));
		mainCharacter.addComponent(new CollidableComponent("true"));
		mainCharacter.addComponent(new PassableComponent("false"));
	}

	private void buildEngine(){
		engine = new Engine();
		Level level1 = engine.createLevel();
		level1.addEntity(mainCharacter);
	}

	private void initRects(){
		// components needed for sprite component
		mainCharacter.addComponent(new FilenameComponent("mario_stand.png"));
		mainCharacter.addComponent(new StandFilenameComponent("mario_stand.png"));
		mainCharacter.addComponent(new RunFilenameComponent("mario_run.gif"));
		mainCharacter.addComponent(new JumpFilenameComponent("mario_stand.png"));
		mainCharacter.addComponent(new HeightComponent("200"));
		mainCharacter.addComponent(new WidthComponent("200"));
		mainCharacter.addComponent(new ZHeightComponent("2"));
		mainCharacter.addComponent(new SpritePolarityComponent("1"));
		mainCharacter.addComponent(new VisibilityComponent("true"));
		
		Entity referencePoint = new Entity();
		referencePoint.addComponent(new FilenameComponent("clouds.png"));
		referencePoint.addComponent(new XPosComponent("500"));
		referencePoint.addComponent(new YPosComponent("300"));
		referencePoint.addComponent(new HeightComponent("500"));
		referencePoint.addComponent(new WidthComponent("500"));
		referencePoint.addComponent(new ZHeightComponent("0"));
		referencePoint.addComponent(new VisibilityComponent("true"));
		engine.getLevel().addEntity(referencePoint);
		
		Entity block = new Entity();
		block.addComponent(new FilenameComponent("brick.png"));
		block.addComponent(new HeightComponent("200"));
		block.addComponent(new WidthComponent("1400"));
		block.addComponent(new HitboxHeightComponent("200.0"));
		block.addComponent(new HitboxWidthComponent("1400.0"));
		block.addComponent(new HitboxXOffsetComponent("0.0"));
		block.addComponent(new HitboxYOffsetComponent("0.0"));
		block.addComponent(new CollidableComponent("true"));
		block.addComponent(new VisibilityComponent("true"));
		block.addComponent(new ZHeightComponent("1"));
		block.addComponent(new XPosComponent("1000"));
		block.addComponent(new YPosComponent("700"));
		block.addComponent(new PassableComponent("false"));
		engine.getLevel().addEntity(block);
	}


	/**
	 * Starts the program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		setup();		
	}
}
