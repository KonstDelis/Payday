package Model.Tiles;

import java.security.InvalidParameterException;

import Controller.Controller;
import Model.Point;

/**
 * This is an abstract class of a general Tile. It contains the number of the order of the
 * tile and the path of the image of the tile, its location as well as set 
 * and get methods for these class members
 * @author csd4623
 * @version 1.0
 */
public abstract class Tile {
	private int orderNumber;
	private String imagePath;
	private Point location;
	private Boolean isThursday;
	private Boolean isSunday;
	/**
	 * <b>constructor</b>: Constructs a new Tile<br>
     * <b>postcondition</b>: Creates and initializes a Tile. 
     * Also initializes the other variables (orderNumber, location, isThursday, isSunday etc)<br>
	 */
	public Tile() {
		this.orderNumber = -1;
		location = null;
		isThursday = false;
		isSunday = false;
	}
	/**
	 * <b>transformer</b>: sets the order number of the tile<br>
	 * <b>Precondition</b>: the orderNumber must be between 0 and 32<br>
	 * <b>Postcondition</b>: the order number has changed<br>
	 * @param orderNumber
	 */
	public void setOrderNumber(int orderNumber) {
		if(orderNumber<0||orderNumber>32) {
			throw new InvalidParameterException("The order number of the tile must be a number between 0 and 32");
		}
		else {
			this.orderNumber = orderNumber;
		}
	}
	/**
	 * <b>accessor</b>: returns the order number of the card<br>
	 * <b>Postcondition</b>: The order number has been returned<br>
	 * @return int order number
	 */
	public int getOrderNumber() {return orderNumber;}
	/**
	 * <b>accessor</b>: returns the imagePath of the card<br>
	 * <b>Postcondition</b>: The imagePath has been returned<br>
	 * @return String path of the image
	 */
	public String getImagePath() {return imagePath;}
	/**
	 * <b>transformer</b>: sets the location of the tile (or null is the tile does not have a location)<br>
	 * <b>Postcondition</b>: the location has changed<br>
	 * @param location
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
	/**
	 * <b>accessor</b>: returns the location of the card<br>
	 * <b>Postcondition</b>: The location has been returned<br>
	 * @return Point location of the tile
	 */
	public Point getLocation() {return location;}
	/**
	 * <b>transformer</b>: sets the isSunday member of the tile to true or false.
	 * If param is 'true' and isThursday is also true it changes isThursday to false<br>
	 * <b>Postcondition</b>: isSunday has changed<br>
	 * @param isSunday true/false 
	 */
	public void setIsSunday(Boolean isSunday) {
		if(isSunday==true&&this.isThursday==true) {
			this.isThursday=false;
		}
		this.isSunday = isSunday;
	}
	/**
	 * <b>observer</b>: returns the state of isSunday<br>
	 * <b>Postcondition</b>: the state if isSunday has been returned<br>
	 * @return
	 */
	public Boolean isSunday() {return isSunday;}
	/**
	 * <b>transformer</b>: sets the isThursday member of the tile to true or false.
	 * If param is 'true' and isSunday is also true it changes isSunday to false<br>
	 * <b>Postcondition</b>: isThrursday has changed<br>
	 * @param isThursday
	 */
	public void setIsThursday(Boolean isThursday) {
		if(this.isSunday==true&&isThursday==true) {
			this.isSunday=false;
		}
		this.isThursday = isThursday ;
	}
	/**
	 * <b>observer</b>: returns the state of isThursday<br>
	 * <b>Postcondition</b>: the state if isThursday has been returned<br>
	 * @return
	 */
	public Boolean isThursday() {return isThursday;}
	/**
	 * returns the String representation of the Tile<br>
	 * <b>Postcondition</b>: the String representation of the tile has been returned<br>
	 * @return String representation
	 */
	public String toString() {
		String toStr = "Order number: "+orderNumber+", isThursday:"+isThursday()+", isSunday:"+
				isSunday()+" location:";
		if(location == null) {
			toStr+= " doesn't currently have a location";
		}
		else {
			toStr+= "("+location.getXcoordinate()+", "+location.getYcoordinate()+")";
		}
		
		return toStr;}
	/**
	 * <b>transformer</b>: When the Player moves to this tile this method does
	 * all the necessary actions according to the type of the tile (eg give him
	 * money, take money from him. If it is Thursday it also<br>
	 * <b>Postcondition</b>: The actions have been performed and a string describing all the 
	 * actions that have been done is returned(Will be used in the output panel of the GUI)<br>
	 * @param controller the controller class of the game
	 * @return the string with the description of the actions
	 */
	public abstract String tileAction(Controller controller);
	//Is used by the subclasses
	protected void setImagePath(String imagePath) {this.imagePath = imagePath;}
}