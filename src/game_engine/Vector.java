package game_engine;

/**
 * 
 * @author benhubsch
 * 
 *         The Class Vector is a convenient data structure for much of the backend, given that many
 *         abstractions can be represented using a Vector.
 */
public class Vector {

	private double myX;
	private double myY;

	/**
	 * Instantiates a new Vector object.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Vector(double x, double y) {
		myX = x;
		myY = y;
	}

	/**
	 * Gets the double object.
	 *
	 * @return double
	 */
	public double getX() {
		return myX;
	}

	/**
	 * Gets the double object.
	 *
	 * @return double
	 */
	public double getY() {
		return myY;
	}
}
