package Model.Tiles;

import Controller.Controller;

/**
 * Subclass of Tile, is this subclass the tileAction method is empty as the 
 * starting tile has no action
 * @author csd4623
 * @version 1.0
 */
public class StartTile extends Tile {
	/**
	 * <b>constructor</b>: Constructs a new DealTile with the given
     * parameters orderNumber, imagePath and numberOfMessageCards<br>
     * <b>postcondition</b>: Creates and initializes a DealTile with the given
     * orderNumber and imagePath<br>
	 */
	public StartTile() {
		super();
		setImagePath("src/resources/images2/start.png");
	}
	/**
	 * The StartTile doesn't have an action as it is the starting tile, method
	 * is empty<br>
	 */
	@Override
	public String tileAction(Controller controller) {return "";}

	public String toString() {
		String toStr = "Type: StartTile, " + super.toString();
		return toStr;
	}
}
