package Model.Cards;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;
/**
 * This class is a deck of cards (basically and ArrayList of Card classes) and
 * methods are included to get the top card, to add a card at the bottom of the
 * deck, shuffle the deck, etc.
 * @author csd4623
 * @version 1.0
 */
public class CardDeck {
	private ArrayList<Card> deck;
	private int maxCapacity;
	/**
	 * <b>Constructor</b>: Constructs a new CardDeck and sets the type of the 
	 * card deck. If type is MESSAGE sets maxCapacity to 48, else if type is 
	 * DEAL sets maxCapacity to 20<br>
	 * <b>Precondition:</b>: type is not null
	 * <b>Postcondition</b>: The object has been constructed and initialized<br>
	 * @param type either DEAL or MESSAGE
	 */
	public CardDeck(DeckType type) {
		deck = new ArrayList<Card>();
		if(type == DeckType.MESSAGE) {
			maxCapacity = 48;
		}
		else if(type == DeckType.DEAL){
			maxCapacity = 20;
		}
		else {
			throw new NullPointerException("Type of card deck cannot be null");
		}
	}
	/**
	 * <b>Constructor</b>: Constructs a new CardDeck and sets the type of the 
	 * card deck. If type is MESSAGE sets maxCapacity to 48, else if type is 
	 * DEAL sets maxCapacity to 20. Also, the elements of the parameter deck are
	 * copied to the ArrayList of the class<br>
	 * 
	 * <b>Precondition</b>: The parameter deck.size&lt;=maxCapacity, deck is not null (and none of
	 * its cards are null) and type is not null<br>
	 * 
	 * <b>Postcondition</b>: The object has been constructed and initialized <br>
	 * @param deck that will be copied to the class' ArrayList
	 * @param type either DEAL or MESSAGE
	 */
	public CardDeck(ArrayList<Card> deck, DeckType type) {
		if(deck == null) throw new NullPointerException("Cannot initialize a deck with a null card deck");
		
		for(Card card: deck) {
			if(card==null) throw new NullPointerException("Cannot initialize the cardDeck with a deck that contains a null card");
		}
		
		if(type == DeckType.MESSAGE) {
			maxCapacity = 48;
		}
		else if(type == DeckType.DEAL){
			maxCapacity = 20;
		}
		else {
			throw new NullPointerException("Type of card deck cannot be null");
		}
		
		if(deck.size()>maxCapacity) {
			throw new InvalidParameterException("The size of the parameter deck is larger than "
					+ "the max capacity of the cardDeck created");
		}
		
		this.deck = deck;
	}
	/**
	 * <b>Transformer</b>: removes the last element and returns it<br>
	 * <b>Precondition</b>: The deck is not empty<br>
	 * <b>Postcondition</b>: The card is removed from the deck and is returned <br>
	 * @return Top Card
	 */
	public Card getTopCard() {
		if(deck.isEmpty()) {
			throw new IndexOutOfBoundsException("Cannot draw card, the card deck is empty");
		}
		return deck.remove(deck.size()-1);
	}
	/**
	 * <b>Transformer</b>: adds a Card as the first element<br>
	 * <b>Precondition</b>: The deck has not reached maxCapacity and card is not null<br>
	 * <b>Postcondition</b>: The card is added as the first element of the deck<br>
	 * @param card that will be added to the deck
	 */
	public void addCardToBottom(Card card) {
		if(card == null) {
			throw new NullPointerException("Cannot add a null card to the card deck");
		}
		else if(deck.size()+1>maxCapacity) {
			throw new IndexOutOfBoundsException("Add a new card, the card deck has already reached its max capacity");
		}
		deck.add(0, card);
	}
	/**
	 * <b>Observer</b>: returns the current size of the deck<br>
	 * <b>Postcondition</b>: the size is returned<br>
	 * @return size of deck
	 */
	public int getDeckSize() {
		return deck.size();
	}
	/**
	 * <b>Observer</b>: returns true if the deck is empty, else false <br>
	 * <b>Postcondition</b>: boolean whether the deck is empty or not has been returned<br>
	 * @return true/false
	 */
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	/**
	 * <b>Transformer</b>: Shuffles the deck<br>
	 * <b>Precondition</b>: The deck is not empty<br>
	 * <b>Postcondition</b>: The order of the cards in the array list is random<br>
	 */
	public void shuffleDeck() {
		if(deck.isEmpty()) throw new IndexOutOfBoundsException("Cannot shuffle empty deck");
		for(int i=0; i<1000; i++) {
			int number1 = (java.lang.Math.abs(new Random().nextInt()))%deck.size();
			int number2 = (java.lang.Math.abs(new Random().nextInt()))%deck.size();
			Card card = deck.remove(number1);
			deck.add(number2, card);
		}
	}
	/**
	 * returns the string representation of the card deck
	 * @return the string representation of the card deck
	 */
	public String toString() {
		String out = "";
		for(Card card: deck) {
			out+= card.toString()+"\n";
		}
		return out;
	}
}
