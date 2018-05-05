package game_engine.event.conditions;

import java.util.Arrays;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.Vector;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.WidthComponent;
import game_engine.event.ComponentNotPresentException;
import game_engine.event.Condition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 * This condition checks if a mouse click is located at the same spot as a specified coordinates
 * @author Kevin D
 *
 */
public class MouseInputCondition implements Condition {
	
	private static final Class<? extends Component<Double>> X_POS = XPosComponent.class;
	private static final Class<? extends Component<Double>> Y_POS = YPosComponent.class;
	private static final Class<? extends Component<Double>> WIDTH = WidthComponent.class;
	private static final Class<? extends Component<Double>> HEIGHT = HeightComponent.class;

	private Engine myEngine;
	private Entity myEntity;
	
	public MouseInputCondition(Engine engine, Entity entity){
		myEngine = engine;
		myEntity = entity;
		
		if (! myEntity.hasAll(Arrays.asList(X_POS, Y_POS, WIDTH, HEIGHT))) {
			throw new ComponentNotPresentException(Arrays.asList(X_POS, Y_POS, WIDTH, HEIGHT));
		}
	}
	
	@Override
	public boolean evaluate() {
		double xPos = myEntity.getComponent(X_POS).getValue();
		double yPos = myEntity.getComponent(Y_POS).getValue();
		double width = myEntity.getComponent(WIDTH).getValue();
		double height = myEntity.getComponent(HEIGHT).getValue();
		
		for (Vector click : myEngine.getMouseInputs()) {
			if (withinBounds(click, xPos, yPos, width, height)) {
				return true;
			}
		}
		return false;
	}

	private boolean withinBounds(Vector click, double xPos, double yPos, double width, double height) {
		double minX = xPos - width / 2;
		double minY = yPos - height / 2;
		Bounds bounds = new BoundingBox(minX, minY, width, height);
		return bounds.contains(click.getX(), click.getY());
	}

}
