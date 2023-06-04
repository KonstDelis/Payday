package Model.Tiles;

import Controller.Controller;


public class PaydayTile extends Tile{
	/**
	 * <b>constructor</b>: Constructs a new LotteryTile with the given
     * parameters orderNumber and imagePath<br>
     * <b>postcondition</b>: Creates and initializes a LotteryTile with the given
     * orderNumber and imagePath<br>
	 */
	public PaydayTile() {
		super();
		setImagePath("src/resources/images2/pay.png");
	}
	/**
	 * <b>transformer</b>: The following actions occur:
	 * The player that moved to this tile receives 3500$ 
	 * from the bank, pays his bills and the bill cards are removed. He also 
	 * pays 10% of his current loans (if he does't have money he gets another 
	 * loan). He can then payoff his loans or part of them. If this is his last 
	 * month then he doesn't play again, else he is moved back to the start.<br>
	 * <b>postcondition</b>: All the necessary actions have been performed and
	 * the data has changed<br>
	 * @param controller the controller of the game
	 */
	@Override
	public String tileAction(Controller controller) {
		String description = controller.payday();
		return description;}
	
	public String toString() {
		String toStr = "Type: PaydayTile, " + super.toString();
		return toStr;
	}
}
