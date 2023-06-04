package Model.Tiles;

import Controller.Controller;

/**
 * Subclass of Tile, the only difference from Tile is the action method
 * that performs the necessary actions of each round
 * @author csd4623
 * @version 1.0
 */
public class FamilyCasinoTile extends Tile {
	/**
	 * <b>constructor</b>: Constructs a new LotteryTile with the given
     * parameters orderNumber and imagePath<br>
     * <b>postcondition</b>: Creates and initializes a LotteryTile with the given
     * orderNumber and imagePath<br>
	 */
	public FamilyCasinoTile() {
		super();
		setImagePath("src/resources/images2/casino.png");
	}
	/**
	 * <b>transformer</b>: The following actions occur:
	 * If the player rolled an odd number to reach this
	 * tile he has to pay 500$ to the jackpot else he receives 500$ from the bank
	 * In case there is a special event (Sunday match/thursday crypto) all the 
	 * necessary actions also take place<br>
	 * <b>postcondition</b>: All the necessary actions have been performed and
	 * the data has changed<br>
	 * @param controller the controller of the game
	 */
	@Override
	public String tileAction(Controller controller) {
		if(isSunday()) controller.sundayMatch();
		if(isThursday()) controller.crypto();
		String description = controller.family();
		return description;
	}

	public String toString() {
		String toStr = "Type: FamilyCasinoTile, " + super.toString();
		return toStr;
	}
}
