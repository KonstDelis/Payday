package Model.Tiles;

import Controller.Controller;
import Model.BankTransactions;
import Model.Cards.DealCard;

/**
 * Subclass of Tile, the only difference from Tile is the action method
 * that performs the necessary actions of each round
 * @author csd4623
 * @version 1.0
 */
public class DealTile extends Tile {
	/**
	 * <b>constructor</b>: Constructs a new DealTile with the given
     * parameters orderNumber, imagePath and numberOfMessageCards<br>
     * <b>postcondition</b>: Creates and initializes a DealTile with the given
     * orderNumber and imagePath<br>
	 */
	public DealTile() {
		super();
		setImagePath("src/resources/images2/deal.png");
	}
	/**
	 * <b>transformer</b>: The following actions occur:
	 * 1 Deal card is opened for the player, the necessary 
	 * money transactions take place and in case there is a 
	 * special event (Sunday match/thursday crypto) all the 
	 * necessary actions also take place<br>
	 * <b>postcondition</b>: All the necessary actions have been performed and
	 * the data has changed<br>
	 * @param controller the controller of the game
	 */
	@Override
	public String tileAction(Controller controller) {
		if(isSunday()) controller.sundayMatch();
		if(isThursday()) controller.crypto();
		DealCard card = controller.drawDealCard();
		if(card == null) return "<br>Player drew a deal card but decided to ignore it";
		controller.whoseTurn().addCard(card);
		BankTransactions.playerPaysBank(controller.whoseTurn(), card.getCost());
		return "<br>Player drew a deal card and bought it for "+card.getCost()+"$";
	}

	public String toString() {
		String toStr = "Type: DealTile, " + super.toString();
		return toStr;
	}
}
