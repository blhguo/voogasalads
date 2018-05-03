package game_engine.systems.collision;
import game_engine.Entity;
import game_engine.level.Level;

@Deprecated
public class CollisionNarrowGJKSystem extends CollisionNarrowSystem {

    @Override
    protected void checkIntersect(Entity e1, Entity e2, double elapsedTime) {

    }

    @Override
    public void act(double elapsedTime, Level level) {

    }
}

//package game_engine.systems.collision;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//import game_engine.Component;
//import game_engine.Entity;
//import game_engine.components.collision.edge_collided.BottomCollidedComponent;
//import game_engine.components.collision.edge_collided.LeftCollidedComponent;
//import game_engine.components.collision.edge_collided.RightCollidedComponent;
//import game_engine.components.collision.edge_collided.TopCollidedComponent;
//import game_engine.components.collision.hitbox.GJKHitboxComponent;
//import game_engine.components.position.AngleComponent;
//import game_engine.components.position.XPosComponent;
//import game_engine.components.position.YPosComponent;
//import game_engine.level.Level;
//import javafx.geometry.Point2D;
//import javafx.scene.shape.Polygon;
//
///**
// * @author Jeremy Chen
// *
// *         Consulted with dyn4j's explanation of the GJK collision detection system:
// *         http://www.dyn4j.org/2010/04/gjk-gilbert-johnson-keerthi/ Adapted from Bryan Chacosky's
// *         implementation of GJK: https://github.com/bryanchacosky/gjk.java/blob/master/GJK.java
// *
// */
//public class CollisionNarrowGJKSystem extends CollisionNarrowSystem {
//	private static final Class<? extends Component<List<Entity>>> TOP = TopCollidedComponent.class;
//	private static final Class<? extends Component<List<Entity>>> BOTTOM = BottomCollidedComponent.class;
//	private static final Class<? extends Component<List<Entity>>> RIGHT = RightCollidedComponent.class;
//	private static final Class<? extends Component<List<Entity>>> LEFT = LeftCollidedComponent.class;
//	private static final Class<? extends Component<List<Double[]>>> GJK = GJKHitboxComponent.class;
//
//	private static final List<Class<? extends Component<?>>> TARGET_COMPONENTS = Collections
//			.unmodifiableList(new ArrayList<Class<? extends Component<?>>>() {
//				{
//					add(TOP);
//					add(BOTTOM);
//					add(LEFT);
//					add(RIGHT);
//				}
//			});
//	private static final List<Class<? extends Component<?>>> GJK_COMPONENTS = Collections
//			.unmodifiableList(new ArrayList<Class<? extends Component<?>>>() {
//				{
//					add(GJK);
//				}
//			});
//
//	@Override
//	protected void checkIntersect(Entity e1, Entity e2, double elapsedTime) {
//		// TODO: unimplemented
//	}
//
//	private Point2D supportFunct() {
//		return null;
//	}
//
//	private Point2D minkowskiDiff() {
//		return null;
//	}
//
//	@Override
//	public void act(double elapsedTime, Level level) {
//		List<Entity> gjkEntities = level.getEntitiesContaining(GJK_COMPONENTS);
//		List<Entity> broadCollidedEntities = level.getEntitiesContainingAny(gjkEntities, TARGET_COMPONENTS);
//		for (int i = 0; i < broadCollidedEntities.size(); i++) {
//			Entity e1 = broadCollidedEntities.get(i);
//			List<Entity> others = new CopyOnWriteArrayList<>();
//			List<Class<? extends Component<List<Entity>>>> collidedClasses = new ArrayList<>();
//			for (Class<? extends Component<?>> c : TARGET_COMPONENTS) {
//				Component<List<Entity>> comp = (Component<List<Entity>>) e1.getComponent(c);
//				if (comp != null) {
//					collidedClasses.add((Class<? extends Component<List<Entity>>>) c);
//					others.addAll(comp.getValue());
//				}
//			}
//			for (Entity e2 : others) {
//				checkIntersect(e1, e2, elapsedTime);
//			}
//			for (Class<? extends Component<?>> c : collidedClasses) {
//				Component<List<Entity>> collidedComp = (Component<List<Entity>>) e1.getComponent(c);
//				if (collidedComp != null && collidedComp.getValue().size() == 0) {
//					e1.removeComponent(c);
//				}
//			}
//		}
//	}
//
//	private List<Point2D> generatePolygon(Entity e) {
//		double xPos = e.getComponent(XPosComponent.class).getValue();
//		double yPos = e.getComponent(YPosComponent.class).getValue();
//		double angle = e.getComponent(AngleComponent.class).getValue();
//		List<Double[]> relativeVertices = e.getComponent(GJKHitboxComponent.class).getValue();
//		Polygon polygon = new Polygon();
//		List<Point2D> transformedVertices = new ArrayList<>();
//
//		for (int i = 0; i < relativeVertices.size(); i++) {
//			double absoluteX = xPos + relativeVertices.get(i)[0];
//			double absoluteY = yPos + relativeVertices.get(i)[1];
//			Point2D transformedVertex = transformPoint(new Point2D(absoluteX, absoluteY), new Point2D(xPos, yPos),
//					angle);
//			transformedVertices.add(transformedVertex);
//		}
//
//		return transformedVertices;
//	}
//
//}
