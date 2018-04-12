package game_engine.components.collision.hitbox;

import java.util.List;

import game_engine.Component;

/**
 * @author: Jeremy Chen
 * Component containing AABB dimensions and X&Y offset for the center of the hitbox (relative to the sprice center)
 * Some/many methods will be refactored into RectangularHitbox (but currently only using AABB for collsions, so this is the main hitbox class)
 */
public class HitboxComponent implements Component {

    private double myWidth;
    private double myHeight;
    private double myXOffset;
    private double myYOffset;

    /**
     * @param args
     * 
     */
    public HitboxComponent (List<String> args){
        myWidth = Double.parseDouble(args.get(0));
        myHeight = Double.parseDouble(args.get(1));
        myXOffset = Double.parseDouble(args.get(2));
        myYOffset = Double.parseDouble(args.get(3));
    }

//    @Override
    /**
     * @return
     */
    public double getXOffset() {
        return myXOffset;
    }

//    @Override
    /**
     * @return
     */
    public double getYOffset() {
        return myYOffset;
    }

//    @Override
    /**
     * @return
     */
    public double getWidth() {
        return myWidth;
    }

//    @Override
    /**
     * @return
     */
    public double getHeight() {
        return myHeight;
    }

	/* (non-Javadoc)
	 * @see game_engine.Component#getValues()
	 * To support compatibility with a stopgap technique in Authoring to support editing of existing component
	 */
	@Override
	public String getValues() {
		String val = "Width,d," + myWidth + ";Height,d," + myHeight + ";xOffset,d," + myXOffset + ";yOffset,d," + myYOffset;
		return val;
	}

	/* (non-Javadoc)
	 * @see game_engine.Component#getName()
	 * To support compatibility with a stopgabp technique in Authoring to support persistent naming of components
	 */
	@Override
	public String getName() {
		return "Hitbox";
	}
}
