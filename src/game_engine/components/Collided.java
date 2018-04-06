package game_engine.components;
import game_engine.Component;

public class Collided implements Component{
    private ECollisionSide collisionSide;

    public Collided(){

    }

    public ECollisionSide getCollisionSide(){
        return collisionSide;
    }
}
