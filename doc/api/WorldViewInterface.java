package voogasalad_callussalad;

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
