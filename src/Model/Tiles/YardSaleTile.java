package Model.Tiles;

import Controller.Controller;

/**
 * Subclass of Tile, the only difference from Tile is the action method
 * that performs the necessary actions of each round
 * @author csd4623
 * @version 1.0
 */
public class YardSaleTile extends Tile {
	/**
	 * <b>constructor</b>: Constructs a new LotteryTile with the given
     * parameters orderNumber and imagePath<br>
     * <b>postcondition</b>: Creates and initializes a LotteryTile with the given
     * orderNumber and imagePath<br>
	 */
	public YardSaleTile() {
		super();
		setImagePath("src/resources/images2/yard.png");
	}
	/**
	 * <b>transformer</b>: The following actions occur:
	 * The player that moved to this tile will have to roll
	 * the dice and pay 100x the roll of his dice to the bank
	 * In case there is a special event (Sunday match/Thursday crypto) all the 
	 * necessary actions also take place<br>
	 * <b>postcondition</b>: All the necessary actions have been performed and
	 * the data has changed<br>
	 * @param controller the controller of the game
	 */
	@Override
	public String tileAction(Controller controller) {
		if(isSunday()) controller.sundayMatch();
		if(isThursday()) controller.crypto();
		String description = controller.yardSale();
		return description;}

	public String toString() {
		String toStr = "Type: YardSaleTile, " + super.toString();
		return toStr;
	}
}
