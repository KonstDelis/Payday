package Model;
import java.security.InvalidParameterException;
/**
 * This class contains the static methods for all the money transactions that 
 * occur between the players or between the player and the bank 
 * @author csd4623
 *
 */
public class BankTransactions {
	/**
	 * <b>transformer</b>: the player will pay the other player an amount of 
	 * money<br>
	 * <b>Precondition</b>: The players must not be null and the amount must be
	 * a positive number<br>
	 * <b>Postcondition</b>: The Player has received the money and the amount 
	 * has been subtracted from the other player<br>
	 * @param pays the player that pays the other player
	 * @param payed the player that is payed by the other player
	 * @param amount the amount of money
	 */
	public static void playerPaysPlayer(Player pays, Player payed, int amount) {
		if(amount<0) {
			throw new InvalidParameterException("amount should be >=0");
		}
		else if(pays == null) {
			throw new NullPointerException("A null player cannot give money to another player");
		}
		else if(payed == null) {
			throw new NullPointerException("A null player cannot receive money from another player");
		}
		else {
			pays.addMoney((-1)*amount);
			giveNeccessaryLoans(pays);
			payed.addMoney(amount);
		}
	}
	/**
	 * <b>transformer</b>: the player will pay the bank an amount of 
	 * money<br>
	 * <b>Precondition</b>: The player must not be null and the amount must be
	 * a positive number<br>
	 * <b>Postcondition</b>: The bank has received the money and the amount 
	 * has been subtracted from the player<br>
	 * @param p the player that pays the bank
	 * @param amount the amount of money
	 */
	public static void playerPaysBank(Player p, int amount) {
		if(amount<0) {
			throw new InvalidParameterException("amount should be >=0");
		}
		else if(p == null) {
			throw new NullPointerException("A null player cannot give money to the bank");
		}
		else {
			p.addMoney((-1)*amount);
			giveNeccessaryLoans(p);
		}
	}
	/**
	 * <b>transformer</b>: the bank will pay the player an amount of 
	 * money<br>
	 * <b>Precondition</b>: The player must not be null and the amount must be
	 * a positive number<br>
	 * <b>Postcondition</b>: The bank has received the money and the amount 
	 * has been subtracted from the player<br>
	 * @param p the player that is payed by the bank
	 * @param amount the amount of money
	 */
	public static void bankPaysPlayer(Player p, int amount) {
		if(amount<0) {
			throw new InvalidParameterException("amount should be >=0");
		}
		else if(p == null) {
			throw new NullPointerException("A null player cannot receive money from the bank");
		}
		else {
			p.addMoney(amount);
		}
	}
	
	//If a player doesn't have money this method automatically gives him money and adds the amount
	//of money he receives to his loans
	private static void giveNeccessaryLoans(Player p) {
		while(p.getMoney()<0) {
			p.addLoan(1000);
			p.addMoney(1000);
		}
	}
}
