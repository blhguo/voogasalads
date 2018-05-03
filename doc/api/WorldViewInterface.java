/**
 * Controls which level and where in the level the view should display; defines some scrolling and window behavior
 * @author Kevin D
 *
 */
public interface WorldViewInterface{
	/**
	 * Returns the geographical position of the level 
	 */
    void getLevelPosition();
    
    /**
     * Returns the current level
     */
    Level getLevel();
}
