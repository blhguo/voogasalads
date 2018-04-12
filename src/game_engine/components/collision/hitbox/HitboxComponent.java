package game_engine.components.collision.hitbox;

import java.util.List;

import game_engine.Component;

/**
 * @author: Jeremy Chen
 * Component containing offset and
 */
public class HitboxComponent implements Component {

    private double myWidth;
    private double myHeight;
    private double myXOffset;
    private double myYOffset;

    public HitboxComponent (List<String> args){
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

	@Override
	public String getValues() {
		String val = "Width,d," + myWidth + ";Height,d,5" + myHeight + ";xOffset,d," + myXOffset + ";yOffset,d," + myYOffset;
		return val;
	}

	@Override
	public String getName() {
		return "HitBox";
	}
}
