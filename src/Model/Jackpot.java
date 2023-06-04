package Model;

import java.security.InvalidParameterException;

/**
 * Jackpot contains the money that has been collected from the players and
 * when a player wins the jackpot he receives the collected money
 * @author csd4623
 *
 */
public class Jackpot {
	private int jackpotMoney;
	/**
	 * <b>constructor</b>: Constructs a new Jackpot<br>
     * <b>postcondition</b>: Creates and initializes a Jackpot and sets its 
     * initial money to 0<br>
	 */
	public Jackpot() {
		jackpotMoney = 0;
	}
	/**
	 * <b>transformer</b>: the player that won will receive the current money of
	 * the jackpot and the jackpot's money will be set to 0<br>
	 * <b>Precondition</b>: The player must not be null<br>
	 * <b>Postcondition</b>: The player has received the money and the jackpot 
	 * money will be set to zero<br>
	 * @param p the player that wins the money
	 */
	public void wonJackpot(Player p) {
		if(p == null) {
			throw new NullPointerException("Null player cannot win jackpot");
		}
		else {
			p.addMoney(jackpotMoney);
			jackpotMoney = 0;
		}
	}
	/**
	 * <b>transformer</b>: the player will pay the jackpot an amount of money<br>
	 * <b>Precondition</b>: The player must not be null and the amount must be
	 * a positive number<br>
	 * <b>Postcondition</b>: The jackpot has received the money and the amount 
	 * has been subtracted from the player<br>
	 * @param p the player that will pay the amount
	 * @param amount the amount of money that the player has to play
	 */
	public void playerPaysJackpot(Player p, int amount) {
		if(p==null) {
			throw new NullPointerException("Null player cannot win jackpot");
		}
		else if(amount<0) {
			throw new InvalidParameterException("The player must give a positive amount of money"
					+ " to the jackpot");
		}
		else {
			p.addMoney(-1*amount);
			giveNeccessaryLoans(p);
			jackpotMoney += amount;
		}
		
	}
	/**
	 * <b>accessor</b>: returns the info of the card<br>
	 * <b>Postcondition</b>: The info has been returned<br>
	 * @return jackpotMoney
	 */
	public int getJackpotMoney() {return jackpotMoney;}
	
	//If a player doesn't have money this method automatically gives him money and adds the amount
	//of money he receives to his loans
	private static void giveNeccessaryLoans(Player p) {
		while(p.getMoney()<0) {
			p.addLoan(1000);
			p.addMoney(1000);
		}
	}
}
