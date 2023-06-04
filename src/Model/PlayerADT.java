package Model;

import java.util.ArrayList;

import Model.Cards.DealCard;
import Model.Tiles.Tile;
/**
 * The interface of a Player with the declaration of the methods that set, get
 * and modify the attributes of the Player
 * @author delis
 *
 */
public interface PlayerADT {
	/**
	 * <b>transformer</b>: sets the win status of the player
	 * <b>postcondition</b>: the win status of the player has changed
	 * @param won true for win false for haven't yet won
	 */
	public void setWinStatus(boolean won);
	/**
	 * <b>observer</b>:Returns the win status of the player
	 * <b>Postcondition</b>: returns the win status of the player
	 * @return true for win false for haven't yet won
	 */
	public boolean hasWon();
	/**
	 * <b>transformer</b>: sets the turn status of the player
	 * <b>postcondition</b>: the turn status of the player has changed
	 * @param myTurn true for my turn false for not my turn
	 */
	public void setTurnStatus(boolean myTurn); 
	/**
	 * <b>observer</b>:Returns the turn status of the player
	 * <b>Postcondition</b>: returns the turn status of the player
	 * @return true for player's turn false for not player's turn
	 */
	public boolean isMyTurn();
	/**
	 * <b>accessor</b>:Returns the name of the player
	 * <b>Postcondition</b>: returns the name of the player
	 * @return name of player
	 */
	public String getName();
	/**
	 * <b>transformer</b>: sets the move status of the player
	 * <b>postcondition</b>: the win status of the player has changed
	 * @param moved true if player has moved in this turn, else false
	 */
	public void setMoveStatus(boolean moved);
	/**
	 * <b>observer</b>:Returns the moved status of the player
	 * <b>Postcondition</b>: returns the moved status of the player
	 * @return true if player has moved in this turn, else false
	 */
	public boolean hasMoved();
	/**
	 * <b>transformer</b>: sets the ongoingAction status of the player
	 * <b>postcondition</b>: the ongoingAction status of the player has changed
	 * @param ongoingAction true if player has an ongoing action (eg he has to draw a card 
	 * but hasn't done so yet) , else false
	 */
	public void setOngoingAction(boolean ongoingAction);
	/**
	 * <b>observer</b>:Returns the ongoingAction status of the player
	 * <b>Postcondition</b>: the ongoingAction status of the player has been returned
	 * @return true if player has an ongoing action (eg he has to draw a card 
	 * but hasn't done so yet) , else false
	 */
	public boolean ongoingAction();
	/**
	 * <b>transformer</b>: moves the player to a new tile<br>
	 * <b>precondition</b>: the tile is not null<br>
	 * <b>postcondition</b>: the currentTile of the player has changed the 
	 * tile of the parameter<br>
	 * @param tile the tile the player will move to 
	 */
	public void setCurrentTile(Tile tile);
	/**
	 * <b>accessor</b>:Returns the currentTile of the player<br>
	 * <b>Postcondition</b>: returns currentTile of the player<br>
	 * @return currentTile of player
	 */
	public Tile getCurrentTile();
	/**
	 * <b>transformer</b>: changes the money of the player<br>
	 * <b>postcondition</b>: the money of the player has changed<br>
	 * @param addedMoney money to add (or substract)
	 */
	public void addMoney(int addedMoney);
	/**
	 * <b>accessor</b>: Returns the money of the player<br>
	 * <b>Postcondition</b>: returns money of the player<br>
	 * @return money
	 */
	public int getMoney();
	/**
	 * <b>transformer</b>: changes the bills of the player<br>
	 * <b>postcondition</b>: the bills of the player have changed<br>
	 * @param addedBills bills to add (or substract)
	 */
	public void addBills(int addedBills);
	/**
	 * <b>accessor</b>: Returns the bills of the player<br>
	 * <b>Postcondition</b>: returns bills of the player<br>
	 * @return bills
	 */
	public int getBills();
	/**
	 * <b>transformer</b>: changes the loan of the player<br>
	 * <b>postcondition</b>: the loans of the player have changed<br>
	 * @param addedLoan loan to be added
	 */
	public void addLoan(int addedLoan);
	/**
	 * <b>accessor</b>: Returns the loans of the player<br>
	 * <b>Postcondition</b>: returns loans of the player<br>
	 * @return loans
	 */
	public int getLoans();
	/**
	 * <b>transformer</b>: adds a card to the inventory of the player<br>
	 * <b>precondition</b>: the card is not null<br>
	 * <b>postcondition</b>: the card has been added<br>
	 * @param card
	 */
	public void addCard(DealCard card);
	/**
	 * <b>accessor</b>: Returns the card inventory of the player<br>
	 * <b>Postcondition</b>: returns the card inventory of the player<br>
	 * @return cardInventory
	 */
	public ArrayList<DealCard> getCardInventory();
	/**
	 * <b>accessor</b>: Calculates and returns the score of the player<br>
	 * <b>Postcondition</b>: The score of the player has been returned<br>
	 * @return
	 */
	public int calculateScore();
	/**
	 * returns the String representation of the Player<br>
	 * <b>Postcondition</b>: the String representation of the Player has been returned<br>
	 * @return String representation
	 */
	public String toString();
}
