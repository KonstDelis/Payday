package Model;
/**
 * A class that holds x,y coordinates
 * @author csd4623
 *
 */
public class Point {
	int x, y;
	/**
	 * <b>constructor</b>: Constructs a new Point with the given
     * coordinates<br>
     * <b>postcondition</b>: Creates and initializes a Point with the given
     * x,y coordinates. <br>
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * <b>transformer</b>: sets the x coordinate<br>
	 * <b>Postcondition</b>: The x coordinate has been set<br>
	 * @param x coordinate
	 */
	public void setXcoordinate(int x) {
		this.x= x;
	}
	/**
	 * <b>accessor</b>: returns the x coordinate<br>
	 * <b>Postcondition</b>: The x coordinate has been returned<br>
	 * @return x
	 */
	public int getXcoordinate() {
		return this.x;
	}
	/**
	 * <b>transformer</b>: sets the y coordinate<br>
	 * <b>Postcondition</b>: The y coordinate has been set<br>
	 * @param y
	 */
	public void setYcoordinate(int y) {
		this.y = y;
	}
	/**
	 * <b>accessor</b>: returns the y coordinate<br>
	 * <b>Postcondition</b>: The y coordinate has been returned<br>
	 * @return y
	 */
	public int getYcoordinate() {
		return this.y;
	}
}
