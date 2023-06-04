package Model.Tiles;

import Controller.Controller;

/**
 * Subclass of Tile, the only difference from Tile is the action method
 * that performs the necessary actions of each round
 * @author csd4623
 * @version 1.0
 */
public class SweepTakesTile extends Tile {

	/**
	 * <b>constructor</b>: Constructs a new SweepTakesTile with the given
     * parameters orderNumber and imagePath<br>
     * <b>postcondition</b>: Creates and initializes a SweepTakesTile with the given
     * orderNumber and imagePath<br>
	 */
	public SweepTakesTile() {
		super();
		setImagePath("src/resources/images2/sweep.png");
	}
	/**
	 * <b>transformer</b>: The following actions occur:
	 * If a player moves to this tile he rolls a dice and he 
	 * receives x1000 his dice roll and in case there is a 
	 * special event (Sunday match/Thursday crypto) all the 
	 * necessary actions also take place<br>
	 * <b>postcondition</b>: All the necessary actions have been performed and
	 * the data has changed<br>
	 * @param controller the controller of the game
	 * @return 
	 */
	@Override
	public String tileAction(Controller controller) {
		if(isSunday()) controller.sundayMatch();
		if(isThursday()) controller.crypto();
		String description = controller.sweepstakes();
		return description;
	}
	
	public String toString() {
		String toStr = "Type: SweepTakesTile, " + super.toString();
		return toStr;
	}
	
}
