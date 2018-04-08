//package game_engine.components;
//
//import game_engine.Component;
//import javafx.util.Pair;
//
//public interface HitboxComponent extends Component {
//    /**
//     * Returns how much center of hitbox is offset from center of sprite
//     * @return
//     */
//    public double getXOffset();
//    public double getYOffset();
//    public double getWidth();
//    public double getHeight();
//}

// TODO: TEMP FIX FOR NOW, WILL FIX LATERS

package game_engine.components;

import game_engine.Component;
import javafx.util.Pair;

import java.util.List;

/**
 * @author: Jeremy Chen
 * Component containing offset and
 */
public class HitboxComponent implements Component {

    private double myWidth;
    private double myHeight;
    private double myXOffset;
    private double myYOffset;

    public HitboxComponent(List<String> args){
        myWidth = Double.parseDouble(args.get(0));
        myHeight = Double.parseDouble(args.get(1));
        myXOffset = Double.parseDouble(args.get(2));
        myYOffset = Double.parseDouble(args.get(3));
    }

//    @Override
    public double getXOffset() {
        return myXOffset;
    }

//    @Override
    public double getYOffset() {
        return myYOffset;
    }

//    @Override
    public double getWidth() {
        return myWidth;
    }

//    @Override
    public double getHeight() {
        return myHeight;
    }
}
