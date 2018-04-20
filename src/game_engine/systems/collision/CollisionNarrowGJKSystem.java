package game_engine.systems.collision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.Vector;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.components.collision.hitbox.GJKHitboxComponent;
import game_engine.components.position.AngleComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 * @author Jeremy Chen
 *
 * Consulted with dyn4j's explanation of the GJK collision detection system: http://www.dyn4j.org/2010/04/gjk-gilbert-johnson-keerthi/
 * Adapted from Bryan Chacosky's implementation of GJK: https://github.com/bryanchacosky/gjk.java/blob/master/GJK.java
 *
 */
public class CollisionNarrowGJKSystem extends CollisionNarrowSystem{
	private static final Class<? extends Component<List<Entity>>> TOP = TopCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> BOTTOM = BottomCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> RIGHT = RightCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> LEFT = LeftCollidedComponent.class;
	private static final Class<? extends Component<List<Double[]>>> GJK = GJKHitboxComponent.class;
	
	private static final List<Class<? extends Component<?>>> TARGET_COMPONENTS = Collections.unmodifiableList(
			new ArrayList<Class<? extends Component<?>>>() {{
				add(TOP);
				add(BOTTOM);
				add(LEFT);
				add(RIGHT);
			}});
	private static final List<Class<? extends Component<?>>> GJK_COMPONENTS = Collections.unmodifiableList(
			new ArrayList<Class<? extends Component<?>>>() {{
				add(GJK);
			}});
	
	public CollisionNarrowGJKSystem(Engine engine) {
		super(engine);
	}

	@Override
	protected void checkIntersect(Entity e1, Entity e2, double elapsedTime) {
		
		
	}

	@Override
	public void act(double elapsedTime) {
		List<Entity> gjkEntities = getEngine().getEntitiesContaining(GJK_COMPONENTS);
		List<Entity> broadCollidedEntities = getEngine().getEntitiesContainingAny(gjkEntities, TARGET_COMPONENTS);
		for(int i = 0; i < broadCollidedEntities.size(); i ++) {
			Entity e1 = broadCollidedEntities.get(i);
			List<Entity> others = new CopyOnWriteArrayList<Entity>();
			List<Component<List<Entity>>> collidedComponents = new ArrayList<Component<List<Entity>>>();
			List<Class<? extends Component<List<Entity>>>> collidedClasses = new ArrayList<Class<? extends Component<List<Entity>>>>();
			for(Class<? extends Component<?>> c: TARGET_COMPONENTS) {
				Component<List<Entity>> comp = (Component<List<Entity>>) e1.getComponent(c);
				if(comp !=null) {
					collidedComponents.add(comp);
					collidedClasses.add((Class<? extends Component<List<Entity>>>) c);
					others.addAll(comp.getValue());
				}
			}
			for(Entity e2: others) {
				checkIntersect(e1,e2, elapsedTime);
			}
			for(Class<? extends Component<?>> c: collidedClasses) {
				Component<List<Entity>> collidedComp = (Component<List<Entity>>) e1.getComponent(c);
				if(collidedComp != null && collidedComp.getValue().size()==0) {
					e1.removeComponent(c);
				}
			}
		}
	}
	
	private Polygon generatePolygon(Entity e) {
		double xPos = e.getComponent(XPosComponent.class).getValue();
		double yPos = e.getComponent(YPosComponent.class).getValue();
		double angle = e.getComponent(AngleComponent.class).getValue();
		List<Double[]> relativeVertices = e.getComponent(GJKHitboxComponent.class).getValue();
		Polygon polygon = new Polygon();
		Double[] transformedVertices = new Double[relativeVertices.size()*2];
		
		for(int i = 0; i < relativeVertices.size(); i ++ ) {
			double absoluteX = xPos + relativeVertices.get(i)[0];
			double absoluteY = yPos + relativeVertices.get(i)[1];
			Point2D transformedRelativeVertex = transformPoint(new Point2D(absoluteX, absoluteY), new Point2D(xPos, yPos), angle);
			transformedVertices[i*2] = transformedRelativeVertex.getX();
			transformedVertices[i*2 + 1] = transformedRelativeVertex.getY();
		}
		
		polygon.getPoints().addAll(transformedVertices);
		return polygon;
	}
	
	private void supportFunc() {
		
	}	

}
