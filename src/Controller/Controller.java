package Controller;
import Model.*;
import Model.Cards.*;
import Model.Tiles.BuyerTile;
import Model.Tiles.DealTile;
import View.CardInventoryUI;
import View.GraphicUI;
import View.Pawn;
import View.PayDayCards;

import java.awt.event.*;
import java.util.ArrayList;
/**
 * Controller is the master of the game and controls all 
 * of the operations executed
 * @author csd4623
 * @version 1.0
 */
public class Controller {
	private Player player1, player2;
	private Board board;
	private CardDeck dealDeck, messageDeck, dealRemoved, messageRemoved;
	private Boolean gameFinished;
	private int player1month, player2month, gameMonths;
	private Dice player1dice, player2dice;
	private Jackpot jackpot;
	private GraphicUI gui;
	/**
	 * <b>constructor</b>: Constructs a new Controller and sets the game as 
	 * eligible to start .<br>
	 * <b>postcondition</b>: constructs a new Controller,with 2 new players
	 * new board, 4 new card decks, the graphicalUI contructor is called and initialize some boolean variables
	 * some int or boolean variables.So,is responsible for creating a new game and 
	 * initializing it.
	 */
	public Controller() {
		player1 = new Player("Player1", 3500);
		player1.setTurnStatus(true);
		player2 = new Player("Player2", 3500);
		board = new Board();
		player1.setCurrentTile(board.getTile(0));
		player2.setCurrentTile(board.getTile(0));
		
		String[][] messageCardsInfo = new PayDayCards().getMailCards();
		String[][] dealCardsInfo = new PayDayCards().getDealCards();
		ArrayList<Card> mailCards = new ArrayList<Card>();
		ArrayList<Card> dealCards = new ArrayList<Card>();
		for(int i=0; i<48; i++) {
			mailCards.add(new MessageCard(messageCardsInfo[i], i));
		}
		for(int i=0; i<20; i++) {
			dealCards.add(new DealCard(dealCardsInfo[i], i));
		}
		messageDeck = new CardDeck(mailCards,DeckType.MESSAGE);
		messageDeck.shuffleDeck();
		dealDeck = new CardDeck(dealCards,DeckType.DEAL);
		dealDeck.shuffleDeck();
		messageRemoved = new CardDeck(DeckType.MESSAGE);
		dealRemoved = new CardDeck(DeckType.DEAL);
		
		player1month = 0;
		player2month = 0;
		gameMonths = GraphicUI.startMenuPopup();
		gameFinished = false;
		jackpot = new Jackpot();
		player1dice = new Dice(player1);
		player2dice = new Dice(player2);
		
		gui = new GraphicUI(board, player1, player2, player1dice, player2dice);
		gui.getP1rollDice().addActionListener(new DiceRollListener());	
		gui.getP2rollDice().addActionListener(new DiceRollListener());
		gui.getP1endTurn().addActionListener(new EndTurnListener());
		gui.getP2endTurn().addActionListener(new EndTurnListener());
		gui.getP1getloan().addActionListener(new GetLoanListener());
		gui.getP2getloan().addActionListener(new GetLoanListener());
		gui.getP1cards().addActionListener(new GetCardsListener());
		gui.getP2cards().addActionListener(new GetCardsListener());
	}
	/**
	 * <b>Transformer</b>: Draws a mail card  from the deck, calls the gui to show the card that was drawn to the players and then
	 * returns its type and value<br>
	 * <b>postcondition</b>: The mail card has been drawn, shown to the players, has been added to the removed mail cards deck, and
	 * its type and value has been returned<br>
	 * @return type and value of the card
	 */
	public String[] drawMailCard() {
		if(messageDeck.isEmpty()) refillMail();
		Card card = messageDeck.getTopCard();
		new PayDayCards().showMailCard(card.getCardNumber());
		String[] typeAndValue = new String[2];
		typeAndValue[0] = card.getType();
		typeAndValue[1] = Integer.toString(card.getValue());
		messageRemoved.addCardToBottom(card);
		return typeAndValue;
	}
	/**
	 * <b>Transformer</b>: Draws a deal card  from the deck, calls the gui to show the card that was drawn to the players and then
	 * if the player decides to buy it the card is returned, else it is added to the removed deal cards deck and null is returned<br>
	 * <b>postcondition</b>: The deal card has been drawn, shown to the players and the player decides what to do with the card. 
	 * According to the choice either null or the dealCard is returned<br>
	 * its type and value has been returned
	 * @return null or the dealCard
	 */
	public DealCard drawDealCard() {
		if (dealDeck.isEmpty()) refillDeal();
		DealCard card = (DealCard) dealDeck.getTopCard();
		int selection = new PayDayCards().showDealCard(card.getCardNumber());
		if(selection==0) 
			return card;
		else if(selection==1) {
			dealRemoved.addCardToBottom(card);
			return null;
		}
		
		else 
			throw new RuntimeException("Unexpected Error: Nothing was selected");
	}
	/**
	 * <b>Transformer</b>: The player is moved to the nearest dealer or buyer tile, and the gui is called to move the pawn<br>
	 * <b>postcondition</b>: The has been moved to the nearest dealer or buyer tile, and the position of the pawn in the gui
	 * board has changed accordingly<br>
	 * @param p the player that will be moved
	 * @return A string description of what has happened
	 */
	public String movePlayerToDealerBuyer(Player p) {
		Pawn pawn;
		if(p.getCurrentTile() instanceof BuyerTile || p.getCurrentTile() instanceof DealTile) return "Player is already on a dealer/buyer tile";
		else if(p==player1)pawn=Pawn.p1;
		else if(p==player2)pawn=Pawn.p2;
		else throw new NullPointerException("Cannot move a player that doesn't exist to a dealer/buyer tile");
		p.setCurrentTile(board.getClosestDealBuyer(p.getCurrentTile()));
		gui.movePawnToTile(pawn, p.getCurrentTile());
		String description = p.getCurrentTile().tileAction(this);
		return description;
	}
	/**
	 * <b>Transformer</b>: The sweepstakes gui popup window is called and the money that the player wins is added to his money<br>
	 * <b>postcondition</b>: The sweepstakesPopUp of the gui has been called and the money has been added to the player<br>
	 * @return A string description of what has happened
	 */
	public String sweepstakes() {
		int roll = gui.sweepstakesPopUp(new Dice());
		BankTransactions.bankPaysPlayer(whoseTurn(), roll*1000);
		return "<br>The player landed on a sweepstakes and won "+roll*1000+"$";
	}
	/**
	 * <b>Transformer</b>: The lottery gui popup window is called and the money of whoever wins is added to his money<br>
	 * <b>postcondition</b>: The lotteryPopUp of the gui has been called and the money has been added to the winner<br>
	 * @return A string description of what has happened
	 */
	public String lottery() {
		Player winner = gui.lotteryPopUp(whoseTurn(), whoseNotTurn(), new Dice());
		BankTransactions.bankPaysPlayer(winner, 1000);
		return "<br>The player landed on a lottery tile and "+winner.getName()+" won 1000$ from the bank";
	}
	/**
	 * <b>Transformer</b>: The radio gui popup window is called and the money of whoever wins is added to his money<br>
	 * <b>postcondition</b>: The radioPopUp of the gui has been called and the money has been added to the winner<br>
	 * @return A string description of what has happened
	 */
	public String radio() {
		Player winner = gui.radioPopUp(whoseTurn(), whoseNotTurn(), new Dice());
		BankTransactions.bankPaysPlayer(winner, 1000);
		return "<br>The player landed on a radio tile and "+winner.getName()+" won 1000$ from the bank";
	}
	/**
	 * <b>Transformer</b>: If the player has no deal cards nothing happens, else the buyerMode of the CardInventoryUI call is
	 * called where the player selects one of his deal cards to sell<br>
	 * <b>postcondition</b>: The buyerMode of the CardInventoryUI has been called, or nothing has happened (depends if the player has
	 * deal cards or not)<br>
	 * @return A string description of what has happened
	 */
	public String buyer() {
		if(whoseTurn().getCardInventory().isEmpty()) {
			return "<br>The player landed on a buyer tile but had no deal cards to sell";
		}
		else {
			whoseTurn().setOngoingAction(true);
			new CardInventoryUI(whoseTurn().getCardInventory()).buyerMode(this);
			return "<br>The player landed on a buyer tile and we are waiting for him to pick a card";
		}
	}
	/**
	 * <b>Transformer</b>: Is called by the CardInventoryUI after a player has selected a deal card to sell, the player is given 
	 * his money and the sold deal card is added to the removed deal cards deck. The player data of the gui is refreshed and the
	 * text of the output panel of the gui is changed to contain the most recent info<br>
	 * <b>postcondition</b>: The player has been given his money, the deal card has been removed from the player and added to the
	 * removed deal cards deck, the players' data of the gui has been refreshed and the text of the ouput panel of gui has been 
	 * changed <br>
	 */
	public void buyerCardSelected(DealCard selected) {
		whoseTurn().setOngoingAction(false);
		int index = whoseTurn().getCardInventory().indexOf(selected);
		if (index<0||whoseTurn().getCardInventory().size()-1<index) throw new IndexOutOfBoundsException("The deal card was not found in the player's inventory");
		dealRemoved.addCardToBottom(whoseTurn().getCardInventory().remove(index));
		BankTransactions.bankPaysPlayer(whoseTurn(), selected.getValue());
		gui.refreshPlayerData(player1, player2, jackpot);
		gui.changeOutputPanelMessage("<html>The player just sold a deal card for "
				+selected.getValue()+"$<br> >>>"+whoseTurn().getName()+"'s turn to play</html>");
	}
	/**
	 * <b>Transformer</b>: The family gui popup window is called and the is either given or taken from the player, depending on his
	 * previous dice roll<br>
	 * <b>postcondition</b>: The familyPopUp of the gui has been called and the money has been added or subtracted from the player<br>
	 * @return A string description of what has happened
	 */
	public String family() {
		Dice dice;
		String description;
		if(whoseTurn()==player1) dice = player1dice;
		else dice = player2dice;
		gui.familyPopUp(dice.getPreviousRoll());
		if(dice.getPreviousRoll()%2==0) {
			jackpot.playerPaysJackpot(whoseTurn(), 500);
			description = "<br>The player landed on a family casino night with a "+dice.getPreviousRoll()
			+" roll, so he payed the jackpot 500$";
		}
		else {
			BankTransactions.bankPaysPlayer(whoseTurn(), 500);
			description = "<br>The player landed on a family casino night with a "+dice.getPreviousRoll()
			+" roll, so the bank gave him 500$";
		}
		gui.refreshPlayerData(player1, player2, jackpot);
		return description;
	}
	/**
	 * <b>Transformer</b>: The yard gui popup window is called, the player pays the amount required and a deal card is given to him<br>
	 * <b>postcondition</b>: The yardPopUp of the gui has been called, the money has been subtracted from the player and a deal card
	 * has been added to his inventory<br>
	 * @return A string description of what has happened
	 */
	public String yardSale() {
		String description = "<br>Player landed on a yard sale tile. He payed ";
		int roll = gui.yardPopUp(new Dice());
		description+=roll*100+" and got a deal card";
		BankTransactions.playerPaysBank(whoseTurn(), roll*100);
		whoseTurn().addCard((DealCard)dealDeck.getTopCard());
		gui.refreshPlayerData(player1, player2, jackpot);
		return description;
	}
	/**
	 * <b>Transformer</b>: The payday gui popup window is called, the player receives 3500$ as a salary, he pays his bills and loan interest,
	 * he can pay off his loans or part of them and all his cards are removed. If it he completed his last month his win status
	 * is set to true and the player stays at the payday tile for the rest of the game, else he is moved back to the start<br>
	 * <b>postcondition</b>: The payday gui popup window has been called, the player has received 3500$ as a salary, he has payed his bills and loan interest,
	 * he has payed off his loans or part of them and all his cards have been removed. If it he has completed his last month his win status
	 * has been set to true and the player stays at the payday tile for the rest of the game, else he has been moved back to the start<br>
	 * @return A string description of what has happened
	 */
	public String payday() {
		BankTransactions.bankPaysPlayer(whoseTurn(), 3500);
		BankTransactions.playerPaysBank(whoseTurn(), whoseTurn().getBills());
		BankTransactions.playerPaysBank(whoseTurn(), whoseTurn().getLoans()/10);
		int payedLoans = gui.paydayPopUp(whoseTurn());
		whoseTurn().addBills((-1)*whoseTurn().getBills());
		BankTransactions.playerPaysBank(whoseTurn(), payedLoans);
		whoseTurn().addLoan(payedLoans*(-1));
		whoseTurn().setCurrentTile(board.getTile(0));
		ArrayList<DealCard> pInventory = whoseTurn().getCardInventory();
		while(pInventory.isEmpty()==false) dealRemoved.addCardToBottom(pInventory.remove(0));
		int monthNumber;
		if(whoseTurn()==player1) {
			player1month++;
			monthNumber = player1month;
			if(player1month==gameMonths) {
				player1.setWinStatus(true);
				return "<br>Player reached the payday tile and completed month number "
						+monthNumber+", which means that he has completed the game";
			}
			gui.movePawnToTile(Pawn.p1, player1.getCurrentTile());
		}
		else {
			player2month++;
			monthNumber = player2month;
			if(player2month==gameMonths) {
				player2.setWinStatus(true);
				return "<br>Player reached the payday tile and completed month number "
						+monthNumber+", which means that he has completed the game";
			}
			gui.movePawnToTile(Pawn.p2, player2.getCurrentTile());
		}
		gui.refreshPlayerData(player1, player2, jackpot);
		return "<br>Player reached the payday tile and completed month number "+monthNumber;
	}
	/**
	 * <b>Transformer</b>: The sunday match gui popup window is called and if the player plays and wins money is added, if he loses
	 * money is subtracted and if he doesn't play nothing happens<br>
	 * <b>postcondition</b>: The sundayPopUp of the gui has been called and the money has been added or subtracted from the player<br>
	 */
	public void sundayMatch() {
		int result = gui.sundayPopUp(new Dice());
		if(result==1) BankTransactions.bankPaysPlayer(whoseTurn(), 500);
		if(result==2) BankTransactions.playerPaysBank(whoseTurn(), 500);
		gui.refreshPlayerData(player1, player2, jackpot);
	}
	/**
	 * <b>Transformer</b>: The Thursday crypto gui popup window is called and if the player plays and wins money is added, if he loses
	 * money is subtracted and if he doesn't play or the price remains the same nothing happens<br>
	 * <b>postcondition</b>: The cryptoPopUp of the gui has been called and the money has been added or subtracted from the player,
	 * or nothing has happened if he decides not to buy crypto or the price remains the same<br>
	 */
	public void crypto() {
		int result = gui.cryptoPopUp(new Dice());
		if(result==1) BankTransactions.bankPaysPlayer(whoseTurn(), 300);
		if(result==2) BankTransactions.playerPaysBank(whoseTurn(), 300);
		gui.refreshPlayerData(player1, player2, jackpot);
	}
	/**
	 * <b>accessor</b>: Returns the player whose turn is to play or null if
	 * it is no one's turn (game has finished)<br>
	 * <b>Postcondition</b>: One of the two players or null has been returned<br>
	 * @return
	 */
	public Player whoseTurn() {
		if(player1.isMyTurn()) {
			return player1;
		}
		else if(player2.isMyTurn()) {
			return player2;
		}
		else {
			return null;
		}
	}
	/**
	 * <b>accessor</b>: Returns the player whose turn is not to play<br>
	 * <b>Precondition</b>: It is only one of the players' turn to play
	 * <b>Postcondition</b>: One of the two players or null has been returned<br>
	 * @return
	 */
	public Player whoseNotTurn() {
		if(player1.isMyTurn()==false) {
			return player1;
		}
		else if(player2.isMyTurn()==false) {
			return player2;
		}
		else {
			throw new RuntimeException("Error: it is both players' turn");
		}
	}
	/**
	 * <b>accessor</b>: calculates the score a player and returns it<br>
	 * <b>Precondition</b>: Player must not be null<br>
	 * <b>Postcondition</b>: Score of the player has been returned<br>
	 * @param p the player whose score will be returned
	 */
	public int calculateScoreOfPlayer(Player p) {return p.getMoney()-p.getBills()-p.getLoans();}
	/**
	 * <b>Observer</b>:Return true if a game(both players completed all months) 
	 * has finished, false otherwise<br>
	 * <b>Postcondition:</b> return true if a game(both players completed all
	 *  months)  has finished, false otherwise<br>
	 * @return true if a game(both players completed all months) has finished, 
	 * false otherwise
	 */
	public Boolean getGameFinished() {
		return gameFinished;
	}
	/**
	 * <b>accessor</b>: returns the jackpot<br>
	 * <b>Postcondition</b>: The jackpot has been returned<br>
	 * @return
	 */
	public Jackpot getJackpot() {return jackpot;}
	//Refills the message deck using the removed message deck
	private void refillMail() {
		if(messageRemoved.isEmpty()) throw new IndexOutOfBoundsException("The removed message "
				+ "card deck is empty, cannot refill message deck");
		else {
			while(messageRemoved.isEmpty()==false) {
				messageDeck.addCardToBottom(messageRemoved.getTopCard());
			}
			messageDeck.shuffleDeck();
		}
	}
	//Refills the deal deck using the removed deal deck
	private void refillDeal() {
		if(dealRemoved.isEmpty()) throw new IndexOutOfBoundsException("The removed deal "
				+ "card deck is empty, cannot refill deal deck");
		else {
			while(dealRemoved.isEmpty()==false) {
				dealDeck.addCardToBottom(dealRemoved.getTopCard());
			}
			dealDeck.shuffleDeck();
		}
	}
	
