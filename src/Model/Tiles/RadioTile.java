package Model.Tiles;

import Controller.Controller;

/**
 * Subclass of Tile, the only difference from Tile is the action method
 * that performs the necessary actions of each round
 * @author csd4623
 * @version 1.0
 */
public class RadioTile extends Tile {
	/**
	 * <b>constructor</b>: Constructs a new LotteryTile with the given
     * parameters orderNumber and imagePath<br>
     * <b>postcondition</b>: Creates and initializes a LotteryTile with the given
     * orderNumber and imagePath<br>
	 */
	public RadioTile() {
		super();
		setImagePath("src/resources/images2/radio.png");
	}
	/**
	 * <b>transformer</b>: The following actions occur:
	 * The two players roll the dice and the player with the 
	 * highest roll wins 1000$ from the bank. If there is a draw the dice is 
	 * rolled again.
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
		String description = controller.radio();
		return description;
	}
	
	public String toString() {
		String toStr = "Type: RadioTile, " + super.toString();
		return toStr;
	}
}
