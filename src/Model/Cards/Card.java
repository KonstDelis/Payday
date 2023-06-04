package Model.Cards;
/**
 * The interface for all cards, contains methods to get and set the 
 * message of a card, its value, its picture and its title
 * @author csd4623
 * @version 1.0
 */
public interface Card {
	/**
	 * <b>transformer</b>: sets the String[] info of the card, which contains
	 * the title, message, type and image and is provided by the PayDayCards class<br>
	 * <b>Postcondition</b>: The message has changed<br>
	 * @param info 
	 */
	void setInfo(String[] info);
	/**
	 * <b>accessor</b>: returns the info of the card<br>
	 * <b>Postcondition</b>: The info has been returned<br>
	 * @return info
	 */
	String[] getInfo();
	/**
	 * <b>transformer</b>: sets the number of the card, the number of the card 
	 * will basically be the number of the row of the card in the 
	 * String[][] mailCards of the PayDayCards class<br>
	 * 
	 * <b>Precondition</b>: for mail Cards 0&lt;=number&lt;=47 and for deal cards
	 * 0&lt;=number&lt;19 <br>
	 * 
	 * <b>Postcondition</b>: The number has changed<br>
	 * @param cardNumber
	 */
	void setCardNumber(int cardNumber);
	/**
	 * <b>accessor</b>: returns the number of the card<br>
	 * <b>Postcondition</b>: The number has been returned<br>
	 * @return cardNumber
	 */
	int getCardNumber();
	/**
	 * <b>accessor</b>: returns the value of the card contained in the info<br>
	 * <b>Postcondition</b>: The value has been returned<br>
	 * @return
	 */
	int getValue();
	/**
	 * <b>accessor</b>: returns the type of the card contained in the info eg 
	 * advertisement, bill, charity, deal(only for deal cards), ...<br>
	 * <b>Postcondition</b>: The type has been returned<br>
	 * @return type
	 */
	String getType();
	/**
	 * returns the String representation of the Card<br>
	 * <b>Postcondition</b>: the String representation of the card has been returned<br>
	 * @return String representation
	 */
	String toString();
}
