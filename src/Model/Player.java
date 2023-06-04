package Model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import Model.Cards.*;
import Model.Tiles.*;

/**
 * Player class (implements PlayerADT) describes the characteristics of a 
 * player (name, cards he has collected, money, loans, bills) and provides 
 * modification methods 
 * @author csd4623
 * @version 1.0
 */
public class Player implements PlayerADT{
	private Tile currentTile;
	private String name;
	private ArrayList<DealCard> inventory;
	private boolean won, myTurn, moved, ongoingAction;
	private int money, bills, loans;
	/**
     * <b>constructor</b>: Constructs a new Player with the given
     * parameter name and initial money<br>
     * <b>postcondition</b>: Creates and initializes a player with the given
     * name and initial money.Also initializes some variables (for example 
     * initialize variables that give us information if a player won the game 
     * or if it is his turn to play)<br>
     * @param name is the name of the player.
     * @param initialMoney is starting money of the player
     */
	public Player(String name, int initialMoney) {
		this.name = name;
		this.money = initialMoney;
		myTurn = false;
		won = false;
		inventory = new ArrayList<DealCard>();
		currentTile = null;
	}
	/**
	 * <b>transformer</b>: sets the win status of the player
	 * <b>postcondition</b>: the win status of the player has changed
	 * @param won true for win false for haven't yet won
	 */
	public void setWinStatus(boolean won) {
		this.won = won;
	}
	/**
	 * <b>observer</b>:Returns the win status of the player
	 * <b>Postcondition</b>: returns the win status of the player
	 * @return true for win false for haven't yet won
	 */
	public boolean hasWon() {
		return won;
	}
	/**
	 * <b>transformer</b>: sets the turn status of the player
	 * <b>postcondition</b>: the turn status of the player has changed
	 * @param myTurn true for my turn false for not my turn
	 */
	public void setTurnStatus(boolean myTurn) {
		this.myTurn = myTurn;
	} 
	/**
	 * <b>observer</b>:Returns the turn status of the player
	 * <b>Postcondition</b>: returns the turn status of the player
	 * @return true for player's turn false for not player's turn
	 */
	public boolean isMyTurn() {
		return myTurn;
	}
	/**
	 * <b>transformer</b>: sets the move status of the player
	 * <b>postcondition</b>: the win status of the player has changed
	 * @param moved true if player has moved in this turn, else false
	 */
	public void setMoveStatus(boolean moved) {
		this.moved = moved;	
	}
	/**
	 * <b>observer</b>:Returns the moved status of the player
	 * <b>Postcondition</b>: returns the moved status of the player
	 * @return true if player has moved in this turn, else false
	 */
	public boolean hasMoved() {return moved;}
	/**
	 * <b>transformer</b>: sets the ongoingAction status of the player
	 * <b>postcondition</b>: the ongoingAction status of the player has changed
	 * @param ongoingAction true if player has an ongoing action (eg he has to draw a card 
	 * but hasn't done so yet) , else false
	 */
	public void setOngoingAction(boolean ongoingAction) {this.ongoingAction=ongoingAction;}
	/**
	 * <b>observer</b>:Returns the ongoingAction status of the player
	 * <b>Postcondition</b>: the ongoingAction status of the player has been returned
	 * @return true if player has an ongoing action (eg he has to draw a card 
	 * but hasn't done so yet) , else false
	 */
	public boolean ongoingAction() {return ongoingAction;}
	/**
	 * <b>accessor</b>:Returns the name of the player
	 * <b>Postcondition</b>: returns the name of the player
	 * @return name of player
	 */
	public String getName() {
		return name;
	}
	/**
	 * <b>transformer</b>: moves the player to a new tile<br>
	 * <b>precondition</b>: the tile is not null<br>
	 * <b>postcondition</b>: the currentTile of the player has changed the 
	 * tile of the parameter<br>
	 * @param tile the tile the player will move to 
	 */
	public void setCurrentTile(Tile tile) {
		if(tile == null) {
			throw new NullPointerException("Cannot set the player's tile to null");
		}
		else {
			this.currentTile = tile;
		}
	}
	/**
	 * <b>accessor</b>:Returns the currentTile of the player<br>
	 * <b>Postcondition</b>: returns currentTile of the player<br>
	 * @return currentTile of player
	 */
	public Tile getCurrentTile() {
		return currentTile;
	}
	/**
	 * <b>transformer</b>: changes the money of the player<br>
	 * <b>postcondition</b>: the money of the player has changed<br>
	 * @param addedMoney money to add (or subtract)
	 */
	public void addMoney(int addedMoney) {
		this.money += addedMoney;
	}
	/**
	 * <b>accessor</b>: Returns the money of the player<br>
	 * <b>Postcondition</b>: returns money of the player<br>
	 * @return money
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * <b>transformer</b>: changes the bills of the player<br>  
	 * <b>Postcondition</b>: the bills of the player have changed<br>
	 * @param addedBills bills to add (or subtract)
	 */
	public void addBills(int addedBills) {
		this.bills += addedBills;
	}
	/**
	 * <b>accessor</b>: Returns the bills of the player<br>
	 * <b>Postcondition</b>: returns bills of the player<br>
	 * @return bills
	 */
	public int getBills() {
		return bills;
	}
	/**
	 * <b>transformer</b>: changes the loan of the player<br>
	 * <b>Postcondition</b>: the loans of the player have changed<br>
	 * @param addedLoan loan to be added
	 */
	public void addLoan(int addedLoan) {
		if(addedLoan%1000==0) {
			this.loans += addedLoan;
		}
		else {
			throw new InvalidParameterException("Loans must be a multiple of 1000");
		}
		
	}
	/**
	 * <b>accessor</b>: Returns the loans of the player<br>
	 * <b>Postcondition</b>: returns loans of the player<br>
	 * @return loans
	 */
	public int getLoans() {
		return loans;
	}
	/**
	 * <b>transformer</b>: adds a card to the inventory of the player<br>
	 * <b>precondition</b>: the card is not null<br>
	 * <b>postcondition</b>: the card has been added<br>
	 * @param card
	 */
	public void addCard(DealCard card) {
		if(card == null) {
			throw new NullPointerException("Cannot add a null card to the player's inventory");
		}
		else {
			inventory.add(card);
		}
	}
	/**
	 * <b>accessor</b>: Returns the card inventory of the player<br>
	 * <b>Postcondition</b>: returns the card inventory of the player<br>
	 * @return cardInventory
	 */
	public ArrayList<DealCard> getCardInventory(){
		return inventory;
	}
	/**
	 * <b>accessor</b>: Calculates and returns the score of the player<br>
	 * <b>Postcondition</b>: The score of the player has been returned<br>
	 * @return
	 */
	public int calculateScore() {
		return money-bills-loans;
	}
	/**
	 * returns the String representation of the Player<br>
	 * <b>Postcondition</b>: the String representation of the Player has been returned<br>
	 * @return String representation
	 */
	public String toString() {
		String toStr = "Player with name "+name+", money: "+money+", bills: "+bills+", loans: "+loans+
				", has won: "+won+", myTurn: "+myTurn+", score: "+calculateScore();
		if(this.currentTile==null) {
			toStr+=", current Tile: player not yet on the board";
		}
		else {
			toStr+=", current Tile: "+currentTile.toString();
		}
		toStr+=", cards in inventory:\n";
		for(DealCard card: inventory) {
			toStr+= card.toString()+"\n";
		}
		return toStr;}
}
