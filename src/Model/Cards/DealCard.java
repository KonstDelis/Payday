package Model.Cards;

import java.security.InvalidParameterException;

/**
 * Implements the Card Interface and it contains all the data needed for
 * a DealCard and the methods to access and transform the data
 * @author csd4623
 *
 */
public class DealCard implements Card {
	private String[] info;
	private int cardNumber;
	/**
	 * <b>constructor</b>: Constructs a new DealCard with the given
     * parameter info and cardNumber<br>
     * <b>Precondition</b>: info is not null and contains all the necessary data for the card
     * and cardNumber is between 0 and 19<br>
     * <b>postcondition</b>: Creates and initializes a DealCard with the given
     * info (which is a String[] with the Title, type, buy price, sell price, 
     * imagePath, message and the 2 choices of the card) and cardNumber 
     * (the number of the order of the card on the board). <br>
	 * @param info String[] with the Title, type, buy price, sell price, 
     * imagePath, message and the 2 choices of the card
	 * @param cardNumber the number of the order of the card on the board
	 */
	public DealCard(String[] info, int cardNumber) {
		if(info==null) throw new NullPointerException("info cannot be null");
		if(info.length!=8) throw new InvalidParameterException("The info doesn't contain all the necessary data for the card");
		if(cardNumber>=20||cardNumber<0) throw new InvalidParameterException("card number must be between 0 and 19");
		this.info = info;
		this.cardNumber = cardNumber;
	}
	/**
	 * <b>transformer</b>: sets the String[] info of the card, which contains
	 * the title, message, type and image and is provided by the PayDayCards class<br>
	 * <b>Precondition</b>: info is not null and contains all the necessary data for the card<br>
	 * <b>Postcondition</b>: The message has changed<br>
	 * @param info 
	 */
	@Override
	public void setInfo(String[] info) {
		if(info==null) throw new NullPointerException("info cannot be null");
		if(info.length!=8) throw new InvalidParameterException("The info doesn't contain all the necessary data for the card");
		this.info = info;
	}
	/**
	 * <b>accessor</b>: returns the info of the card<br>
	 * <b>Postcondition</b>: The info has been returned<br>
	 * @return info
	 */
	@Override
	public String[] getInfo() {
		return info;
	}
	/**
	 * <b>transformer</b>: sets the number of the card, the number of the card 
	 * will basically be the number of the row of the card in the 
	 * String[][] mailCards of the PayDayCards class<br>
	 * 
	 * <b>Precondition</b>: for deal cards 0&lt;=number&lt;19 <br>
	 * 
	 * <b>Postcondition</b>: The number has changed<br>
	 * @param cardNumber
	 */
	@Override
	public void setCardNumber(int cardNumber) {
		if(cardNumber>=20||cardNumber<0) throw new InvalidParameterException("card number must be between 0 and 19");
		this.cardNumber = cardNumber;
	}
	/**
	 * <b>accessor</b>: returns the number of the card<br>
	 * <b>Postcondition</b>: The number has been returned<br>
	 * @return cardNumber
	 */
	@Override
	public int getCardNumber() {
		return cardNumber;
	}
	/**
	 * <b>accessor</b>: returns the value of the card contained in the info<br>
	 * <b>Postcondition</b>: The value has been returned<br>
	 * @return
	 */
	@Override
	public int getValue() {
		int value=-1;
		try {
			value = Integer.parseInt(info[4]);
		}
		catch(NumberFormatException e) {
			System.err.println("Info[4] cannot be parsed to an int");
		}
		return value;
	}
	public int getCost() {
		int cost=-1;
		try {
			cost = Integer.parseInt(info[3]);
		}
		catch(NumberFormatException e) {
			System.err.println("Info[3] cannot be parsed to an int");
		}
		return cost;
	}
	/**
	 * <b>accessor</b>: returns the type of the card contained in the info eg 
	 * advertisement, bill, charity, deal(only for deal cards), ...<br>
	 * <b>Postcondition</b>: The type has been returned<br>
	 * @return type
	 */
	@Override
	public String getType() {
		return "Deal";
	}
	/**
	 * returns the String representation of the Card<br>
	 * <b>Postcondition</b>: the String representation of the card has been returned<br>
	 * @return String representation
	 */
	public String toString() {
		String out = "Type: "+getType()+", Value: "+getValue()+", Cost"+getCost()+", CardNumber: "+
				getCardNumber()+", Info: '"+info[0]+", "+info[1]+", "+info[2]+", "+info[3]+", "+
				info[4]+", "+info[5]+"'";
		return out;
	}
}
