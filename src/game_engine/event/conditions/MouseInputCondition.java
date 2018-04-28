package game_engine.event.conditions;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.WidthComponent;
import game_engine.event.Condition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;

public class MouseInputCondition implements Condition{

	private Engine myEngine;
	private Entity myEntity;
	
	public MouseInputCondition(Engine engine, Entity entity){
		myEngine = engine;
		myEntity = entity;
	}
	
	@Override
	public boolean evaluate() {
		double xPos = myEntity.getComponent(XPosComponent.class).getValue();
		double yPos = myEntity.getComponent(YPosComponent.class).getValue();
		double width = myEntity.getComponent(WidthComponent.class).getValue();
		double height = myEntity.getComponent(HeightComponent.class).getValue();
		
		for (MouseEvent input : myEngine.getMouseInputs()){
			if (withinBounds(input, xPos, yPos, width, height)){
				return true;
			}
		}
		return false;
	}

	private boolean withinBounds(MouseEvent input, double xPos, double yPos, double width, double height) {
		double minX = xPos - width / 2;
		double minY = yPos - height / 2;
		Bounds bounds = new BoundingBox(minX, minY, width, height);
		return bounds.contains(input.getX(), input.getY());
	}

}
