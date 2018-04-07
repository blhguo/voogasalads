package game_engine.components;
import game_engine.Component;

public class CollidedComponent implements Component{
    private boolean collided;
    private ECollisionSide collisionSide;

    public CollidedComponent(){
        collided = true;
    }

    public ECollisionSide getCollisionSide(){
        return collisionSide;
    }

    public boolean getCollided(){
        return collided;
    }
}
