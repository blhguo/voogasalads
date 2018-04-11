//package game_engine.components;
//import game_engine.Component;
//import game_engine.Entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class CollidedComponent implements Component{
//    private boolean collided;
//    private ECollisionSide collisionSide;
//    private List<Entity> collidedWith;
//
//    public CollidedComponent(List<String> args){
//        this();
//    }
//
//    public CollidedComponent(){
//        collided = true;
//        collidedWith = new ArrayList<Entity>();
//    }
//
//    public void addCollidedWith(Entity e2) {
//    	collidedWith.add(e2);
//    }
//
//    public ECollisionSide getCollisionSide(){
//        return collisionSide;
//    }
//
//    public void setCollisionSide(ECollisionSide cs) {
//    	collisionSide = cs;
//    }
//
//    public boolean getCollided(){
//        return collided;
//    }
//
//    @Override
//    public String getValues() {
//        return null;
//    }
//
//    @Override
//    public String getName() {
//        return "Collided";
//    }
//}
