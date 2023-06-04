package Model.Tiles;

import Controller.Controller;

/**
 * Subclass of Tile, the only difference from Tile is the action method
 * that performs the necessary actions of each round
 * @author csd4623
 * @version 1.0
 */
public class BuyerTile extends Tile {
	/**
	 * <b>constructor</b>: Constructs a new LotteryTile with the given
     * parameters orderNumber and imagePath<br>
     * <b>postcondition</b>: Creates and initializes a LotteryTile with the given
     * orderNumber and imagePath<br>
	 */
	public BuyerTile() {
		super();
		setImagePath("src/resources/images2/buyer.png");
	}
	/**
	 * <b>transformer</b>: The following actions occur:
	 * If a player moves to this tile he will have to sell
	 * one of his cards. If he has one it is automatically sold, else he will have
	 * to choose which of his cards he will sell.
	 * In case there is a special event (Sunday match/Thursday crypto) all the 
	 * Necessary actions also take place<br>
	 * <b>postcondition</b>: All the necessary actions have been performed and
	 * the data has changed<br>
	 * @param controller the controller of the game
	 */
	@Override
	public String tileAction(Controller controller) {
		if(isSunday()) controller.sundayMatch();
		if(isThursday()) controller.crypto();
		String description = controller.buyer();
		return description;
	}

	public String toString() {
		String toStr = "Type: BuyerTile, " + super.toString();
		return toStr;
	}
}