	/**
	 * The action listener of the "Roll Dice" buttons of the graphical user interface
	 * @author delis
	 */
	public class DiceRollListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int startingLoan = whoseTurn().getLoans();
			if(e.getSource()==gui.getP1rollDice() && gui.getP1rollDice()!=null && 
					whoseTurn()==player1 && player1.hasMoved() == false) {
				player1dice.rollDice();
				int tileNo = player1.getCurrentTile().getOrderNumber();
				int nextTile;
				if(tileNo+player1dice.getPreviousRoll()<31)
					nextTile = tileNo+player1dice.getPreviousRoll();
				else 
					nextTile = 31;
				player1.setCurrentTile(board.getTile(nextTile));
				gui.changeDiceImage(Pawn.p1, player1dice);
				gui.movePawnToTile(Pawn.p1, player1.getCurrentTile());
				player1.setMoveStatus(true);
				if(player1dice.getPreviousRoll()==6) {
					gui.wonJackpot(jackpot.getJackpotMoney());
					jackpot.wonJackpot(player1);
				}
				String description = player1.getCurrentTile().tileAction(Controller.this);
				gui.changeOutputPanelMessage("<html>Player1 rolled a "+player1dice.getPreviousRoll()+
						description+ "<br> >>>Player1's turn to play</html>");
			}
			if(e.getSource()==gui.getP2rollDice() && gui.getP2rollDice()!=null && 
					whoseTurn()==player2 && player2.hasMoved()==false) {
				player2dice.rollDice();
				int tileNo = player2.getCurrentTile().getOrderNumber();
				int nextTile;
				if(tileNo+player2dice.getPreviousRoll()<31)
					nextTile = tileNo+player2dice.getPreviousRoll();
				else 
					nextTile = 31;
				player2.setCurrentTile(board.getTile(nextTile));
				gui.movePawnToTile(Pawn.p2, player2.getCurrentTile());
				player2.setMoveStatus(true);
				gui.changeDiceImage(Pawn.p2, player2dice);
				if(player2dice.getPreviousRoll()==6) {
					gui.wonJackpot(jackpot.getJackpotMoney());
					jackpot.wonJackpot(player2);
				}
				String description = player2.getCurrentTile().tileAction(Controller.this);
				gui.changeOutputPanelMessage("<html>Player2 rolled a "+player2dice.getPreviousRoll()+
						description + "<br> >>>Player2's turn to play</html>");
			}
			gui.refreshPlayerData(player1, player2, jackpot);
			int endLoan = whoseTurn().getLoans();
			if(startingLoan<endLoan) gui.loanPopUp(endLoan-startingLoan);
		}
		
	}
	/**
	 * The action listener of the "End Turn" buttons of the graphical user interface
	 * @author delis
	 */
	public class EndTurnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(whoseTurn().ongoingAction()) {
				gui.changeOutputPanelMessage("<html>"+whoseTurn().getName()+" has an ongoing action<br>"
						+ "Unable to complete any other actions at the moment</html>");
			}
			else if(e.getSource()==gui.getP1endTurn() && gui.getP1endTurn()!=null && 
					whoseTurn()==player1 && player1.hasMoved() == true && player2.hasWon()==false) {
				player1.setMoveStatus(false);
				player1.setTurnStatus(false);
				player2.setTurnStatus(true);
				gui.changeOutputPanelMessage("<html>Player1 has completed his turn<br>"
						+ " >>>Player2's turn to play</html>");
			}
			else if(e.getSource()==gui.getP1endTurn() && gui.getP1endTurn()!=null && whoseTurn()==player1 
					&& player1.hasMoved() == true && player2.hasWon()==true && player1.hasWon()==false) {
				player1.setMoveStatus(false);
				player1.setTurnStatus(true);
				gui.changeOutputPanelMessage("<html>Player1 has completed his turn<br>"
						+ "Player 2 has completed the game, so Player 1 plays again<br>"
						+ " >>>Player1's turn to play</html>");
			}
			else if(e.getSource()==gui.getP1endTurn() && gui.getP1endTurn()!=null && whoseTurn()==player1 
					&& player1.hasMoved() == true && player2.hasWon()==true && player1.hasWon()==true) {
				gui.changeOutputPanelMessage("<html>Game completed!!!</html>");
				gui.winPopUp(calculateScoreOfPlayer(player1), calculateScoreOfPlayer(player2));
			}
			else if(e.getSource()==gui.getP1endTurn() && gui.getP1endTurn()!=null && 
					whoseTurn()==player1 && player1.hasMoved() == false) {
				gui.changeOutputPanelMessage("<html>Player1 must roll the dice before ending his turn<br>"
						+ " >>>Player1's turn to play</html>");
			}
			else if(e.getSource()==gui.getP2endTurn() && gui.getP2endTurn()!=null && whoseTurn()==player2 
					&& player2.hasMoved() == true && player1.hasWon()==false) {
				player2.setMoveStatus(false);
				player2.setTurnStatus(false);
				player1.setTurnStatus(true);
				gui.changeOutputPanelMessage("<html>Player2 has completed his turn<br>"
						+ " >>>Player1's turn to play</html>");
			}
			else if(e.getSource()==gui.getP2endTurn() && gui.getP2endTurn()!=null && whoseTurn()==player2 
					&& player2.hasMoved() == true && player1.hasWon()==true && player2.hasWon()==false) {
				player2.setMoveStatus(false);
				player2.setTurnStatus(true);
				gui.changeOutputPanelMessage("<html>Player2 has completed his turn<br>"
						+ "Player 1 has completed the game, so Player 2 plays again<br>"
						+ " >>>Player2's turn to play</html>");
			}
			else if(e.getSource()==gui.getP2endTurn() && gui.getP2endTurn()!=null && whoseTurn()==player2 
					&& player2.hasMoved() == true && player1.hasWon()==true && player2.hasWon()==true) {
				gui.changeOutputPanelMessage("<html>Game completed!!!</html>");
				gui.winPopUp(calculateScoreOfPlayer(player1), calculateScoreOfPlayer(player2));
			}
			else if(e.getSource()==gui.getP2endTurn() && gui.getP2endTurn()!=null && 
					whoseTurn()==player2 && player2.hasMoved() == false) {
				gui.changeOutputPanelMessage("<html>Player2 must roll the dice before ending his turn<br>"
						+ " >>>Player2's turn to play</html>");
			}
			
		}
		
	}
	/**
	 * The action listener of the "Get Loan" buttons of the graphical user interface
	 * @author delis
	 */
	public class GetLoanListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==gui.getP1getloan() && gui.getP1getloan()!=null && 
					whoseTurn()==player1) {
				player1.addLoan(1000);
				BankTransactions.bankPaysPlayer(player1, 1000);
				gui.refreshPlayerData(player1, player2, jackpot);
				gui.changeOutputPanelMessage("<html>Player1 has taken o loan of 1000$ and now has "
						+ player1.getLoans()+"$ of loans<br> >>>Player1's turn to play</html>");
			}
			else if(e.getSource()==gui.getP2getloan() && gui.getP2getloan()!=null && 
					whoseTurn()==player2) {
				player2.addLoan(1000);
				BankTransactions.bankPaysPlayer(player2, 1000);
				gui.refreshPlayerData(player1, player2, jackpot);
				gui.changeOutputPanelMessage("<html>Player2 has taken o loan of 1000$ and now has "
						+ player2.getLoans()+"$ of loans<br> >>>Player2's turn to play</html>");			}
			
		}
		
	}
	/**
	 * The action listener of the "My Deal Cards" buttons of the graphical user interface
	 * @author delis
	 */
	public class GetCardsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==gui.getP1cards() && gui.getP1cards()!=null && 
					whoseTurn()==player1) {
				new CardInventoryUI(player1.getCardInventory()).viewer();;
				gui.changeOutputPanelMessage("<html>Player1 has opened his inventory<br> >>>Player1's turn to play</html>");
			}
			else if(e.getSource()==gui.getP2cards() && gui.getP2cards()!=null && 
					whoseTurn()==player2) {
				new CardInventoryUI(player2.getCardInventory()).viewer();
				gui.changeOutputPanelMessage("<html>Player2 has opened his inventory<br> >>>Player2's turn to play</html>");			
				}
		}
		
	}
}
