package Model.Tiles;

import Controller.Controller;
import Model.BankTransactions;

/**
 * Subclass of Tile, the only difference from Tile is the action method
 * that performs the necessary actions of each round, and there is an extra
 * member that holds how many message cards should be opened
 * @author csd4623
 * @version 1.0
 */
public class MessageTile extends Tile {
	private final int numberOfMessageCards;
	/**
	 * <b>constructor</b>: Constructs a new MessageTile with the given
     * parameter orderNumber, imagePath and numberOfMessageCards<br>
     * <b>postcondition</b>: Creates and initializes a MessageTile with the given
     * orderNumber, imagePath and numberOfMessageCards<br>
	 * @param numberOfMessageCards
	 */
	public MessageTile(int numberOfMessageCards) {
		super();
		if(numberOfMessageCards>=2) {
			this.numberOfMessageCards=2;
			setImagePath("src/resources/images2/mc2.png");
		}
		else {
			this.numberOfMessageCards=1;
			setImagePath("src/resources/images2/mc1.png");
		}
		
	}
	/**
	 * <b>transformer</b>: The following actions occur:
	 * 1 or 2 message cards are opened for the player (depends
	 * on the numberOfMessageCards), the necessary money transactions take place and
	 * in case there is a special event (Sunday match/Thursday crypto) all the 
	 * Necessary actions also take place<br>
	 * <b>postcondition</b>: All the necessary actions have been performed and
	 * the data has changed<br>
	 * @param controller the controller of the game
	 */
	@Override
	public String tileAction(Controller controller) {
		if(isSunday()) controller.sundayMatch();
		if(isThursday()) controller.crypto();
		String description = "";
		
		for(int i=0; i<numberOfMessageCards;i++) {
			String[] typeAndValue = controller.drawMailCard();
			int value = Integer.parseInt(typeAndValue[1]);

			if(typeAndValue[0].equals("Αdvertisement")) {
				controller.whoseTurn().addMoney(value);
				description+="<br>The Player drew message card that contained an advertisement "
						+ "and sold it for "+value+"$";
			}
			else if(typeAndValue[0].equals("Bill")) {
				controller.whoseTurn().addBills(value);
				description+="<br>The Player drew message card that contained bills "
						+ "worth "+value+"$, which have been added to his existing bills";
			}
			else if(typeAndValue[0].equals("Charity")) {
				controller.getJackpot().playerPaysJackpot(controller.whoseTurn(), value);
				description+="<br>The Player decided to give "+value+"$ for charity, now the "
						+ "jackpot has "+controller.getJackpot().getJackpotMoney()+"$";
			}
			else if(typeAndValue[0].equals("PayTheNeighbor")) {
				BankTransactions.playerPaysPlayer(controller.whoseTurn(), controller.whoseNotTurn(), value);
				description+="<br>The Player drew PayTheNeighbor card and payed his neighbor "+value+"$";
			}
			else if(typeAndValue[0].equals("MadMoney")) {
				BankTransactions.playerPaysPlayer(controller.whoseNotTurn(), controller.whoseTurn(), value) ;
				description+="<br>The Player drew message a MadMoney card and the oppenent payed the "
						+ "player "+value+"$";
			}
			else if(typeAndValue[0].equals("MoveToDealBuyer")) {
				String deal = controller.movePlayerToDealerBuyer(controller.whoseTurn());
				description+="<br>The Player drew message a MoveToDealerBuyer card  "
						+ "and was moved to the closest dealer/buyer tile"
						+ deal;
			}
			else {
				System.err.println("Card with type '"+typeAndValue[0]+"' cannot be recognised");
				description+="<br>Error: card type '"+typeAndValue[0]+"' not recognised";
			}
		}
		return description;
	}
	
	public String toString() {
		String toStr = "Type: MessageTile"+numberOfMessageCards+", " + super.toString();
		return toStr;
	}
}
