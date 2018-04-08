package game_engine.components;

import game_engine.Component;

public interface HitboxComponent extends Component {
    /**
     * Returns how much center of hitbox is offset from center of sprite
     * @return
     */
    public double getXOffset();
    public double getYOffset();
}
